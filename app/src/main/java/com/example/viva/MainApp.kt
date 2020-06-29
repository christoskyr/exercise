package com.example.viva

import android.app.Application
import di.components.AppComponent
import di.modules.ContextModule

class MainApp : Application() {

//    private lateinit var appComponent: AppComponent
//    private lateinit var mInstance: MainApp
//
//    override fun onCreate() {
//        super.onCreate()
//        mInstance = this
//        injectDependencies()
//    }
//
//    protected fun injectDependencies() {
//        appComponent = DaggerAppComponent.builder()
//            .contextModule(ContextModule(this))
//            .build()
//    }
//
//    fun getAppComponent(): AppComponent = appComponent

}