# Private 생성자나 열거 타입으로 싱글턴임을 보증하라.



## 싱글턴이란?

: 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다.


### 싱글턴을 만드는 3가지 방식. 

##### 1. public static final 필드 방식의 싱글턴

```java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {... }
  
    public void leaveTheBuilder() { ... }
  
}
```
 : private 생설자는 public static final 필드인 Elvis.INSTANCE를 초기화할 때 딱 한번만 호출된다.
 
 > public 필드의 가장 큰 장점은 해당 클래스가 싱근턴임이 API에 명백히 들어난다는 것이다. public static 필드가 final이니 절대로 다른 객체를 참조할 수 없다.
 
 
<br>

<br>
 
##### 2. 정적 팩터리 방식의 싱글턴 


```java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { ... }
    public static Elvis getInstance() { return INSTANCE; }
  
    public void leaveTheBuilder() { ... }
  
}
  
```

> API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다는 점이다.유일한 인스턴스를 반환하던 팩터리 메서드가 호출하는 스레드별로 다른 인스턴스를 넘겨주게 할 수 있다. <br>
> 제네릭 싱글턴 팩토리로 만들 수 있다. <br>
> 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용할 수 있다. 

<br>

 public static final 필드 방식의 싱글턴, 정적 팩토리 방식의 싱글턴을 직렬화를 하려면 단순히 Serializable을 구현한다고 선언하는 것만으로는 부족하다. 
 (모든 인스턴스 필드를 일시적(transient)이라고 선언하고 readResolve 메서드를 제공해야 한다.) 


<br>

<br>

##### 3. 열거 타입 방식의 싱글턴 - 바랍직한 방식. 

```java
public enum Elvis {
  INSTANCE; 
  
  public void leaveTheBuilding() {... }
  
}

```

> public 방식과 비슷하지만, 더 간결하고 추가 노력 없이 직렬화 할 수 있ㄷ.ㅏ

" 대부분의 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다."
