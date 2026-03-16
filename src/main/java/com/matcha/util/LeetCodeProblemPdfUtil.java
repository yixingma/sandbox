package com.matcha.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Utility to read a LeetCode problems JSON dataset and write selected fields into a PDF table.
 * <p>
 * Expects JSON array of objects with at least: frontend_id, title, difficulty.
 * Example: [{"frontend_id":"1","title":"Two Sum","difficulty":"Easy"}, ...]
 */
public final class LeetCodeProblemPdfUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /** Default path to the merged LeetCode problems JSON dataset. */
    public static final Path DEFAULT_JSON_PATH = Path.of("/Users/yixingma/Downloads/merged_problems.json");

    /** Default path to the leetcode package to scan for solved problems (relative to working dir). */
    public static final Path DEFAULT_LEETCODE_SOURCE_DIR = Path.of("src/main/java/com/matcha/leetcode");

    private static final Pattern LC_CLASS_PATTERN = Pattern.compile("LC(\\d+)_");

    /** Light green background for solved rows. */
    private static final java.awt.Color SOLVED_HIGHLIGHT = new java.awt.Color(0xCCFFCC);

    private LeetCodeProblemPdfUtil() {}

    /**
     * Reads the JSON dataset and extracts frontend_id, title, and difficulty into a list of rows.
     *
     * @param jsonPath path to the JSON file
     * @return list of problem rows (frontend_id, title, difficulty)
     * @throws IOException if the file cannot be read or JSON is invalid
     */
    public static List<LeetCodeProblemRow> readFromJson(Path jsonPath) throws IOException {
        try (InputStream in = Files.newInputStream(jsonPath)) {
            return readFromJson(in);
        }
    }

    /**
     * Reads the JSON dataset from an input stream and extracts frontend_id, title, and difficulty.
     *
     * @param inputStream source of the JSON (array of problem objects)
     * @return list of problem rows
     * @throws IOException if reading or parsing fails
     */
    public static List<LeetCodeProblemRow> readFromJson(InputStream inputStream) throws IOException {
        JsonNode root = OBJECT_MAPPER.readTree(inputStream);
        JsonNode array = root.isArray() ? root : root.has("questions") ? root.get("questions") : null;
        if (array == null || !array.isArray()) {
            throw new IOException("Expected JSON array of problems or object with 'questions' array");
        }
        List<LeetCodeProblemRow> rows = new ArrayList<>();
        for (JsonNode node : array) {
            String frontendId = getText(node, "frontend_id");
            String title = getText(node, "title");
            String difficulty = getText(node, "difficulty");
            rows.add(new LeetCodeProblemRow(frontendId, title, difficulty));
        }
        return rows;
    }

    private static String getText(JsonNode node, String field) {
        JsonNode value = node.has(field) ? node.get(field) : null;
        if (value == null || value.isNull()) {
            return "";
        }
        if (value.isTextual()) {
            return value.asText();
        }
        return value.toString();
    }

    /**
     * Writes the problem rows into a PDF file with a simple table (frontend_id, title, difficulty).
     * Rows whose frontend_id is in {@code solvedIds} are highlighted (light green).
     *
     * @param rows     list of problem rows to write
     * @param pdfPath  path where the PDF will be written
     * @param solvedIds set of frontend_id values for problems solved in this repo (e.g. "199", "200")
     * @throws IOException       if the file cannot be written
     * @throws DocumentException if PDF creation fails
     */
    public static void writeToPdf(List<LeetCodeProblemRow> rows, Path pdfPath, Set<String> solvedIds) throws IOException, DocumentException {
        Rectangle pageSize = PageSize.A4.rotate();
        float margin = 12f;
        Document document = new Document(pageSize, margin, margin, margin, margin);
        PdfWriter.getInstance(document, Files.newOutputStream(pdfPath));
        document.open();

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 6f, 1.5f});
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        Font headerFont = new Font(Font.HELVETICA, 7, Font.BOLD);
        Font cellFont = new Font(Font.HELVETICA, 6, Font.NORMAL);

        // Header row
        table.addCell(createCell("Frontend ID", headerFont, true, false));
        table.addCell(createCell("Title", headerFont, true, false));
        table.addCell(createCell("Difficulty", headerFont, true, false));

        for (LeetCodeProblemRow row : rows) {
            boolean highlight = solvedIds != null && solvedIds.contains(row.frontendId());
            table.addCell(createCell(row.frontendId(), cellFont, false, highlight));
            table.addCell(createCell(row.title(), cellFont, false, highlight));
            table.addCell(createCell(row.difficulty(), cellFont, false, highlight));
        }

        document.add(table);
        document.close();
    }

    /**
     * Writes the problem rows into a PDF with no highlighting (no solved set).
     */
    public static void writeToPdf(List<LeetCodeProblemRow> rows, Path pdfPath) throws IOException, DocumentException {
        writeToPdf(rows, pdfPath, Set.of());
    }

    private static PdfPCell createCell(String text, Font font, boolean header, boolean highlight) {
        PdfPCell cell = new PdfPCell(new Phrase(text != null ? text : "", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(1.5f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (header) {
            cell.setBackgroundColor(new java.awt.Color(0xE0E0E0));
        } else if (highlight) {
            cell.setBackgroundColor(SOLVED_HIGHLIGHT);
        }
        return cell;
    }

    /**
     * Scans the leetcode source directory for LC###_*.java classes and returns the set of frontend IDs (e.g. "199", "200").
     *
     * @param leetcodeDir path to the com.matcha.leetcode package directory (may contain subdirs like levelordertraversal)
     * @return set of problem numbers as strings
     */
    public static Set<String> findSolvedFrontendIds(Path leetcodeDir) throws IOException {
        Set<String> ids = new HashSet<>();
        if (!Files.isDirectory(leetcodeDir)) {
            return ids;
        }
        try (var stream = Files.walk(leetcodeDir)) {
            stream.filter(p -> p.getFileName().toString().endsWith(".java"))
                    .forEach(p -> {
                        Matcher m = LC_CLASS_PATTERN.matcher(p.getFileName().toString());
                        if (m.find()) {
                            ids.add(m.group(1));
                        }
                    });
        }
        return ids;
    }

    /**
     * Reads the JSON dataset from the given path and writes the extracted rows to the given PDF path.
     * Solved problems (LC### classes under the leetcode package) are highlighted in the PDF.
     *
     * @param jsonPath path to the JSON file
     * @param pdfPath  path for the output PDF
     * @param repoRoot project root (e.g. user.dir); used to resolve leetcode source dir for finding solved IDs
     * @throws IOException       if reading or writing fails
     * @throws DocumentException if PDF creation fails
     */
    public static void readJsonAndWritePdf(Path jsonPath, Path pdfPath, Path repoRoot) throws IOException, DocumentException {
        List<LeetCodeProblemRow> rows = readFromJson(jsonPath);
        Path leetcodeDir = repoRoot != null ? repoRoot.resolve(DEFAULT_LEETCODE_SOURCE_DIR) : null;
        Set<String> solvedIds = (leetcodeDir != null && Files.exists(leetcodeDir))
                ? findSolvedFrontendIds(leetcodeDir) : Set.of();
        writeToPdf(rows, pdfPath, solvedIds);
    }

    /**
     * Reads the JSON and writes the PDF; discovers solved problems from {@code src/main/java/com/matcha/leetcode}
     * relative to the current working directory.
     */
    public static void readJsonAndWritePdf(Path jsonPath, Path pdfPath) throws IOException, DocumentException {
        readJsonAndWritePdf(jsonPath, pdfPath, Path.of(System.getProperty("user.dir", ".")));
    }

    /**
     * Reads from {@link #DEFAULT_JSON_PATH} and writes the PDF to the given path.
     */
    public static void readDefaultJsonAndWritePdf(Path pdfPath) throws IOException, DocumentException {
        readJsonAndWritePdf(DEFAULT_JSON_PATH, pdfPath);
    }

    /**
     * Convenience main: reads from the default JSON path and writes to leetcode_problems.pdf
     * in the same directory as the JSON file (or current dir if JSON path is not a file).
     */
    public static void main(String[] args) {
        Path jsonPath = args.length > 0 ? Path.of(args[0]) : DEFAULT_JSON_PATH;
        Path pdfPath = args.length > 1 ? Path.of(args[1]) : jsonPath.getParent().resolve("leetcode_problems.pdf");
        Path repoRoot = args.length > 2 ? Path.of(args[2]) : Path.of(System.getProperty("user.dir", "."));
        try {
            readJsonAndWritePdf(jsonPath, pdfPath, repoRoot);
            System.out.println("Wrote " + pdfPath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Row data: frontend_id, title, difficulty.
     */
    public record LeetCodeProblemRow(String frontendId, String title, String difficulty) {}
}
