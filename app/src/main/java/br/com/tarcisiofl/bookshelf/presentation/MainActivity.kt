package br.com.tarcisiofl.bookshelf.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.tarcisiofl.bookshelf.presentation.details.ui.BookDetailsScreen
import br.com.tarcisiofl.bookshelf.presentation.listing.BookListingViewModel
import br.com.tarcisiofl.bookshelf.presentation.listing.ui.BookListingScreen
import br.com.tarcisiofl.bookshelf.ui.theme.BookshelfTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.onSurface
                ) {
                    BookshelfApp()
                }
            }
        }
    }
}

@Composable
fun BookshelfApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ScreenRoute.BOOK_LISTING_SCREEN.route) {
        composable(ScreenRoute.BOOK_LISTING_SCREEN.route) {
            val viewModel: BookListingViewModel = getViewModel()
            BookListingScreen(navController, viewModel = viewModel)
        }
        composable(
            ScreenRoute.BOOK_DETAILS_SCREEN.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            BookDetailsScreen(
                navController,
                bookId = backStackEntry.arguments?.getInt("bookId")!!,
                viewModel = getViewModel()
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    BookshelfTheme {
        BookshelfApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    BookshelfTheme(darkTheme = true) {
        BookshelfApp()
    }
}