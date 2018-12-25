package dsm.flickr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_content.view.*

/**
 * Created by ghdth on 2018-12-20.
 */
class ContentAdapter(var contentList:ArrayList<Content_Item>):RecyclerView.Adapter<ContentAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_content,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindItems(contentList[position])

    }
    override fun getItemCount(): Int {
        Log.d("getItemCount",contentList.size.toString())
        return contentList.size
    }
    inner class CustomViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){

        fun bindItems(content:Content_Item){
            itemView.title.text=content.title
            var image=itemView.image_iv
            Glide.with(itemView).load("https://farm"+content.farm+".staticflickr.com/"+content.server+"/"+content.id+"_"+content.secret+".jpg").into(image)
        }
    }

}

