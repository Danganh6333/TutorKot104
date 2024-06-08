package com.dangchph33497.fpoly.tutorkot104.Buoi1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.dangchph33497.fpoly.tutorkot104.R
import kotlinx.coroutines.time.delay

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .safeDrawingPadding(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
        Text(text = "PH33497", style = MaterialTheme.typography.titleLarge)
    }
    LaunchedEffect(Unit) {
        delay(2000L)
        navHostController
    }
}