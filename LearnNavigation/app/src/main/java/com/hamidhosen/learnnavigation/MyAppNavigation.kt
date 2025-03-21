package com.hamidhosen.learnnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun  MyAppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.screenA, builder = {
        composable(Routes.screenA){
            ScreenA(navController)
        }
        composable(Routes.screenB+"/{name}") {
            var name = it.arguments?.getString("name")
            ScreenB(name?:"No Name")
        }
    })
}