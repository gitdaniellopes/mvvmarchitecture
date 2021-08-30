package daniel.io.mvvmarchitecturetutorial.data.remote

import daniel.io.mvvmarchitecturetutorial.data.modelresponse.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi : BaseApi {

    //field por que são campos
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse
}