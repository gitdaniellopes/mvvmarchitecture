package daniel.io.mvvmarchitecturetutorial.ui.auth

import androidx.lifecycle.ViewModel
import daniel.io.mvvmarchitecturetutorial.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {
}