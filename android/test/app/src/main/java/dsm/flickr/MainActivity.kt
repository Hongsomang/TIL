package dsm.flickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.support.v7.widget.RecyclerView
import com.google.gson.JsonArray
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL


class MainActivity : AppCompatActivity() {
    private var categoryList=ArrayList<Category_Item>()
    private var contentList=ArrayList<Content_Item>()
    private var BASE_URL="https://secure.flickr.com/services/rest/?method=flickr.photos.search&" +
            "api_key=2f904f1669187c7860cae324d891ccd3&"
    private var mCompositeDisposable: CompositeDisposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()

        var categoryAdapter=CategoryAdapter(categoryList)
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
        content_rv.adapter=contentAdapter
    }
    private fun lodeJSON( page:Int,category:String){
        val retrofitService= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)

        mCompositeDisposable?.add(retrofitService.getContents(page,category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
    }

}

