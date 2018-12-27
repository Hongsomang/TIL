##Flickr api를 이용한 사진 검색 어플리케이션

-flickr api를 이용하여 원하는 category의 사진을 가져오는 어플리케이션이다, category를 총12개로 이미로 지정해했다.

### 1.준비

준비물: sdk 19이상의 안드로이드 폰, android Studio, flickr api_key

####API_URL(예시)

``` 
https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=2f904f1669187c7860cae324d891ccd3&text=apple&page=2&per_page=10&format=json&nojsoncallback=1
```



####Photo_URL

```
https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
```



####프로젝트에 코틀린 추가하기

코틀린을 사용하기 위해서 ``` Gradle Scripts> build.gradle(Project:test)```에서 아래 내용을 추가해준다.

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









