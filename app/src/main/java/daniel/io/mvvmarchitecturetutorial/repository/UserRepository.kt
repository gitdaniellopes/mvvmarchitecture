package daniel.io.mvvmarchitecturetutorial.repository

import daniel.io.mvvmarchitecturetutorial.data.remote.UserApi

class UserRepository(
//    private val api: AuthApi,
//    private val preferences: UserPreferences
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }


//    suspend fun login(
//        email: String,
//        password: String
//    ) = safeApiCall {
//        api.login(email, password)
//    }
//
//    suspend fun saveAuthToken(token: String) {
//        preferences.saveAuthToken(token)
//    }
}