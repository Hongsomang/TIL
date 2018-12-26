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
    private var lastPage=false
    private var count = 1

    private lateinit var clickCategory:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()

        var categoryAdapter=CategoryAdapter(categoryList)

        var category= arrayOf("Apple","Dog","Banana","Cat","bag","doll","Soccer","chocolate","Korea","Pizza","Chicken","flower")
        for(count in category.indices){
            categoryList.add(Category_Item(category.get(count)))
        }

        category_rv.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        category_rv.adapter=categoryAdapter

        category_rv.addOnItemTouchListener(RecyclerViewClickListener(this, category_rv, object :
                RecyclerViewClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                //setTabLayoutEdge(position)
                seatch_category(position)
                contentList.clear()
                contentAdapter.notifyDataSetChanged()
                Log.d("click","dfdf")
            }

            override fun onLongItemClick(view: View?, position: Int) {

            }
        }))


        Log.d("onCreate::","dfdfsdfsdfsdfsdfsdfsdfsdfsdfdsf")
        lodeJSON(METHOD,API_KEY,"Apple","1",FORMAT,"1","10")
        setScollListener("Apple")


    }
   /* private fun getMoreItems(){
        count+=1
        contentAdapter.addData(contentList)
      //  lodeJSON(METHOD,API_KEY,"Apple",count.toString(),FORMAT,"1"/*"10"*/)

    }*/
    private fun seatch_category(position:Int){
        category_tv.text=categoryList[position].Category_name
        Toast.makeText(this@MainActivity,categoryList[position].Category_name,Toast.LENGTH_SHORT).show()
        lodeJSON(METHOD,API_KEY,categoryList[position].Category_name,"1",FORMAT,"1","10")
        Log.d("categoryList_name",categoryList[position].Category_name)
        setScollListener(categoryList[position].Category_name)
    }

    private fun lodeJSON(method:String,api:String,text:String,page:String,format:String,callback:String,per_page:String){
        Log.d("lodeJSON","dfsfsdfsdfsdfsdf")
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
        //contentAdapter.addAll(contentList)
      /*  content_rv.addOnScrollListener(object :PaginationScrollListener(layoutManager = LinearLayoutManager(this)){
            override fun isLastPage(): Boolean {
                return lastPage
            }

            override fun isLoading(): Boolean {
                return loading
            }

            override fun loadMoreItems() {
                loading=true
                getMoreItems()
            }

        })*/
    }

    private fun bindError(error: Throwable){
        Log.d("binderror",error.toString())

    }

    private fun setAdapter(){
        Log.d("setAdapter","dfsfsdfsdfsdfsdf")
        contentAdapter= ContentAdapter(contentList)
        //contentAdapter.setLinearLayoutManager(LinearLayoutManager(this))
        //contentAdapter.setRecyclerView(content_rv)
        content_rv.layoutManager=linearLayoutManager

        content_rv.adapter=contentAdapter
        content_rv.setNestedScrollingEnabled(false);

        if(contentList.size<100){
            loading=false
        } else{
            loading=true
            Log.d("100넘음","ㄹㄴ알알ㄴ러ㅏ")
            Toast.makeText(this@MainActivity,"피드를 받을 수 없습니다.",Toast.LENGTH_LONG).show()
        }


    }
    private fun setScollListener(category:String){
        content_rv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("scroll111111111111111","gkgkgkgkgkgkggkggkgk")
                val visibleItemCount=linearLayoutManager.childCount
                val totalItemCount=linearLayoutManager.itemCount
                val firstVisible=linearLayoutManager.findFirstVisibleItemPosition()

                Log.d("onScrollListener",visibleItemCount.toString()+" "+totalItemCount.toString()+" "+firstVisible.toString())
                if(!loading&&(visibleItemCount + firstVisible) >= totalItemCount){

                       loading=true
                       count+=1
                       Log.d("ifiifififififi","dfsfosfiwjfoewjfwoifj")
                       lodeJSON(METHOD,API_KEY,category,count.toString(),FORMAT,"1","10")



                }else{
                    Log.d("else:;;;:;","dfdfsdfsfsdf")
                }
            }
        })
    }

    override fun onDestroy() {
        Log.d("onDestory","dfsfsdfsdfsdfsdf")

        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}

