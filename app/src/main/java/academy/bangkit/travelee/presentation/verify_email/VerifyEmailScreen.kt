package academy.bangkit.travelee.presentation.verify_email

import academy.bangkit.travelee.presentation.profile.ProfileViewModel
import academy.bangkit.travelee.presentation.verify_email.components.ReloadUser
import academy.bangkit.travelee.presentation.verify_email.components.VerifyEmailContent
import academy.bangkit.travelee.sources.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import academy.bangkit.travelee.utilities.Utils.Companion.showMessage
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        content = { padding ->
            VerifyEmailContent(
                padding = padding,
                reloadUser = {
                    viewModel.reloadUser()
                }
            )
        },
        scaffoldState = scaffoldState
    )

    ReloadUser(
        navigateToProfileScreen = {
            if (viewModel.isEmailVerified) {
                navigateToProfileScreen()
            } else {
                showMessage(context, EMAIL_NOT_VERIFIED_MESSAGE)
            }
        }
    )
}