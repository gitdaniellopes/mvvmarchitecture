package daniel.io.mvvmarchitecturetutorial.data.remote

import daniel.io.mvvmarchitecturetutorial.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//email: probelalkhan1@gmail.com
//password: 12345678
class RemoteDataSource {
    companion object {
        private const val BASE_URL = "http://simplifiedcoding.tech//mywebapp/public/api"
        private const val BASE_URL_USER = "https://simplifiedcoding.tech/mywebapp/public/api/user"
    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Authorization", "Bearer $authToken")
                    }.build())
                }
                .also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.level = HttpLoggingInterceptor.Level.BODY
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}