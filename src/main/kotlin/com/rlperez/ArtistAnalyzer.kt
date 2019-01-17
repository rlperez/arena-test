package com.rlperez

import java.io.File
import java.net.URI

class ArtistAnalyzer(private val fileInputPath: URI, private val fileOutputPath: URI) {
    private val counts: MutableMap<String, Int> = mutableMapOf()

    fun analyze() {
        readInput(fileInputPath)
        val result: Set<String> = filterOccurrences(counts, 50)
        writeOutput(result, fileOutputPath)
    }

    fun readInput(fileInputPath: URI) {
        counts.clear()
        File(fileInputPath).bufferedReader().useLines { lines ->
            lines.forEach { line ->
                val combinations: Set<String> = combinations(line.split(","))
                combinations.forEach { counts[it] = counts.getOrDefault(it, 0) + 1 }
            }
        }
    }

    fun writeOutput(values: Set<String>, fileOutputPath: URI): Unit {
        File(fileOutputPath).bufferedWriter().use { bw ->
            values.forEach { bw.appendln(it) }
        }
    }

    /**
     * Returns a set of keys for which the value is greater than or equal to the minimumOccurrences value.
     */
    fun filterOccurrences(artistFrequencies: Map<String, Int>, minimumOccurrences: Int): Set<String> =
        artistFrequencies.filter { it.value >= minimumOccurrences }.keys

    /**
     * Returns unique combinations of elements in the list as a Set of comma separated strings.
     * @param artists: List<String>
     * @return Set<String>
     */
    fun combinations(artists: List<String>): Set<String> =
        artists.mapIndexed { i, artist ->
            artists.drop(i + 1)
                .map { otherArtist ->
                    listOf(artist, otherArtist).sorted().joinToString(",")
                }
        }.flatten().toSet()
}


