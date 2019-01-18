package com.rlperez

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.io.InputStream

class ArtistAnalyzerTest {
    private lateinit var testFileStream: InputStream

    @Before
    fun before() {
        File("./Artist_lists_small.txt").createNewFile()
        testFileStream = File("./Artist_lists_small.txt").inputStream()
    }

    @After
    fun after() {
        testFileStream.close()
        File("./Artist_lists_small.txt").delete()
    }

    @Test
    fun combinationsTest() {
        val analyzer = ArtistAnalyzer(testFileStream, File("output.txt").outputStream())
        val combinations = analyzer.combinations(listOf("A", "B", "C"))
        assertThat(combinations)
            .isNotEmpty
            .containsOnlyOnce("A,B")
            .containsOnlyOnce("A,C")
            .containsOnlyOnce("B,C")
            .doesNotContain("A,A")
            .doesNotContain("B,A")
            .doesNotContain("C,A")
    }

    @Test
    fun filterOccurrencesTest() {
        val analyzer = ArtistAnalyzer(testFileStream, File("output.txt").outputStream())
        val values = mutableMapOf("A" to 51, "B" to 50, "C" to 1)
        val result = analyzer.filterOccurrences(values, 50)
        assertThat(result)
            .contains("A")
            .contains("B")
            .doesNotContain("C")
    }
}
