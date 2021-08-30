package daniel.io.mvvmarchitecturetutorial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import daniel.io.mvvmarchitecturetutorial.data.modelresponse.LoginResponse
import daniel.io.mvvmarchitecturetutorial.data.remote.Resource
import daniel.io.mvvmarchitecturetutorial.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableLiveData<Resource<LoginResponse>>()
    val user: LiveData<Resource<LoginResponse>> = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}