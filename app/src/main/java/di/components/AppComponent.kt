package di.components

import dagger.Component
import di.modules.ApiModule
import di.modules.ContextModule
import di.modules.NetworkModule
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component (
    modules = [ApiModule::class, ContextModule::class, NetworkModule::class]
)
interface AppComponent {

    fun okHttpClient(): OkHttpClient
}