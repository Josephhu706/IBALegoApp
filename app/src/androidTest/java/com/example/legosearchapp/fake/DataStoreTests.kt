package com.example.legosearchapp.fake

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.legosearchapp.data.DataStoreRepository
import junit.framework.TestCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataStoreTests {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())
    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create (
        scope = testScope,
        produceFile = {tmpFolder.newFile("saved.preferences_pb")}
    )
    private val datastoreRepository: DataStoreRepository = DataStoreRepository(testDataStore)

    @Test
    fun testGetDarkModeSavedStateIsWorking(){
        testScope.runTest {
            val savedState = datastoreRepository.getDarkMode().first()
            TestCase.assertEquals(false, savedState)
        }
    }

    @Test
    fun testSavingDarkModeSavedStateIsWorking(){
        testScope.runTest {
            datastoreRepository.setIsDarkMode(true)
            val savedState = datastoreRepository.getDarkMode().first()
            TestCase.assertEquals(true, savedState)
        }
    }
}