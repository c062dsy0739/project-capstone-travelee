package academy.bangkit.travelee.presentation.verify_email.components

import academy.bangkit.travelee.components.ProgressBar
import academy.bangkit.travelee.domain.model.Response.Failure
import academy.bangkit.travelee.domain.model.Response.Loading
import academy.bangkit.travelee.domain.model.Response.Success
import academy.bangkit.travelee.presentation.profile.ProfileViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ReloadUser(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {
    when(val reloadUserResponse = viewModel.reloadUserResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isUserReloaded = reloadUserResponse.data
            LaunchedEffect(isUserReloaded) {
                if (isUserReloaded) {
                    navigateToProfileScreen()
                }
            }
        }
        is Failure -> reloadUserResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}