package com.example.legosearchapp.utils

import android.content.Context
import com.example.legosearchapp.model.Theme
import com.example.legosearchapp.model.Set
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import kotlin.reflect.KClass

fun <T : Any> readCSV(
    fileName: String,
    appContext: Context,
    csvType: KClass<T>
): MutableList<T> {
    val bufferReader = BufferedReader(appContext.assets.open(fileName).reader())
    val csvParser = CSVParser.parse(
        bufferReader, CSVFormat.DEFAULT
            .withDelimiter(',')
            .withQuote('"')
            .withRecordSeparator("\r\n")
    )
    if (csvType == Set::class) {
        val sets = mutableListOf<Set>()
        for (csvRecord in csvParser) {
            sets.add(
                Set(
                    setId = csvRecord.get(0),
                    name = csvRecord.get(1),
                    year = csvRecord.get(2).toInt(),
                    themeId = csvRecord.get(3).toInt(),
                    numParts = csvRecord.get(4).toInt(),
                )
            )
        }
        return sets as MutableList<T>
    } else {
        val themes = mutableListOf<Theme>()
        for (csvRecord in csvParser) {
            themes.add(
                Theme(
                    id = csvRecord.get(0).toInt(),
                    name = csvRecord.get(1),
                    parentId = csvRecord.get(2).toInt()
                )
            )
        }
        return themes as MutableList<T>
    }
}