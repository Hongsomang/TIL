package dsm.flickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_category.*
import kotlinx.android.synthetic.main.item_category.view.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL


class MainActivity : AppCompatActivity() {


    private var categoryList=ArrayList<Category_Item>()
   private var contentList=ArrayList<Content_Item>()

    private val SEARCH_URL = "https://api.flickr.com/services/"
    private val API_KEY = "2f904f1669187c7860cae324d891ccd3"
    private val METHOD = "flickr.photos.search"
    private val FORMAT = "json"

    private lateinit var contentAdapter:ContentAdapter
    private val linearLayoutManager = LinearLayoutManager(this)
    private var mCompositeDisposable: CompositeDisposable? = null

    private var loading = false
    private var count = 1
    private var position_before=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mCompositeDisposable = CompositeDisposable()


        var category= arrayOf("Apple","Dog","Banana","Cat","bag","doll","Soccer","chocolate","Korea","Pizza","Chicken","flower")
        for(count in category.indices){
            categoryList.add(Category_Item(category.get(count)))
        }

        var categoryAdapter=CategoryAdapter(categoryList)
        category_rv.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        category_rv.adapter=categoryAdapter

        category_rv.addOnItemTouchListener(RecyclerViewClickListener(this, category_rv, object :
                RecyclerViewClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                category_rv.findViewHolderForAdapterPosition(position_before).itemView.category_iv.setVisibility(View.GONE)
                category_rv.findViewHolderForAdapterPosition(position).itemView.category_iv.setVisibility(View.VISIBLE)
                position_before=position
                seatch_category(position)
                contentList.clear()
                contentAdapter.notifyDataSetChanged()
            }

            override fun onLongItemClick(view: View?, position: Int) {

            }
        }))


        lodeJSON(METHOD,API_KEY,"Apple","1",FORMAT,"1","10")

        setScollListener("Apple")


    }

    private fun seatch_category(position:Int){
        category_tv.text=categoryList[position].Category_name
        lodeJSON(METHOD,API_KEY,categoryList[position].Category_name,"1",FORMAT,"1","10")

        setScollListener(categoryList[position].Category_name)
    }

    private fun lodeJSON(method:String,api:String,text:String,page:String,format:String,callback:String,per_page:String){
        val gson=GsonBuilder()
                .setLenient()
                .create()

        val retrofitService= Retrofit.Builder()
                .baseUrl(SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RetrofitService::class.java)

        mCompositeDisposable?.add(retrofitService.getContents(method,api,text,page,format,callback,per_page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::bindContent, this::bindError))
    }

    private fun bindContent(res:JsonObject){
        Log.d("res:::",res.toString())
        var photos=res.get("photos").asJsonObject
        var photo=photos.get("photo").asJsonArray

        for(count in 0 until photo.size()){
            var photoList=photo.get(count).asJsonObject

            var id=photoList.get("id").asString
            var secret=photoList.get("secret").asString
            var server=photoList.get("server").asString
            var title=photoList.get("title").asString
            var farm=photoList.get("farm").asString

            contentList.add(Content_Item(farm,server,id,secret,title))
        }
        setAdapter()

    }

    private fun bindError(error: Throwable){
        Toast.makeText(this,"사진을 받아 올 수 없습니다.",Toast.LENGTH_LONG).show()
        Log.d("binderror",error.toString())

    }

    private fun setAdapter(){
        contentAdapter= ContentAdapter(contentList)
        content_rv.layoutManager=linearLayoutManager
        content_rv.adapter=contentAdapter
        content_rv.setNestedScrollingEnabled(false);

        if(contentList.size<100){
            loading=false
        } else{
            loading=true
            Toast.makeText(this@MainActivity,"사진을 100개 이상 받을 수 없습니다.",Toast.LENGTH_LONG).show()
        }


    }
    private fun setScollListener(category:String){
        content_rv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount=linearLayoutManager.childCount
                val totalItemCount=linearLayoutManager.itemCount
                val firstVisible=linearLayoutManager.findFirstVisibleItemPosition()

                if(!loading&&(visibleItemCount + firstVisible) >= totalItemCount){

                       loading=true
                       count+=1
                       lodeJSON(METHOD,API_KEY,category,count.toString(),FORMAT,"1","10")
                }
            }
        })
    }

    override fun onDestroy() {

        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}

