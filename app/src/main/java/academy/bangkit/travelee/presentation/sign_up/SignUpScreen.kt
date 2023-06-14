package academy.bangkit.travelee.presentation.sign_up

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.travelee.signing.presentation.sign_up.components.SendEmailVerification
import com.travelee.signing.presentation.sign_up.components.SignUp
import com.travelee.signing.presentation.sign_up.components.SignUpContent
import com.travelee.signing.utilities.Constants.VERIFY_EMAIL_MESSAGE
import com.travelee.signing.utilities.Utils.Companion.showMessage

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