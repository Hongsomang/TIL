package dsm.flickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()

        var categoryAdapter=CategoryAdapter(categoryList)

        var category= arrayOf("Apple","Dog","Banana","Cat","Pen","Cap","Soccer","Mango","Korea","Pizza","Chicken","Rose")
        for(cout in category.indices){
            categoryList.add(Category_Item(category.get(cout)))
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


        lodeJSON(METHOD,API_KEY,"cat","2",FORMAT,"1","10")



    }

    private fun seatch_category(position:Int){
        Toast.makeText(this@MainActivity,categoryList[position].Category_name,Toast.LENGTH_SHORT).show()
        lodeJSON(METHOD,API_KEY,categoryList[position].Category_name,"2",FORMAT,"1","10")
        Log.d("categoryList_name",categoryList[position].Category_name)
        try{
            for(count in categoryList.indices){
                if(count==position){
                    category_rv.getChildAt(count).findViewById<TextView>(R.id.category_button).setBackgroundResource(R.drawable.button_select)
                    Log.d("count==position",count.toString())
                }else{
                    category_rv.getChildAt(position).findViewById<TextView>(R.id.category_button).setBackgroundResource(R.drawable.button)
                    Log.d("count==else",count.toString())

                }
            }
        }catch (e: NullPointerException ){
            Log.d("NullPointerException",e.toString())
        }
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
        Log.d("getresponse",res.toString())
        var photos=res.get("photos").asJsonObject
        Log.d("하이루",photos.toString())
        var photo=photos.get("photo").asJsonArray
        Log.d("보이루",photo.toString())
        for(count in 0 until photo.size()){
            var photoList=photo.get(count).asJsonObject

            var id=photoList.get("id").asString
            var secret=photoList.get("secret").asString
            var server=photoList.get("server").asString
            var title=photoList.get("title").asString
            var farm=photoList.get("farm").asString
            contentList.add(Content_Item(farm,server,id,secret,title))
            Log.d("하하하하",contentList.toString())
        }
        setAdapter()
    }
    private fun bindError(error: Throwable){
        Log.d("binderror",error.toString())

    }
    private fun setAdapter(){
        contentAdapter=ContentAdapter(contentList)
        content_rv.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        content_rv.adapter=contentAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}

