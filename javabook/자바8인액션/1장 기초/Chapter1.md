### 자바 8을 눈여겨봐야 하는 이유

> 자바 8의 새로운 기능 : 람다, 스트림, 디폴트 메서드


##### 기존 코드 <br>

##### : 무게에 따라 목록(inventory)에서 사과 리스트를 정렬하는 코드. 

```java
Collections.sort(inventory, new Comparator<Apple>() {
     public int compare(Apple a1, Apple a2){
           return a1.getWeight().compareTo(a2.getWeight()); 
     }
});
```

##### refactoring 코드 

```java
inventory.sort(comparing(Apple::getWeight)); 
```


> 자바 8이 등장하기 이전에는 나머지 코어를 활용하려면 스레드를 사용하는 것이 좋다고 누군가 조언했을 것이다. 하지만 스레드를 사용하면 관리하기 어렵고 많은 문제가 발생할 수 있다.<br>
> 자바는 이러한 병령 실행 환경을 쉽게 관리할 수 있고 에러가 덜 발생할 수 있는 방향으로 진화하려 노력했다. <br>
> 자바 5에서는 스레드 풀, 병령 실행 컬렉션 등 강력한 도구를 도입했고, 자바 7에서는 병렬 실행에 도움을 줄 수 있는 포크/조인 프레임워크를 제공했지만 여전히 개발자가 활용하기는 쉽지 않았다.


" 간결한 코드"와 "멀티코어 프로세서" 이 두가지가 자바 8을 구성하는 핵심적인 사항이다.

<br>

### 자바 8에서 제공하는 새로운 기술. 

- 스트림 API <br>
- 메서드에 코드를 전달하는 기법 <br>
- 인터페이스의 디폴트 메서드 <br>

<br>

* 자바 8에서는 병렬 연산을 지원하는 스트림이라는 새로운 API를 제공한다. <br>
* DB 질의 언어에서 고수준 언어로 원하는 동작을 표현하면, 구현에서 최적의 저수준 실행 방법을 선택하는 방식으로 동작한다.
* 즉, 스트림을 이용하면 에러를 자주 일으키며 멀티코어 CPU를 이용하는 것보다 비용이 훨씬 비싼 키워드 synchronized를 사용하지 않아도 된다. 

----

## 스트림 처리 

> 스트림이란 한 번에 한 개씩 만들어지는 연속적인 데이터 항목들의 모임이다. <br>
> 자바 8에서는 java.util.stream 패키지에 스트림 API가 추가되었다. <br>
> 스트림 API 는 파이프라인을 만드는데 필요한 많은 메서드를 제공한다.

"자바8에서 스트림 API의 핵심은 우리가 하려는 작업을 고수준으로 추상화해서 일련의 스트림으로 만들어 처리할 수 있다는 것이다. 또한 스트림 파이프라인을 이용해서 입력 부분을 여러 CPU 코어에 쉽게 할당할 수 있다는 부가적인 이득도 얻을 수 있다." <br>

> 병렬성을 꽁짜로 얻을 수 있다.
     - 일반적으로 synchronized는 시스템 성능에 악영향을 미친다. 하지만 자바 8 스트림을 이용하면 기존의 자바 스레드 API보다 쉽게 병렬성을 활용할 수 있다.



<br><br>


### 자바8에 추가된 개념. 


1. 메서드 래퍼런스 <br>
     - 기존에 객체 래퍼런스를 이용해서 객체를 이리저리 주고받았던 것처럼 자바 8에서는 File::isHiden을 이용해서 메서드 래퍼런스를 전달할 수 있게 되었다. 

```java
File[] hiddenFiles = new File(".").listFiles(File::isHidden);
```


2. 람다 : 익명함수   https://mangkyu.tistory.com/113

##### 자바 8 이전 


```java

// 녹색사과를 선택해서 리스트로 반환
public staic List<Apple> filterGreenApples(List<Apple> inventory){

     List<Apple> result = new ArrayList<>();
     for(Apple apple : inventory){
          if("green".equals(apple.getColor())){
               result.add(apple);
          }
     }
     return result;
}


// 무게 150kg 이상 필터링
public static List<Apple> filterHeavyApples(List<Apple> inventory){
     List<Apple> result = new ArrayList<>();
     for(Apple apple : inventory){
          if(apple.getWeight() > 150){
               result.add(apple);
          }
     }
     return result;
}
```


##### 자바 8코드

```java
public static boolean isGreenApple(Apple apple){
     return "green".equals(apple.getColor());
}

public static boolean isHeavyApple(Apple apple){
     return apple.getWeight() > 150;
}

public interface Predicate<T>{
     boolean test(T, t);
}

static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
     
     List<Apple> result = new ArrayList<>();
     for(Apple apple : inventory){
          if(p.test(apple)){
               result.add(apple);
          }
     }
     return result; 
}
```

```java
// 1) 메서드 호출방법1
filterApples(inventory, Apple::isGreenApple);

// 2) 메서드 호출방법2
filterApples(inventory, Apple::isHeavyApple);


```

* Iterator 과 for-each 의 차이점 : https://erinyees.tistory.com/21


##### 메서드 전달에서 람다로


```java
filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));

or

filterApples(inventory, (Apple a) -> "a.getWeight() >150);

or 

filterApples(inventory, (Apple, a) -> a.getWeight() <80 || "brown".equals(a.getColor()));

```

###### filter를 이용

```java
filter(inventory, (Apple a) -> a.getWeight() > 150); 
```

: 위 코드는 병렬성이라는 중요성 때문에 설계자들은 이와 같은 설계를 포기했다. 대신 자바 8에서는 filter와 비슷한 동작을 수행하는 연산집합을 포함하는 새로운 API를 제공한다.
또한 컬렉션과 스트림 간에 변환할 수 있는 메서드(map, reduc 등) 도 제공한다.
