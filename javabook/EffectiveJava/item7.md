# 다 쓴 객체 참조를 해제하라. 

##### 메모리 누수가 일어나는 위치는 어디인가?

```java
public class Stack {
    private Object[] elements;  
    private int size = 0; 
    private static final int DEFAULT_INITIAL_CAPACITY = 16; 
    
    
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e){
        ensureCapacity();
        elements[size++] = e; 
    }
    
    public Object pop(){
        if(size == 0)
            throw new EmptyStackException();
       return elements[--size];
    }
    
    private void ensureCapacity(){
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1); 
        }
    }
   
}

```

: 해법은 간단하다. 핻당 참조를 다 썼을 때 null 처리 (참조 해제)하면 된다. 예시의 스택 클래스에서는 각 원소의 참조가 더 이상 필요 없어지는 시점은 스택에서 꺼내질 때다.

##### 제대로 구현한 POP 메서드

```java
public Object pop(){
    if(size == 0)
        throw new EmptyStackException();
    Object result = elements[--size]; 
    
    elements[size] = null; // 다 쓴 참조 해제
    return result;
  
}

```

> 가비지 컬렉션 언어에서는 메모리 누수를 찾기가 아주 까다롭다. 객체 참조 하나를 살려두면 가비지 컬렉터는 그 객체뿐 아니라 객체가 참조하는 모든 객체를 회수해가지 못한다. 그래서 단 몇 개의 객체가 아주 많은 객체를  회수 되지 못하게 할 수 있고 잠재적으로 성능에 악영향을 줄수 있다.



모든 객체를 다 쓰자마자 일일이 null 처리하는데 혈안이 되기도 한다.하지만 그럴 필요도 없고 바람직하지도 않다. 

> 객체 참조를 null 처리하는 일은 예외적인 경우여야 한다.
> 그렇다면 null 처리는 언제할까? Stack 클래스는 메모리 누수에 취약한 걸까? 바로 스택이 자기 메모리를 직접 관리하기 떄문이다. 이 스택은 elemenets 배열로 저장소 풀을 만들어 원소들을 관리한다.



<br>

일반적으로 자기 메모리를 직접 관리하는 클래스라면 프로그래머는 항시 메모리 누수에 주의해야 한다. 원소를 다 사용한 즉시 그 원소가 참조한 객체들을 다 null 처리해줘야 한다.

<br>


캐시 역시 메모리 누수를 일으키는 주범이다. 객체 참조를 캐시에 넣고 나서, 이 사실을 까맣게 잊은 채 그 객체를 다 쓴 뒤로도 한참을 그냥 놔두는 일을 자주 접할 수 있다. 

<br>

이 때 WeakHashMap을 사용해 캐시를 만들면 다 쓴 엔트리는 그 즉시 자동으로 제거할 수 있다. 


캐시를 만들 때 보통은 캐시 엔트리의 유효 기간을 정확히 정의하기 여럽기 때문에 시간이 지날수록 엔트리의 가치를 떨어뜨리는 방식을 흔히 사용한다.이런 방식에서는 쓰지 않는 엔트리를 이따끔 청소해줘야 한다.
백그라운드 스레드를 활용하거나 캐시에 새 엔트리를 추가할 때 부수 작업으로 수행하는 방법이 있다. LinkedHashMap은 removeEldestEntry 메서드를 써서 후자의 방식으로 처리한다.
<br>
더 복잡한 캐시를 만들고 싶다면 java.lang.ref 패키지를 직접 활용해야 한다.



> 메모리 누수의 세 번째 주범은 바로 리스너(listener) 혹은 콜백(callback)이라 부르는 것이다. 클라이언트가 콜백을 등록만 하고 명확히 해지하지 않는다면, 뭔가 조치를해주지 않는 한 콜백은 계쏙 쌓여갈 것이다.
이럴 때 콜백을 약한 참조로 저장하면 가바지 컬렉터가 즉시 수거해간다.예를 들어 WeakHashMap에 키로 저장하면 된다.
