# MVP

###1. MVP란?

​	: MVP(Model View Presenter)는 사용자 인터페이스를 구축할 때 이용하는 설계 기법이다.

​	  구현에 따라 모델과 뷰와 프레젠터라는 세 가지 역할로 처리를 나눌 수 있다.

​	모델

​	-데이터와 비즈니즈 로직이 들어 있음(UI에 관한 로직x)

​	-데이터베이스나 API접근에 관한 처리가 들어감

​	뷰

​	-데이터를 표시

​	-액션은 뷰에서 처리하지 않고 프제젠터로 넘김

​	프레젠터

​	-모델과 뷰 사이에서 서로 통신 함

​	-뷰에서 이벤트 발생-> 프레전터는 이벤트 대응 처리

​	-인스턴스를 프레젠터로부터 직접 참조 하지 않게 함 인터페이스를 통해 접근 

​	=>테스트 시에 목 객체로 대체할 수 있어 테스트 쉬워짐

+ ###MVP설계의 장점

  -모델, 뷰, 프레젠터로 역할을 명확히 나눔-> 어느 처리 내용이 어디에 있는지 명확, 관리 효율 상승

  -뷰와 모델 사이에 프레젠터가 들어감->뷰와 모델의 의존관계가 없어짐

+ ### MVP설계의 단점

  -프레전터는 인터페이스를 통해 뷰와 모델에 접근하므로 그것들의 위치를 인터베이스로서 정의 할 필요가 있음-> 코드가 길어지기 쉬움 

  -모델에서 가져온 데이터를 뷰에 표시하는 것을 개발자가 집접 구현해야 됨

  -안드로이드에서는 기본적을 MVP패턴을 지원하는 프레임워크가 없음-> 어떻게 UI로직을 프레젠터로 분리하는가 하는 설계상의 난이도가 높다

  ​	