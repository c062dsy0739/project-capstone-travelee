package academy.bangkit.travelee.presentation.forgot_password.components

import academy.bangkit.travelee.components.ProgressBar
import academy.bangkit.travelee.domain.model.Response.Failure
import academy.bangkit.travelee.domain.model.Response.Loading
import academy.bangkit.travelee.domain.model.Response.Success
import academy.bangkit.travelee.presentation.forgot_password.ForgotPasswordViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ForgotPassword(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    showResetPasswordMessage: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val sendPasswordResetEmailResponse = viewModel.sendPasswordResetEmailResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isPasswordResetEmailSent = sendPasswordResetEmailResponse.data
            LaunchedEffect(isPasswordResetEmailSent) {
                if (isPasswordResetEmailSent) {
                    navigateBack()
                    showResetPasswordMessage()
                }
            }
        }
        is Failure -> sendPasswordResetEmailResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}