package dsm.flickr

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.reactivex.Observable
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
 * Created by ghdth on 2018-12-20.
 */
interface RetrofitService{
    @GET("rest/?")
    fun getContents(
            @Query("method") method:String,
            @Query("api_key")apiKey:String,
            @Query("tags")  tags:String,
            @Query("per_page")perPage:String,
            @Query("format")  format:String,
           @Query("nojsoncallback")call_back:String

            /* @Path("page")page:Int,
             @Path("category")category:String*/
    ):Observable<JsonObject>

}