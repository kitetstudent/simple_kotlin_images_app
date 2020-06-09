package com.example.wt

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private val myDataSet: ArrayList<Category>, private val listener: OnItemListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view_item,
            parent,
            false
        ) as CardView
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return myDataSet.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myDataSet[position], listener)
    }


    class MyViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        private val imageView = cardView.findViewById<ImageView>(R.id.item_imageview)
        private val textView = cardView.findViewById<TextView>(R.id.item_text_view)

        fun bind(category: Category, listener: OnItemListener) {
            Picasso.get()
                .load("https://your_domain/WT_images/" + category.image + ".jpg")
                .placeholder(R.drawable.a1).into(imageView)
            textView.text = category.title

            cardView.setOnClickListener(View.OnClickListener {
                listener.onClickItem(category)
            })
        }
    }
}

interface OnItemListener {
    fun onClickItem(category: Category)
}