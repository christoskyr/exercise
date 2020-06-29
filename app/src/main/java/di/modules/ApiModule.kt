package di.modules

import dagger.Module
import dagger.Provides
import network.RestBackEndService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private const val BASE_URL = "https://vivawallet.free.beeceptor.com/v1/api"
    }

    @Provides
    @Singleton
    fun provideRestBackEndService(httpClient: OkHttpClient, gson: GsonConverterFactory): RestBackEndService {
        val retrofit: Retrofit = Retrofit.Builder().client(httpClient)
            .baseUrl(BASE_URL).addConverterFactory(gson).build()

        return retrofit.create(RestBackEndService::class.java)
    }
}