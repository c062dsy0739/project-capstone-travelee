package academy.bangkit.travelee.presentation.sign_in.components

import academy.bangkit.travelee.components.ProgressBar
import academy.bangkit.travelee.domain.model.Response.Failure
import academy.bangkit.travelee.domain.model.Response.Loading
import academy.bangkit.travelee.domain.model.Response.Success
import academy.bangkit.travelee.presentation.sign_in.SignInViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
        }
    }
}