package com.example.viva.presenter

import com.example.viva.view.MainActivityView
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter
import domain.ProductListResponseDomainTransformer
import ext.response.ProductDTO
import network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter : MvpNullObjectBasePresenter<MainActivityView>() {

    fun loadData(pullToRefresh: Boolean) {
        view.showLoading(pullToRefresh)
        fetchData()
    }

    private fun fetchData() {
        val call: Call<List<ProductDTO>> = RetrofitClientInstance.service.getProductList()
        call.enqueue(object : Callback<List<ProductDTO>> {

            override fun onResponse(
                call: Call<List<ProductDTO>>?,
                response: Response<List<ProductDTO>>?
            ) {
                view.setData(ProductListResponseDomainTransformer.transform(response?.body()))
                view.showContent()
            }

            override fun onFailure(call: Call<List<ProductDTO>>?, t: Throwable?) {
                //progerssProgressDialog.dismiss()
            }

        })
    }
}