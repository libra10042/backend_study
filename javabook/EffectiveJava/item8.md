# finalizer, 와 cleaner 사용을 피하라.

: 자바는 두 가지 객체 소멸자를 제공한다. 그중 finailzer는 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요하다. <br>

finailzer는 나름의 쓰임새가 몇가지 있지만 기본적으로 쓰지말아야 한다.

<br>

그래서 자바 9에서는 finailzer를 사용 자제 API로 지정하고 cleaner를 그 대안으로 소개핬다. cleaner는 finailizer보다는 덜 위험하지만, 예측할 수 없고, 느리고, 일반적으로 불필요하다.


<br>

<br>

자바의 finalizer와 cleaner는 C++의 파괴자(destructor)와는 다른 개념이다. 

- 자바에서는 접근할 수 없게 된 객체를 회수하는 역할을 가비지 컬렉터가 담당하고, 프로그래머에게는 아무런 작업도 요구하지 않는다. <br>
- c++의 파괴자는 비메모리 자원을 회수하는 용도로도 쓰인다.하지만 자바에서는 try-with-resources와 try-finally 를 사용해 해결한다.


> finalizer와 cleaner는 즉시 수행된다는 보장이 없다. 즉, finalizer와 cleaner로는 제때 실행되어야 하는 작업은 절대 할 수 없다.


<br>

<br>

----

finalizer나 cleaner를 얼마나 신속하게 수행할지는 전적으로 가비지 컬렉터 알고리즘에 달렸으며, 이는 컬렉터 구현마다 천차만별이다. finalizer나 cleaner 수행 시점에 의존
하는 프로그램의 동작또한 마찬가지다.

<br>

또한 프로그램 생애주기에 의존해서는 안된다. 상태를 영구적으로 수정하는 작업에서는 절대 finializer나 cleaner에 의존해서는 안된다. <br>

예를 들어 DB 같은 공유 자원의 영구 락(lock) 해제를 finalizer나 cleaner 에 맡겨 놓으면 분산 시스템 전체가 서서히 멈출 것이다.



> System.runFinalizersOnExit와 그 쌍둥이인 Runtime.runFinalizersOnExit 두 메서드는 심각한 결함 때문에 수십 년간 지탄 받아 왔다. 



그리고 finalizer와 cleaner는 심각한 성능 문제도 동반한다. finalizer를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬 수도 있다.

<br>

##### * 객체 생성을 막으려면 생성자에서 예외를 던지는 것만으로 충분하지만, finalizer가 있다면 그렇지도 않다.


##### * final 이 아닌 클래스 finalizer 공격으로부터 방어하려면 아무 일도 하지 않는 finalize 메서드를 만들고 final로 선언하자.

<br><br><br>

----
## finalizer나 cleaner를 대신해줄 묘안은 무엇일까? 

- AutoCloseable을 구현해주고, 클라이언트에서 인스턴스를 다 쓰고 나면 close 메서드를 호출하면 된다.

- 구체적인 구현법과 관련하여 알아두면 좋을 게 하나 있다.각 인스턴스는 자신이 닫혔는지를 추적하는 것이 좋다. 
- 각 인스턴스는 자신이 닫혔는지를 추적하는 것이 좋다. 다시 말해 close 메서드에서 이 객체는 더 이상 유효하지 않음을 필드에 기록하고, 다른 메서드는 이 필드를 검사해서 객체가 닫힌 후에 불렸다면 IllegalStateException을 던지는 것이다.



<br><Br>
  
## cleaner 와 finalizer는 어디에 써야 할까?
  
  
### 적절한 쓰임새 두가지 
  
  1. 자원의 소유자가 close 메서드를 호출하지 않는 것에 대비한 안전망 역할이다. <br>
   - cleaner나 finalizer가 즉시 호출되리라는 보장은 없지만, 클라이언트가 하지 않은 자원 회수를 늦게라도 해주는 것이 아예 안 하는 것보다는 나으니 말이다. <br>
   - 이런 안전망 역할은 finalizer를 작성할 때는 그럴만한 값어치가 있는지 심사숙고하자. <br>
   - FileInputStream, FileOuputStream, ThreadPoolExcutor가 대표적이다.
  
  2. 네이티브 피어와 연결된 객체이다. <br>
   - 네이티브 피어란 일반 자바 객체가 네이티브 메서드를 통해 위임한 네이티브 객체를 말한다. <br>
   - 네이티브 피어는 자바 객체가 아니니 가비지 컬렉터는 그 존재를 알지 못한다. 그 결과 자바 피어를 회수 할 때 네이티브 객체까지 회수하지 못한다. 그 결과 자바 피어를 회수할 때 네이티브 객체까지 회수하지 못한다. <br>
   - cleaner나 finalizer가 나서서 처리하기에 적당한 작업이다. 단 성능 저하를 감당할 수 있고 네이티브 피어가 심각한 자원을 가지고 있지 않을 때에만 해당된다. <br>
   - 성능 저하를 감당할 수 없거나 네이티브 피어가 사용하는 자원을 즉시 회수해야 한다면 앞서 설명한 close 메서드를 사용해야 한다. <br>
  
  
  
##### cleaner를 안전망으로 활용하는 AutoCloseable 클래스
  
```java
public class Room implements AutoCloseable {
  
    private static final Cleaner cleaner = Cleaner.create();
  
    
    // 청소가 필요한 자원, 절대 ROOM을 참조해서는 안된다.!
    private static class State implements Runnable {
  
        int numJunkPiles; // 방(Room) 안의 쓰레기 수 
  
        State(int numJunkPiles){
            this.numJunkPiles = numJunkPiles; 
 
        }
 
  
        // close 메서드나 cleaner 가 호출한다.
        @Override
        public void run(){
            System.out.println("방청소");
            numJunkPiles = 0; 
        }
    }
  
    // 방의 상태. cleanable과 공유한다.
    private final State state; 
  
    // cleanable 객체, 수거 대상이 되면 방을 청소한다.
    private final Clenaer.Cleanable cleanable; 
  
    public Room(int numJunkPiles){
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
      
    }
  
    @Override
    public void close(){
        cleanable.clean();
    }
}
  
```
  
: static으로 선언된 중첩 클래스인 State는 cleaner가 방을 청소할 떄 수거할 자원들을 담고 있다. 이 예에서는 단순히 방안의 쓰레기 수를 뜻하는 numJunkPiles 필드가 수거할 자원에 해당한다.
  더 현실적으로 만들려면 이 필드는 네이티브 피어를 가리키는 포인터를 담은 final long 변수여야 한다. State는 Runnable 을 구현하고, 그 안의 run 메서드는 cleanable에 의해 딱 한번만 호출될 것이다.
  이 cleanable 객체는 Room 생성자에서 cleaner에 Room과 State를 등록할 때 얻는다. run메서드가 호춛되는 상황은 둘 중 하나다. 보통은 Room의 close 메서드를 호출할 떄다.
  <br>
  close 메서드에서 Cleanable의 clean을 호출하면 이 메서드 안에서 run을 호출한다. 혹은 가비지 컬렉터가 Room을 회수할 때까지 클라이언트가 close를 호출하지 않는다면 cleaner가 (바라건대) State의 run 메서드를 호출해줄 것이다.
  
  
  State 인스턴스는 '절대로' Room 인스턴스를 참조해서는 안 된다. Room 인스턴스를 참조할 경우 순환참조가 생겨 가비지 컬렉터가 Room 인스턴스를 회수해갈 기회가 오지 않는다.
  
 State가 정적 중첩 클래스인 이유가 여기에 있다.정적이 아닌 중첩 클래스는 자동으로 바깥 객체의 참조를 갖기 쉬우니 사용하지 않는다.
  
  
  
  앞서 이야기 한 대로 Room의 cleaner 는 단지 안저망으로만 쓰였다. 클라이언트가 모든 Rooom 생성을 try-with-resources 블록을 감쌌다면 자동 청소는 전혀 필요하지 않다.
  
<br> 
  
##### 다음은 잘 짜인 클라이언트 코드의 예이다.
  
```java
public class Adult {
  
    public static void main(String[] args) {
        try(Room myRoom = new Room(7){
            System.out.println("안녕~");
        }
     
    }
  
}
```
  
  
기대한 대로 Adult 프로그램은 "안녕~"을 출력한 후 , 이어서 "방 청소"를 출력한다.
 
  
##### 이번엔 결코 방 청소를 하지 않는 다음 프로그램을 살펴보자.

```java
public clas Teenager {
  
    public static void main(String[] args){ 
          new Room(99);
          System.put.println("아무렴").
    }
  
}  
```

  
  
> cleaner는 안전망 역할이나 중요하지 않은 네이티브 자원 회수 용으로만 사용하자. 물론 이런 경우라도 불확실성과 성능 저하에 주의해야 한다.  
  
