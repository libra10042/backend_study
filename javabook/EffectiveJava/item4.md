# 인스턴스화를 막으려거든 PRIVATE 생성자를 사용하라. 

추상 클래스로 인스턴스화를 막을 수 없다.

<br>

인스턴스화를 막는 방법은 간단한데, private 생성자를 추가하면 클래스의 인스턴스화를 막을 수 있다.

##### 인스턴스를 만들 수 없는 유틸리티 클래스
 
```java
public class UtilityClass {

    // 기본 생성자가 만들어지는 것을 막는다. ( 인스턴스화 방지용 ) 
    private UtilityClass(){
        throw new AssertionError(); 
    }
}
```
