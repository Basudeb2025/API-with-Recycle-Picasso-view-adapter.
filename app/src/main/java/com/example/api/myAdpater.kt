package com.example.api

import android.app.Activity
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class myAdpater(val context:Activity,val productarrayList: List<Product>) : RecyclerView.Adapter<myAdpater.viewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val item = LayoutInflater.from(parent.context).inflate(R.layout.mydraw,parent,false)
        return  viewHolder(item)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val current = productarrayList[position]
        holder.title.text = current.title
        //Picasso
        Picasso.get().load(current.thumbnail).into(holder.image)
    }

    override fun getItemCount(): Int {
       return productarrayList.size
    }
    class viewHolder (val itemview:View):RecyclerView.ViewHolder(itemview){
        val title : TextView
        val image: ImageView
        init {
            title = itemview.findViewById(R.id.tex)
            image = itemview.findViewById(R.id.img)
        }
    }


}