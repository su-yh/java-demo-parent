package com.suyh0103.valuesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvFileSourceTest {

    // Skip the first line
    @ParameterizedTest
    @CsvFileSource(resources = "/simple.csv", numLinesToSkip = 1)
    void test_csv_file(String str, int length) {
        Assertions.assertEquals(length, str.length());
    }

}
