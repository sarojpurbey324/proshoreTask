package com.proshore.utils;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlUtil {

    private SqlUtil() {
    }
    
    public static String normalizeSql(String input) {
        return input.replaceAll("\\s", "").toLowerCase();
    }

    public static void assertSqlStrings(String expected, String actual) {
        assertEquals(normalizeSql(expected), normalizeSql(actual));
    }

    public static void assertEqualForParams(Map<String, Object> expected, Map<String, Object> actual) {
        assertEquals(expected.size(), actual.size());
        for (Map.Entry<String, Object> entry : actual.entrySet()) {
            Object expectedValue = expected.get(entry.getKey());
            assertEquals(expectedValue, entry.getValue());
        }
    }

    public static void assertEqualForMapSqlParams(List<MapSqlParameterSource> expected, List<MapSqlParameterSource> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            for (String parameterName : actual.get(i).getParameterNames()) {
                Object expectedValue = expected.get(i).getValue(parameterName);
                assertEquals(expectedValue, actual.get(i).getValue(parameterName));
            }
        }
    }
}
