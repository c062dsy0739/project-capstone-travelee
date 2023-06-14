package academy.bangkit.travelee.viewmodel

import academy.bangkit.travelee.domain.repository.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    init {
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false
}