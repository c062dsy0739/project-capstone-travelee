package academy.bangkit.travelee.presentation.sign_up.components

import academy.bangkit.travelee.components.ProgressBar
import academy.bangkit.travelee.domain.model.Response.Failure
import academy.bangkit.travelee.domain.model.Response.Loading
import academy.bangkit.travelee.domain.model.Response.Success
import academy.bangkit.travelee.presentation.sign_up.SignUpViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

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