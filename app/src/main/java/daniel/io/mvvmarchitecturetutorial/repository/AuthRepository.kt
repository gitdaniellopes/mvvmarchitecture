package daniel.io.mvvmarchitecturetutorial.repository

import daniel.io.mvvmarchitecturetutorial.data.UserPreferences
import daniel.io.mvvmarchitecturetutorial.data.remote.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }
}