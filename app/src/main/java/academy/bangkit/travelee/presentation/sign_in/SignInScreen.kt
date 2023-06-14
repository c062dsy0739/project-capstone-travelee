package academy.bangkit.travelee.presentation.sign_in

import academy.bangkit.travelee.presentation.sign_in.components.SignIn
import academy.bangkit.travelee.presentation.sign_in.components.SignInContent
import academy.bangkit.travelee.utilities.Utils.Companion.showMessage
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
@ExperimentalComposeUiApi
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            SignInContent(
                padding = padding,
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen
            )
        }
    )

    SignIn(
        showErrorMessage = { errorMessage ->
            showMessage(context, errorMessage)
        }
    )
}