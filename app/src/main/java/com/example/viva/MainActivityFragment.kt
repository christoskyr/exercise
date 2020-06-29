package com.example.viva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viva.presenter.MainActivityPresenter
import com.example.viva.view.MainActivityView
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState
import domain.ProductListDomainResponse

/**
 * Main Activity Fragment
 */
class MainActivityFragment:
    MvpLceViewStateFragment<LinearLayout, ProductListDomainResponse, MainActivityView, MainActivityPresenter>(), MainActivityView {

    /**
     * Views
     */
    private lateinit var headerText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshButton: ImageView

    private lateinit var mData: ProductListDomainResponse
    private lateinit var mAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        headerText = view.findViewById(R.id.headerText)
        recyclerView = view.findViewById(R.id.recyclerView)
        refreshButton = view.findViewById(R.id.refreshButton)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isRetainInstance = true
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadData(pullToRefresh)
    }

    override fun createPresenter(): MainActivityPresenter {
        return MainActivityPresenter(context)
    }

    override fun setData(data: ProductListDomainResponse?) {
        this.mData = data!!
        initUI()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return e?.message!!
    }

    override fun createViewState(): LceViewState<ProductListDomainResponse, MainActivityView> = RetainingLceViewState()

    override fun getViewState(): RetainingLceViewState<ProductListDomainResponse, MainActivityView>? {
        @Suppress("UNCHECKED_CAST")
        return super.getViewState() as? RetainingLceViewState<ProductListDomainResponse, MainActivityView>?
    }

    override fun getData(): ProductListDomainResponse {
       return this.mData
    }

    fun initUI(){
        headerText.text = context?.resources?.getString(R.string.info_header_text)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = ProductListAdapter(data.productList)
        recyclerView.adapter = mAdapter
        refreshButton.setOnClickListener {
            refreshProductsList()
        }
    }

    private fun refreshProductsList() {
        presenter.refreshProductsList()
    }

}