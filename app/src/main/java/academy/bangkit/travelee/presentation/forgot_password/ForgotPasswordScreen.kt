package academy.bangkit.travelee.presentation.forgot_password

import academy.bangkit.travelee.presentation.forgot_password.components.ForgotPassword
import academy.bangkit.travelee.presentation.forgot_password.components.ForgotPasswordContent
import academy.bangkit.travelee.utilities.Constants.RESET_PASSWORD_MESSAGE
import academy.bangkit.travelee.utilities.Utils.Companion.showMessage
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            ForgotPasswordContent(
                padding = padding,
                sendPasswordResetEmail = { email ->
                    viewModel.sendPasswordResetEmail(email)
                }
            )
        }
    )

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = {
            showMessage(context, RESET_PASSWORD_MESSAGE)
        },
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}