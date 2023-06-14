package academy.bangkit.travelee.presentation.forgot_password.components

import academy.bangkit.travelee.components.BackIcon
import academy.bangkit.travelee.utilities.Constants.FORGOT_PASSWORD_SCREEN
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun ForgotPasswordTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = FORGOT_PASSWORD_SCREEN
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}