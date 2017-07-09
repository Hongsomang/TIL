## ViewFlipper 사용하여 화면 슬라이드 구현(java)

#### 1.ViewFlipperAtion.java

+ 인터페이스 View.OnTouchListener 사용

+ 탭 클릭시 이미지 변경하기 위한 인터페이스 선언

      public interface ViewFlipperCallback{
         void onFlipperActionCallback(int position);
      }

+ 화면 전환할 때 애니메이셔 효과를 넣기 위해 SetInAnimation(), setOutAnimation()메소드 사용

##### + MotionEvent 클래스

+ 안드로이드 시스템에서 터치에 관한 각종 행위를 정의하기 위해 제공하는 클래스
+ 이벤트가 발생했을 때 이벤트 처리 메소드를 매개 변수 형식으로 전달
+ 이벤트 처리 방법 : 콜백 메소드 or 터치 이벤트 리스너
+ ACTION_DOWN:무언가 스크린을 눌렀음을 나타내는 함수
+ ACTION_MOVE:무언가가 스크린에서 이동되고 있음을 나타내는 함수
+ ACTION_UP:무언가가 스크린에서 떼어졌음을 나타내는 함수 

#### TutoMainActivity.java

+ ViewFlipperAtion에서 선언한 인터페이스 viewFlipperCallback 사용

  ```public class TutoMainActivity extends AppComatActivity implements ViewFlipperAction.ViewFlipperCallback{} ```

+ 리스너설정-좌우로 터치시 화면 넘어가기

  ```flipper.setOnTouchListener(new ViewFlipperAction(this, flipper));```

+ 이미지 바꾸기- setImageResource()

  ​



