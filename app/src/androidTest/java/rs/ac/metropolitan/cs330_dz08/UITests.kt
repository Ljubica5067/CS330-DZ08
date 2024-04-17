package rs.ac.metropolitan.cs330_dz08

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITests {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()
    @Test
    fun homeScreenNavigationToDetailScreen() {
        rule.onNodeWithText("Food List").assertExists()
    }
}