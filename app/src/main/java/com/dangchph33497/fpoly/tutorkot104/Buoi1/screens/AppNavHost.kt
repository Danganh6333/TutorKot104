package com.dangchph33497.fpoly.tutorkot104.Buoi1.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class ROUTE_SCREEN_NAME{
    WELCOME,
    HOME
}

@Composable
fun AppNavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = ROUTE_SCREEN_NAME.WELCOME.name
    ){
        composable(ROUTE_SCREEN_NAME.WELCOME.name){
            WelcomeScreen(navHostController = navController)
        }
        composable(ROUTE_SCREEN_NAME.HOME.name){

        }
    }
}