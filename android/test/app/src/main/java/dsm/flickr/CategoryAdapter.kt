package dsm.flickr

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * Created by ghdth on 2018-12-20.
 */
class CategoryAdapter(var categoryList:ArrayList<Category_Item>): RecyclerView.Adapter<CategoryAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindItems(categoryList[position])

        if(position==0){
            holder.itemView.category_iv.setVisibility(View.VISIBLE)
        }else{
            holder.itemView.category_iv.setVisibility(View.GONE)

        }

    }


    override fun getItemCount(): Int {return categoryList.size}


    inner class CustomViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){
        fun bindItems(category:Category_Item){
            itemView.category_button.text=category.Category_name;
        }
    }



}