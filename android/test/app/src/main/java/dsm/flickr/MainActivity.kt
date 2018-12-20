package dsm.flickr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var categoryList=ArrayList<Category_Item>()
    private var ContentList=ArrayList<Content_Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        categoryList.add(Category_Item("apple"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        categoryList.add(Category_Item("cat"))
        var categoryAdapter=CategoryAdapter(categoryList)

        category_rv.layoutManager=LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        category_rv.adapter=categoryAdapter
    }
}
