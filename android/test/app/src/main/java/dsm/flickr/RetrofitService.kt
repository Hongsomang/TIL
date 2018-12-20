package dsm.flickr

import com.google.gson.JsonArray
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
 * Created by ghdth on 2018-12-20.
 */
interface RetrofitService{
    @GET("&per_page={page}&format=json&tags={category}")
    fun getContents(
            @Path("page")page:Int,
            @Path("category")category:String
    ):Observable<JsonArray>

}