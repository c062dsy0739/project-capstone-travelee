package com.travelee.signing.presentation.sign_up.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.travelee.signing.components.ProgressBar
import com.travelee.signing.domain.model.Response.Failure
import com.travelee.signing.domain.model.Response.Loading
import com.travelee.signing.domain.model.Response.Success
import com.travelee.signing.presentation.sign_up.SignUpViewModel
import com.travelee.signing.utilities.Utils.Companion.print

@Composable
fun SendEmailVerification(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    when(val sendEmailVerificationResponse = viewModel.sendEmailVerificationResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> sendEmailVerificationResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}