package com.example.fakestoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fakestoreapp.screens.ProductDetailScreen
import com.example.fakestoreapp.screens.ProductsScreen
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "products"
                ) {
                    composable(route = "products") {
                        ProductsScreen(navController = navController)
                    }
                    composable(
                        route = "products/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                                nullable = false
                            }
                        )
                    ) { backStack ->
                        val id = backStack.arguments?.getInt("id") ?: 0
                        ProductDetailScreen(
                            id = id,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
