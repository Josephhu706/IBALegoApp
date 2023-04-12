package com.example.legosearchapp.fake

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.legosearchapp.model.Theme
import com.example.legosearchapp.model.Set
import com.example.legosearchapp.utils.readCSV
import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.example.legosearchapp.LegoSearchApplication
import com.example.legosearchapp.fake.fake.expectedSets
import com.example.legosearchapp.fake.fake.expectedThemes
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CsvTests {
    var legoSearchContext: Context = LegoSearchApplication.context!!

    @Test
    fun starwarsCSVReader_readingCorrectFilesForSets(){
        val testSetData = readCSV("testSets.csv", legoSearchContext, Set::class)
        assertEquals(expectedSets, testSetData)
    }

    @Test
    fun starwarsCSVReader_readingCorrectFilesForThemes(){
        val testThemeData = readCSV("testThemes.csv", legoSearchContext, Theme::class)
        assertEquals(expectedThemes, testThemeData)
    }

}