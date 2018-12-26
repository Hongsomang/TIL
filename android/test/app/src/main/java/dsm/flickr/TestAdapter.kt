package dsm.flickr

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_content.view.*

/**
 * Created by ghdth on 2018-12-25.
 */
class TestAdapter(var onLoadMoreList: OnLoadMoreList): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private val contentList: ArrayList<Content_Item?>

    private var isMoreLoading=true

    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0

    init {
        contentList = ArrayList()
    }
    override fun getItemCount(): Int {
        Log.d("getItemCount()","dfsfsdfsdfsdfsdf")

        return contentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("onCreateViewHolder","dfsfsdfsdfsdfsdf")

        if(viewType==VIEW_ITEM){
            val view=LayoutInflater.from(parent.context).inflate(R.layout.item_content,parent,false)
            return ContentViewHolder(view)
        }
        else{
            return ProgressViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("onBindViewHolder","dfsfsdfsdfsdfsdf")

        if(holder is ContentViewHolder){
            holder.bindItems(contentList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("getItemViewType","dfsfsdfsdfsdfsdf")

        return if (contentList[position] != null) VIEW_ITEM else VIEW_PROG
    }

    interface OnLoadMoreList{
        fun onLoadMore()
    }
    fun setLinearLayoutManager(linearLayoutManager: LinearLayoutManager) {
        Log.d("setLinearLayoutManager","dfsfsdfsdfsdfsdf")

        this.mLinearLayoutManager = linearLayoutManager
    }

    fun setMoreLoading(loading:Boolean){
        Log.d("setMoreLoading","dfsfsdfsdfsdfsdf")

        isMoreLoading=loading
    }
    fun setRecyclerView(view:RecyclerView){
        Log.d("setRecyclerview","dfsfsdfsdfsdfsdf")

        view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount= mLinearLayoutManager!!.childCount
                val totalItemCount=mLinearLayoutManager!!.itemCount
                val firstVisible=mLinearLayoutManager!!.findFirstVisibleItemPosition()

                Log.d("total", totalItemCount.toString() + "")
                Log.d("visible", visibleItemCount.toString() + "")
                Log.d("first", firstVisible.toString() + "")

                if (!isMoreLoading && totalItemCount - visibleItemCount <= firstVisible + 1) {
                    if (onLoadMoreList != null) {
                        onLoadMoreList.onLoadMore()
                    }
                    isMoreLoading = true
                }



            }

        })
    }

    fun setProgressMore(isProgress: Boolean) {
        Log.d("setProgressMore","dfsfsdfsdfsdfsdf")

        if(isProgress){
            Handler().post({
                contentList.add(null)
                notifyItemInserted(contentList.size-1)
            })
        }
        else{
            contentList.removeAt(contentList.size-1)
            notifyItemRemoved(contentList.size)
        }
    }

    fun addAll(lst: List<Content_Item>) {
        Log.d("addAll","dfsfsdfsdfsdfsdf")

        contentList.clear()
        contentList.addAll(lst)
        notifyDataSetChanged()
    }

    fun addItemMore(lst: List<Content_Item>) {
        Log.d("addItemMore","dfsfsdfsdfsdfsdf")

        contentList.addAll(lst)
        notifyItemRangeChanged(0, contentList.size)
    }

    inner class ContentViewHolder(view:View) : RecyclerView.ViewHolder (view){

        fun bindItems(content: Content_Item?){
            Log.d("bindItems","dfsfsdfsdfsdfsdf")

            itemView.title.text=content?.title
            var image=itemView.image_iv
            Glide.with(itemView).load("https://farm"+content?.farm+".staticflickr.com/"+content?.server+"/"+content?.id+"_"+content?.secret+".jpg").into(image)
        }
    }
    inner class ProgressViewHolder(view: ViewGroup):RecyclerView.ViewHolder(
            LayoutInflater.from(view.context).inflate(R.layout.item_progress,view,false)
    )

}