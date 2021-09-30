# 생성자 대신 정적 팩토리 메서드를 고려하라. 


정적(static), 팩토리(factory), 메서드(method) 

정적 팩토리 메서드란 객체 생성의 역할을 하는 클래스 메서드라는 의미이다.

<br>

### 정적 팩터리 메서드에 흔히 사용하는 명명 방식
- from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환. 
- of : 여러개의 매개 변수를 받아서 객체를 생성. 
- ValueOf : from과 of의 더 자세한 버전. 
- instance | getInstange : 인스턴스를 반환하지만 이전에 반환했던 것과 다를 수 있음. 
- create | newInstance : 새로운 인스턴스 생성. 
- getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다.
- newType : newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다.
- type : getType과 newType의 간결한 버전. 
