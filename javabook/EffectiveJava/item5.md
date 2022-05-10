# 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라. 

##### 정적 유틸리티를 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다. 

```java
public class SpellChecker {
 
    private static final Lexicon dictionary = ...;
  
    private SpellChecker() { } // 객체 생성 방지 
  
    public static boolean isValid(String word) {...}  
    public static List<String> suggestions(String typo) {...}
  
}
```


<br><br>

##### 싱글턴을 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.

```java
public class SpellChecker {
  
    private final Lexicon dictionary = ...;
  
    private SpellChecker(...) { }
    public static SpellChecker INSTANCE = new SpellChecker(...);
  
    public boolean isValid(String word){ ... }
    public List<String> suggestions(String type) {...}
    
}
```

두 방식 모두 사전을 단 하나만 사용한다고 가정한다는 점에서 그리 휼룡해 보이지 않다.


##### 사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식이 적합하지 않다.


( 인스턴스를 생성할 떄 생성자에 필요한 자원을 넘겨주는 방식이다. )

<br> <br>


###### 의존 객체 주입은 유연성과 테스트 용이성을 높여준다.

```java
public class SpellChecker {
    private final Lexicon dictionary; 
  
    public SpellChecker(Lexicon dictionary){
        this.dictionary = Objects.requireNonNull(dictionary);  
    }
  
    public boolean isValid(String word){ ... }
    public List<String> suggestions(String type) { ... }
  
 
}
```



의존 객체 주입은 정적 팩터리, 빌더 모두에 똑같이 응용할 수 있다. <br>

이 패턴의 쓸만한 변형으로, 생성자에 자원 팩터리를 넘겨주는 방식이 있다. 팩터리란 호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 객체를 말한다.

<br>

즉, 팩터리 메서드 패턴을 구현한 것이다. 자바 8에서 Supplier<T> 인터페이스가 팩터리를 표현한 완벽한 예이다. 
  
  
  
<br>
  
  
> 클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 자원이 클래스 동작에 영향을 준다면 싱글턴과 정적 유틸리티 클래스는 사용하지 않는 것이 좋다.
