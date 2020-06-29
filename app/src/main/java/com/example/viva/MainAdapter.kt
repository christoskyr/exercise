package com.example.viva

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viva.event.ProductClickedEvent
import com.squareup.picasso.Picasso
import domain.ProductListDomainResponse
import org.greenrobot.eventbus.EventBus

class MainAdapter (
    val data: ProductListDomainResponse,
    val context: Context?
) :
    RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder =
        MainAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )

    override fun getItemCount() = data.productList.size

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
//        Glide.with(context)
//        .load(data.productList[position].thumbnail)
//        .into(holder.image)
        Picasso.with(context).load(data.productList[position].thumbnail).into(holder.image)
        holder.title.text = data.productList[position].name
        holder.price.text = data.productList[position].price

        holder.cardView.setOnClickListener(View.OnClickListener {
            EventBus.getDefault().post(ProductClickedEvent(data.productList[position]))
        })
    }

    /**
     * MainAdapter ViewHolder
     */
    inner class MainAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val title: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}