## Intent

### 1. Intent란?

​	:Intent는 일종의 메시지 객체 

​	 이것을 사용해 다른 앱 구성요소로부터 작업을 요청할 수 잇음



### 2.여러 구성 요소 사이의 통신을 용이하게 하는 방법

+ 액티비티 시작

  -Activity의 새 인스턴스를 시작하려면 Intent를 startActivity()로 전달하면 됩니다. Intent는 시작할 액티비티를 설명하고 모든 필수 데이터를 담고 있다. 

  -액티비티를 완료되었을 때 결고 수신 하려면 startActivityForResult()를 호출한다.

+ 서비스 시작

  -서비스를 시작하여 일회성 작업을 수행하도록 하려면 Intent를 startServiece()에 

   전달하면 된다. **Intent는 시작할 서비스를 설명하고 모든 필수 데이터를 담고 있다.**

  ​

+ 브로드캐스트 전달

  -다른 여러 앱에 브로캐스트를 전달하려면 Intent를 sendBroadcast(), sendOrderedBroadcast() 또는 sendStickyBroadcast()에 전달하면 된다.

  ​


### 3.인텐트 유형

+ **명시적 인텐트**는 일반적으로 본인의 앱 안에서 구성요소를 시작 할  때 쓴다.

  시작하고자 하는 엑티비티 또는 서비스의 ㅋ클래스 이름을 알고 있기 때문이다

  ex)새 액티비티 호출, 백그라운드 파일 다운로드하기위해 서비를 시작하는 것

  명시적 인텐트 예제

  ```
  Intent intent=new Intent(getApplication(),Activity1.class);
  startActivity(intent);
  ```

+ **암시적 인텐트**는 수행할 일반적인 작업을 선언하여 또 다른 앱의 구성요소가 이를 처리 할 수 있도록 해준다.

  암지적 인텐트 예제

  ```
  //암시적 인텐트 목적에 맞는 호출:지도보기, 연락처보기,인터넷,sns 공유등등
  Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com/));
  startActivity(intent);
  ```

  ​