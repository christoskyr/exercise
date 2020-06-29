package network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  The Retrofit Client Instance
 */
object RetrofitClientInstance {

    private const val BASE_URL = "https://vivawallet.free.beeceptor.com/v1/api/"
    val service: RestBackEndService
            get() {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                return retrofit.create(RestBackEndService::class.java)
            }
}