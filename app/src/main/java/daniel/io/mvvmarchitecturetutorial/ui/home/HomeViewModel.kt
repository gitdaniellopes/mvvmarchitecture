package daniel.io.mvvmarchitecturetutorial.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.io.mvvmarchitecturetutorial.data.modelresponse.LoginResponse
import daniel.io.mvvmarchitecturetutorial.data.remote.Resource
import daniel.io.mvvmarchitecturetutorial.repository.UserRepository
import daniel.io.mvvmarchitecturetutorial.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user = MutableLiveData<Resource<LoginResponse>>()
    val user: LiveData<Resource<LoginResponse>> = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}