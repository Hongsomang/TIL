### bundle

+ 여러가지의 타입의 값을 저장하는 Map클래스이다.

+ 한 Activity에서 다른 Acitivity로 전달하고 싶은 데이터가 있을 때 사용

+ 키로 값을 찾아 참소하는 방식

+ 키는 변하지 않는 고정 상수이기 떄문에 보통 static final String으로 선언해 놓고 참조하는 것이다.

  ```
  final Bundle extras = data.getExtras();//선언
  ```

  ```
  Bitmap photo = extras.getParcelable("data");//참조
  ```

