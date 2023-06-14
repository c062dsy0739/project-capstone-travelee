package academy.bangkit.travelee.presentation.sign_up

import academy.bangkit.travelee.presentation.sign_up.components.SendEmailVerification
import academy.bangkit.travelee.presentation.sign_up.components.SignUp
import academy.bangkit.travelee.presentation.sign_up.components.SignUpContent
import academy.bangkit.travelee.utilities.Constants.VERIFY_EMAIL_MESSAGE
import academy.bangkit.travelee.utilities.Utils.Companion.showMessage
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            SignUpContent(
                padding = padding,
                signUp = { name, email, password ->
                    viewModel.signUpWithEmailAndPassword(name, email, password)
                },
                navigateBack = navigateBack
            )
        }
    )

    SignUp(
        sendEmailVerification = {
            viewModel.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, VERIFY_EMAIL_MESSAGE)
        }
    )

    SendEmailVerification()
}