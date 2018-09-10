### Android Activity LifeCycle

#### 0.들어가기 전에..

+ Activity: 안드로이드 어플리케이션의 기본 단위로, 사용자 인터페이스를 화면에 표시하고 사용자의 입력을 받아서 처리하는역할
+ 생명주기:Activity는 시작, 실행,활성,비활성화,정지,종료되는 일련의 상태를 순환

#### 1.액티비티 생명주기

![image](https://raw.githubusercontent.com/Hongsomang/TIL/master/android/study/image/activitylifecycle.PNG)

+ **첫 번째 activity가 실행되었을 때** :onCreate()-> onStart()->onResume()순으로 실행
+ **두 번째 activity가 실행되었을 때**: 두번 째 activity에서 onCreate()-> onStart()->onResume()순으로 실행, 첫 번째 엑티비티에서는 onPause()->onStop()순으로 실행(onStop()은 두 번째 activity가 onResume()까지 실행되고 나서 실행된다.)
+ **두 번째 activity에서 Back key를 눌렀을 경우**:  두 번째 activity에서  onPause()를 실행하고, 첫 번째 activity에서  onRestart()->onStart()->onReume()순서로 실행되고, 두번 째activity에서 onStop()->onDestroy()순서로 Activity가 종료된다.



#### 2.함수 설명

activity가 실행될 때

+ onCreate()

  **Activity가 처음 만들어질 때 호출**되는 함수이면서 activity를 초기화 한다.

  중지했다 재 시작하는 경우라면  activity의 이전 상태 정보인 bundle이 전달 된다. 이정보대로 재초기화 한다.

+ onStart()

  **activity가 사용자에게 보이기 직전에 호출**되는 함수이다.

+ onResume()

  **사용자와 상호작용을 하기 직전에 호출**되는 함수이다. 이 단계에서 스택의 제일 위로 올라와 activity 스택의 맨 위에 있어 activity가 사용자에게 보여진다.

+ onRestart()

  **재 시작될 때 호출**되는 함수 즉,stopped상태였을 때 다시 호출되어 시작될 때 불린다.	

  ​

다른 activity가 실행될 때

+ onPause()

  **다른 activity가 실행 될 때 호출**하는 함수이다.  다른 activity가 실행되기 전에 실행 되기 때문에 시간이 많이 소요되는 작업이나, 많은 일을 처리하면 Activity가 호출되는시간이 지연되기 때문에 많은 일을 처리하지 않도록 주의해야한다.

activity종료할 때

+ onStop()

  **완전히 화면을 벗어날 때 호출되는 함수**이다. 

  홈키를 누르거나 또 다른 activity로 이동하는 경우 호출이 되고, 만약 이 상태에서 Activity가 다시 불려지면 onRestart()함수가 실행됨


+ onDestroy()

  **엑티비티가 완전히 종료 될 때 호출**되는 함수이다.

  ​

출처

http://limkydev.tistory.com/32

http://gpark.tistory.com/7

http://apponline.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9CAndroid-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Activity-%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0	-