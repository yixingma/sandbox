package com.matcha.leetcode.word_pattern_match;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LC93_RestoreIpAddressesTest {

    private final LC93_RestoreIpAddresses solver = new LC93_RestoreIpAddresses();

    @Test
    public void testRestoreIpAddressesBasic() {
        List<String> res1 = solver.restoreIpAddresses("25525511135");
        assertEquals(2, res1.size());
        assertTrue(res1.contains("255.255.11.135"));
        assertTrue(res1.contains("255.255.111.35"));

        List<String> res2 = solver.restoreIpAddresses("0000");
        assertEquals(1, res2.size());
        assertTrue(res2.contains("0.0.0.0"));
    }

    @Test
    public void testRestoreIpAddressesMultiple() {
        List<String> res = solver.restoreIpAddresses("101023");
        assertEquals(5, res.size());
        assertTrue(res.contains("1.0.10.23"));
        assertTrue(res.contains("1.0.102.3"));
        assertTrue(res.contains("10.1.0.23"));
        assertTrue(res.contains("10.10.2.3"));
        assertTrue(res.contains("101.0.2.3"));
    }

    @Test
    public void testRestoreIpAddressesEdgeCases() {
        // Length constraints
        assertTrue(solver.restoreIpAddresses("255").isEmpty());
        assertTrue(solver.restoreIpAddresses("2552552552552").isEmpty());

        // Impossible values
        assertTrue(solver.restoreIpAddresses("999999999999").isEmpty());
    }
}
