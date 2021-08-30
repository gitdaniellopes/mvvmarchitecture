package daniel.io.mvvmarchitecturetutorial.ui.base

import androidx.lifecycle.ViewModel
import daniel.io.mvvmarchitecturetutorial.data.remote.UserApi
import daniel.io.mvvmarchitecturetutorial.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel() {

    suspend fun logout(api: UserApi) = repository.logout(api)
}