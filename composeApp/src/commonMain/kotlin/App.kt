import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.MongoDB
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.home.HomeScreen
import presentation.home.HomeViewModel
import presentation.task.TaskViewModel

val lightRedColor = Color(color = 0xFFF57D88)
val darkRedColor = Color(color = 0xFF77000B)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    initializeKoin()
    val lightColors = lightColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor,
    )
    val darkColors = darkColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor,
    )

    val colours by mutableStateOf(
        if (isSystemInDarkTheme()) darkColors else lightColors,
    )
    MaterialTheme(colorScheme = colours) {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}

// using koin DI we use module for getting dependency for mondogb clss in viewmodel , unlike hilt it is running in runtime
val mongoModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
}

fun initializeKoin() {
    startKoin {
        modules(mongoModule)
    }
}
