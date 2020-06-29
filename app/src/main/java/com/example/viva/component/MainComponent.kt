package com.example.viva.component

import com.example.viva.MainActivityFragment
import com.example.viva.presenter.MainActivityPresenter
import dagger.Component
import di.modules.ApiModule
import di.modules.ContextModule
import di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, ContextModule::class, NetworkModule::class]
)
interface MainComponent {

    fun inject(fragment: MainActivityFragment)

    fun presenter() : MainActivityPresenter
}