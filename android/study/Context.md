## Context

### 1.Context란?

​	:Application 환경에 대한 전역 정보를 접근하기 위한 인터페이스

​	1.어플리케이션에 관하여 시스템이 관리하고 있는 정보에 접근하기 

​		ex) Context 인터페이스가 제공하는 API 중 getPackageName(), getResource()

​		     등의 메서드 

​	2.안드로이드 시스템 서비스에서 제공하는 API를 호출 할 수 있는 기능

​		ex) startActivity(), bindService()

### 2.Context가 필요한 이유

+ 전역적인 어플리케이션 정보에 접근할 때와 어플리케이션 연관된 스스템 기능을 수행하기 위해 android에서는 Context라는 인스턴스화된 매개체를 통해야만 유사한 일들을 수행 할 수 있다.
+ 예를 들어 어플리케이

### 3. Context 생성

+ Context는 어플리케이션이 시작될 때 물론, 어플리케이션 컴포너트들이 생성될 때 마다 생성된다.
+ 새롭게 생성되는 Context들은 부모와 완전히 독립되어 있는 존재가 아니고 '거의' 비슷한 내용을 담고 있다.

### 4.Context 종류

+ Application Context

  :애플리케이션 자체의 생명 주기에 영향을 받는다.

   따라서 항상 애플리케이션의 생명 주기와 함께한다.

  + application life-cycle에 접목되는 개념
  + 하나의 에플리케이션이 실행되어 종료될 때까지 동일한 객체

+ Activity Context

  :Activity에 대한 환경 정보들이 Context에 있고 ,이 Context에서 Intent를 동해 다른 액티비티를 띄우게 된다.

  + activity life-cycle에 접목되는 개념
  + 액티비티가 onDestroy()된 경우 사라질 수 있는 객체

###5.Context 참조

+ LoginActivity.this

  :Activity를 상속받는 자신의 클래스를 참조하지만 Activity 또한 Context class를 상속받으므로 activity Context를 제공하는데 사용될 수 있다.

+ getApplication()

  :Application 객체를 참조하지만 Application 클래스는 Context 클래스를 상속받으므로  application context를 제공하는데 사용될 수 있다.

+ getApplicationContext()

  :application context를 제공한다.

+ getBaseContext()

  : activity context를 제공한다.

