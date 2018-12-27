##Flickr api를 이용한 사진 검색 어플리케이션



-flickr api를 이용하여 원하는 category의 사진을 가져오는 어플리케이션이다, category를 총12개로 지정했다.



###1.준비

#### 준비물

 sdk 19이상의 안드로이드 폰, android Studio, flickr api_key



####API_URL(예시)

``` 
https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=2f904f1669187c7860cae324d891ccd3&text=apple&page=2&per_page=10&format=json&nojsoncallback=1
```



####Photo_URL

```
https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
```



####프로젝트에 코틀린 추가하기

코틀린을 사용하기 위해서 ``` Gradle Scripts> build.gradle(Project:test)```에서 아래 내용을 추가해 준다.

```
buildscript{
  dependencies{
            classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
			
  }
}
```

그 다음으로 ``` Gradle Scripts> build.gradle(Module:app)```에서 아래 내용을 추가해 준다

```
apply plugin: 'kotlin-android'
android{
      kapt { generateStubs = true }
}
dependencies{
      implementation"org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
}
```



#### 이 어플리케이션에 필요한 라이브러리 추가

필요한 라이브러리는 ``` Gradle Scripts> build.gradle(Module:app)```에서 추가한다.

```
dependencies{
  //CoordinatorLayout,AppBarLayout 사용할 때 쓰임 -activity_main.xml-
  implementation 'com.android.support:design:27.1.1'
  
  //리스트뷰를 만들 때 사용
  implementation 'com.android.support:recyclerview-v7:27.1.0' //-activity_main.xml-
  implementation 'com.android.support:cardview-v7:27.1.0' //-item_category.xml,item_content.xml-
  
  //api 통신 할 때 retorfit과 rxjava를 사용한다. -RetrofitService.kt, MainActivity.kt-
  implementation 'com.squareup.retrofit2:retrofit:2.4.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
  implementation 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
  implementation 'io.reactivex.rxjava2:rxjava:2.x.x'
  implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
  
  //사진을 어플리케이션에 보여줄 때 사용 -ContentAdapter.kt-
  implementation 'com.github.bumptech.glide:glide:4.2.0'
  kapt 'com.github.bumptech.glide:compiler:4.2.0'
 
}
```



### 2.주요 기능

+ RETROFIT &  RXJAVA  이용한 통신

  ```
  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
  ```

  Retrofit과 함께 RxJava Observable을 사용하려면 새로운 Adapter Factory 구현을 사용해야한다. addCallAdapterFactory () 메서드를 사용하여 수행된다.

  ```
  mCompositeDisposable?.add(retrofitService.getContents(method,api,text,page,format,callback,per_page)
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.io())
              .subscribe(this::bindContent, this::bindError))
  }
  ```

  observeOnd 과 subscribeOn은 스레드를 정의와 결과를 처리하고 네트워크 작업을 수행해야한다.

  또, 결과가 메인스레드에 전달되어 처리되어야 하고, 네트워크 작업는 IO스레드에서 처리되어야된다고 정의했다.  bindContent는 성공했을 때 bindError는 실패 했을 때 실행 된다.

+ 머티리얼 디자인 적용 

  activity_main.xml 형태

  ```
  <CoordinatorLayout>
  	<AppBarLayout>
  		<Toolbar app:layout_scrollFlags="scroll|enterAlways"></Toolbar>
  		<RecyclerView>
  	</AppBarLayout>
  	<NestedScrollView>
  	</NestedScrollView>
  </CoordinatorLayout>
  ```

   ```app:layout_scrollFlags="scroll|enterAlways```를 하여 아래로 스크롤하면 툴바가 사라지고 위로 스크롤하면 다시 툴바가 보인다.

  ​

+ Category 별로 사진 보여주기 

  ```
          
          
  category_rv.addOnItemTouchListener(RecyclerViewClickListener(this, category_rv, object :
  RecyclerViewClickListener.OnItemClickListener {

  	override fun onItemClick(view: View, position: Int) {
  				        		      				        		     lodeJSON(METHOD,API_KEY,categoryList[position].Category_name,"1",FORMAT,"1","10")

  	}
  }))
  ```

  ClickListener 인터페이스를 만들어 리사클러뷰를 클릭할 때 마다 클리한 position을 받아와 ArrayList에서 Position에 맞는 category이름을 불러와 통신한다.

+ 사진보여주기

  ```
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
  ```

   JsonObject형태로 오는데  id, secret, server, farm, title만 가져와 Cotent_item 에 넣는다.

  ```
  Glide.with(itemView).load("https://farm"+content.farm+".staticflickr.com/"+content.server+"/"+content.id+"_"+content.secret+".jpg").into(image)
  ```

  Content_item에 넣는 데이터를 필요한 자리에 넣어준다.

  ​





