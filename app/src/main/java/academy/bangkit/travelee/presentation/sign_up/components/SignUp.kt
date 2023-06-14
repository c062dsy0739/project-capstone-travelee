package academy.bangkit.travelee.presentation.sign_up.components

import academy.bangkit.travelee.components.ProgressBar
import academy.bangkit.travelee.domain.model.Response.*
import academy.bangkit.travelee.presentation.sign_up.SignUpViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
    sendEmailVerification: () -> Unit,
    showVerifyEmailMessage: () -> Unit
) {
    when(val signUpResponse = viewModel.signUpResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}