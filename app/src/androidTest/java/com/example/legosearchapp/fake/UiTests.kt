package com.example.legosearchapp.fake

import android.content.Context
import android.content.res.Resources.Theme
import androidx.activity.ComponentActivity
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.legosearchapp.LegoSearchApp
import com.example.legosearchapp.LegoSearchApplication
import com.example.legosearchapp.data.DataStoreRepository
import com.example.legosearchapp.ui.screens.LegoAppViewModel
import com.example.legosearchapp.ui.theme.LegoSearchAppTheme
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class UiTests {
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())

    fun setupTestRepoAndViewModel(tempFolder: TemporaryFolder):LegoAppViewModel{
        val newFile : File = tmpFolder.newFile("savedData.preferences_pb")
        val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create (
            scope = testScope,
            produceFile = {newFile}
        )
        val testRepository: DataStoreRepository = DataStoreRepository(testDataStore)
        return LegoAppViewModel(dataStoreRepository = testRepository)
    }

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    var starwarsContext: Context = LegoSearchApplication.context!!

    private lateinit var navController: TestNavHostController

    fun setupComposeTestRuleForApp(viewModel : LegoAppViewModel){
        composeTestRule.setContent {
            navController = TestNavHostController(starwarsContext)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            val uiState = viewModel.uiState.collectAsState().value
            LegoSearchAppTheme(
                uiState.isDarkTheme
            ) {
                LegoSearchApp(
                    windowSize = WindowWidthSizeClass.Compact,
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }

    @Test
    fun testTopBarAppears(){
        val viewModel = setupTestRepoAndViewModel(tmpFolder)
        setupComposeTestRuleForApp(viewModel)
        composeTestRule.onNodeWithTag("Top Bar").assertExists()
    }

    @Test
    fun testDarkModeToggleAppears(){
        val viewModel = setupTestRepoAndViewModel(tmpFolder)
        setupComposeTestRuleForApp(viewModel)
        composeTestRule.onNodeWithTag("Dark Mode Toggle").assertExists()
        assertEquals(viewModel.uiState.value.isDarkTheme, false)
    }

    @Test
    fun testDarkModeToggleChangesDarkTheme(){
        val viewModel = setupTestRepoAndViewModel(tmpFolder)
        setupComposeTestRuleForApp(viewModel)
        composeTestRule.onNodeWithTag("Dark Mode Toggle").performClick()
        assertEquals(viewModel.uiState.value.isDarkTheme, true)
    }

    @Test
    fun testCorrectTextDisplayedWhenToggleDarkTheme(){
        val viewModel = setupTestRepoAndViewModel(tmpFolder)
        setupComposeTestRuleForApp(viewModel)
        composeTestRule.onNodeWithText("Light Mode").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Dark Mode Toggle").performClick()
        composeTestRule.mainClock.autoAdvance = true // default
        composeTestRule.waitForIdle() // Advances the clock until Compose is idle
        composeTestRule.onNodeWithText("Dark Mode").assertIsDisplayed()
    }
}