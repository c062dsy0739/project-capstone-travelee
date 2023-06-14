package academy.bangkit.travelee.presentation.sign_up

import academy.bangkit.travelee.domain.model.Response.Loading
import academy.bangkit.travelee.domain.model.Response.Success
import academy.bangkit.travelee.domain.repository.AuthRepository
import academy.bangkit.travelee.domain.repository.SendEmailVerificationResponse
import academy.bangkit.travelee.domain.repository.SignUpResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Success(false))
        private set
    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(Success(false))
        private set

    fun signUpWithEmailAndPassword(name : String, email: String, password: String) = viewModelScope.launch {
        signUpResponse = Loading
        signUpResponse = repo.firebaseSignUpWithEmailAndPassword(name, email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()
    }
}