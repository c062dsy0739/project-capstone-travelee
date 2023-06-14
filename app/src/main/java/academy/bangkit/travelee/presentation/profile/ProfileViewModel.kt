package academy.bangkit.travelee.presentation.profile

import academy.bangkit.travelee.domain.model.Response.Loading
import academy.bangkit.travelee.domain.model.Response.Success
import academy.bangkit.travelee.domain.repository.AuthRepository
import academy.bangkit.travelee.domain.repository.ReloadUserResponse
import academy.bangkit.travelee.domain.repository.RevokeAccessResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }
}