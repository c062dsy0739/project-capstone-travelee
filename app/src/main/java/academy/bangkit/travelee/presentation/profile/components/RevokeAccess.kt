package academy.bangkit.travelee.presentation.profile.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.travelee.signing.components.ProgressBar
import com.travelee.signing.domain.model.Response.Failure
import com.travelee.signing.domain.model.Response.Loading
import com.travelee.signing.domain.model.Response.Success
import com.travelee.signing.presentation.profile.ProfileViewModel
import com.travelee.signing.utilities.Constants.ACCESS_REVOKED_MESSAGE
import com.travelee.signing.utilities.Constants.REVOKE_ACCESS_MESSAGE
import com.travelee.signing.utilities.Constants.SENSITIVE_OPERATION_MESSAGE
import com.travelee.signing.utilities.Constants.SIGN_OUT_ITEM
import com.travelee.signing.utilities.Utils.Companion.showMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT_ITEM
        )
        if (result == SnackbarResult.ActionPerformed) {
            signOut()
        }
    }

    when(val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Loading -> ProgressBar()
        is Success -> {
            val isAccessRevoked = revokeAccessResponse.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked) {
                    showMessage(context, ACCESS_REVOKED_MESSAGE)
                }
            }
        }
        is Failure -> revokeAccessResponse.apply {
            LaunchedEffect(e) {
                print(e)
                if (e.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}