package daniel.io.mvvmarchitecturetutorial.data.remote

import daniel.io.mvvmarchitecturetutorial.data.modelresponse.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}