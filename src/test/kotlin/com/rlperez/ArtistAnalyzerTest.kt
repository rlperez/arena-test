package com.rlperez

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.net.URI

class ArtistAnalyzerTest {
    @Test
    fun combinationsTest() {
        val analyzer = ArtistAnalyzer(URI(""), URI(""))
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
        val analyzer = ArtistAnalyzer(URI(""), URI(""))
        val values = mutableMapOf("A" to 51, "B" to 50, "C" to 1)
        val result = analyzer.filterOccurrences(values, 50)
        assertThat(result)
            .contains("A")
            .contains("B")
            .doesNotContain("C")
    }
}
