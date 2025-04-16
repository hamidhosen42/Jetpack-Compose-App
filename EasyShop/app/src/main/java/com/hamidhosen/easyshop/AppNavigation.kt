package com.hamidhosen.easyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.hamidhosen.easyshop.pages.CategoryProductPage
import com.hamidhosen.easyshop.pages.ProductDetailsPage
import com.hamidhosen.easyshop.screen.AuthScreen
import com.hamidhosen.easyshop.screen.HomeScreen
import com.hamidhosen.easyshop.screen.LoginScreen
import com.hamidhosen.easyshop.screen.SignupScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    GlobalNavigation.navController = navController

    val isLoggedIn = Firebase.auth.currentUser != null
    val firstPage = if (isLoggedIn) "home" else "auth"

    NavHost(
        navController = navController, startDestination = firstPage
    ) {
        composable("auth") {
            AuthScreen(modifier, navController)
        }

        composable("login") {
            LoginScreen(modifier, navController)
        }

        composable("signup") {
            SignupScreen(modifier, navController)
        }

        composable("home") {
            HomeScreen(modifier, navController)
        }

        composable("category-products/{categoryId}") {
            val categoryId = it.arguments?.getString("categoryId") ?: ""
            CategoryProductPage(modifier, categoryId)
        }

        composable("products-details/{productId}") {
            val productId = it.arguments?.getString("productId") ?: ""
            ProductDetailsPage(modifier, productId?:"")
        }
    }
}

object GlobalNavigation {
    lateinit var navController: NavController
}