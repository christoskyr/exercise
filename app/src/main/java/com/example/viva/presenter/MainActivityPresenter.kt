package com.example.viva.presenter

import android.content.Context
import android.widget.Toast
import com.example.viva.view.MainActivityView
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter
import domain.ProductListResponseDomainTransformer
import ext.response.ProductDTO
import network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

/**
 * Main Activity Presenter
 */
class MainActivityPresenter (val context: Context?) : MvpNullObjectBasePresenter<MainActivityView>() {

    companion object {
        private const val fileName = "productsList.txt"
    }

    fun refreshProductsList() {
        view.showLoading(false)
        fetchData()
    }

    fun loadData(pullToRefresh: Boolean) {
        if (fileExists(fileName)) {
            fetchDataFromFile()
        } else {
            view.showLoading(pullToRefresh)
            fetchData()
        }
    }

    private fun fetchData() {
        val call: Call<List<ProductDTO>> = RetrofitClientInstance.service.getProductList()
        call.enqueue(object : Callback<List<ProductDTO>> {

            override fun onResponse(
                call: Call<List<ProductDTO>>?,
                response: Response<List<ProductDTO>>?
            ) {
                view.setData(ProductListResponseDomainTransformer.transform(response?.body()))
                val fstream: FileOutputStream = context!!.openFileOutput(fileName, Context.MODE_PRIVATE)
                val os = ObjectOutputStream(fstream)
                for (item: ProductDTO in response?.body()!!) {
                    os.writeObject(item)
                }
                os.close()
                fstream.close()
                Toast.makeText(context, "Products Saved Successfully", Toast.LENGTH_SHORT).show();
                view.showContent()
            }

            override fun onFailure(call: Call<List<ProductDTO>>?, t: Throwable?) {
                view.showLoading(false)
            }

        })
    }

    private fun fetchDataFromFile() {
        val fInputStream: FileInputStream = context!!.openFileInput(fileName)
        val objectInputStream = ObjectInputStream(fInputStream)
        val list : MutableList<ProductDTO>  = mutableListOf()
        try {
            while (true) {
                list.add(objectInputStream.readObject() as ProductDTO)
            }
        } catch (e: EOFException) {
            // End of stream
        }
        objectInputStream.close()
        fInputStream.close()
        view.setData(ProductListResponseDomainTransformer.transform(list))
        view.showContent()
    }

    private fun fileExists(fileName: String): Boolean {
        val file: File = context!!.getFileStreamPath(fileName)
        return file.exists()
    }


}