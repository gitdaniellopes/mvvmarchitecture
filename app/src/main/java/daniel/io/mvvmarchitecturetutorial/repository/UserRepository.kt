package daniel.io.mvvmarchitecturetutorial.repository

import daniel.io.mvvmarchitecturetutorial.data.remote.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall { api.getUser() }
}