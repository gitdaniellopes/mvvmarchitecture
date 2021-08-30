package daniel.io.mvvmarchitecturetutorial.repository

import daniel.io.mvvmarchitecturetutorial.data.remote.BaseApi
import daniel.io.mvvmarchitecturetutorial.data.remote.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }
}