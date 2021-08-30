package daniel.io.mvvmarchitecturetutorial.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import daniel.io.mvvmarchitecturetutorial.data.modelresponse.User
import daniel.io.mvvmarchitecturetutorial.repository.AuthRepository
import daniel.io.mvvmarchitecturetutorial.repository.BaseRepository
import daniel.io.mvvmarchitecturetutorial.repository.UserRepository
import daniel.io.mvvmarchitecturetutorial.ui.auth.AuthViewModel
import daniel.io.mvvmarchitecturetutorial.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}