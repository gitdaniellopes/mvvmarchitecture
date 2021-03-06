package daniel.io.mvvmarchitecturetutorial.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import daniel.io.mvvmarchitecturetutorial.data.modelresponse.LoginResponse
import daniel.io.mvvmarchitecturetutorial.data.remote.Resource
import daniel.io.mvvmarchitecturetutorial.repository.AuthRepository
import daniel.io.mvvmarchitecturetutorial.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>> = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email, password)
    }

   suspend fun saveAuthToken(token: String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }
}