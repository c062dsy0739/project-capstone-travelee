package com.travelee.signing

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController



@Composable
fun LoginScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val authType = remember {
        mutableStateOf("email")
    }

}
