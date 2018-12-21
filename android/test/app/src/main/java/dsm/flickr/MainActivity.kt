package dsm.flickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
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
    private var mCompositeDisposable: CompositeDisposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()

       /* var categoryAdapter=CategoryAdapter(categoryList)
        var contentAdapter=ContentAdapter(contentList)
        /*categoryList.add(Category_Item("apple"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))*/


        category_rv.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        category_rv.adapter=categoryAdapter

        /*contentList.add(Content_Item("tilte1", "https://farm5.staticflickr.com/4807/45666331414_e88aa8ac78.jpg"))
        contentList.add(Content_Item("tilte2", "https://farm5.staticflickr.com/4807/45666331414_e88aa8ac78.jpg"))
        contentList.add(Content_Item("tilte3", "https://farm5.staticflickr.com/4807/45666331414_e88aa8ac78.jpg"))
        contentList.add(Content_Item("tilte4", "https://farm5.staticflickr.com/4807/45666331414_e88aa8ac78.jpg"))
        contentList.add(Content_Item("tilte5", "https://farm5.staticflickr.com/4807/45666331414_e88aa8ac78.jpg"))*/

        content_rv.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        content_rv.adapter=contentAdapter*/
        lodeJSON(METHOD,API_KEY,"cat","2",FORMAT,"1")
    }
    private fun lodeJSON(method:String,api:String,tags:String,perPage:String,format:String,callback:String){
        val gson=GsonBuilder()
                .setLenient()
                .create()
        val retrofitService= Retrofit.Builder()
                .baseUrl(SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RetrofitService::class.java)

        mCompositeDisposable?.add(retrofitService.getContents(method,api,tags,perPage,format,callback)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::bindContent, this::bindError))
        Log.d("실행","ㅇㄹㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄹㅇㄴㄹ")
    }
    private fun bindContent(res:JsonObject){
        Log.d("getresponse",res.toString())

    }
    private fun bindError(error: Throwable){
        Log.d("binderror",error.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy","dfdfdfsdddfsdfsdf")
        mCompositeDisposable?.clear()
    }
}

