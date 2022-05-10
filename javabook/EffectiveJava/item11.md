# euqals 를 재정의하려거든 hashCode도 재정의하라.

: equals를 재정의한 클래스 모두에서 hasCode도 재정의해야 한다. 그렇지 않으면 hasCode 일반 규약을 어기게 되어 해당 클래스의 인스턴스를 HashMap이나 HashSet 같은 컬렉션의 원소로 사용할 때 문제를 일으킬 것이다.
 
- equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다. 단 어플리케이션을 다시 실행한다면 값이 달라져도 상관없다.

- equals(Object)가 두 객체를 같다고 판단했다면, 두 객체의 hashCode 는 똑같은 값을 반환해야 한다.

- equals(Object)가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다. 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.


" hashCode 재정의를 잘못했을 때 크게 문제가 되는 조항은 두 번째다. 즉 논리적으로 같은 객체의 해시코드를 반환해야 한다. "

```java
Map<PhoneNumber, String> m = new HashMap<>();
m.put(new PhoneNumber(707, 867, 5309), "제니");

```

m.get(new PhoneNumber(707, 867, 5309))를 실행하면 "제니"가 나와야할 것 같지만, 실제로는 null을 반환한다. 

여기에는 2개의 PhoneNumber 인스턴스가 사용되었다. 하나는 HashMap에 "제니"를 넣을 때 사용됐고, (두번째는 이를 꺼내려할 때 사용됐다. PhoneNumber 클래스는 hashCode를 재정의하지 않았기 때문에 동치인 두 객체가 서로 다른 해시코드를 반환하여 두번째 규약을 지키지 못한다.

그 결과 get 메서드는 엉뚱한 해시 버킷에 가서 객체를 찾으려 한 것이다. 설사 두 인스턴스를 같은 버킷에 담았더라도 get 메서드는 여전히 null을 반환하는데, hashMap은 해시코드가 다른 엔트리끼리는 동치성 비교를 시도하지 않도록 최적화 되어 있기 때문이다.



##### 전형적인 hashCode 메서드

```JAVA
@Override
public int hasCode(){
    int result = Short.hasCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    return result; 
}
```
 
 
##### 한 줄짜리 hashCode 메서드 - 성능이 살짝 아쉽다.

```java
@Override
public int hashCode(){
    return Objects.hash(lineNum, prefix, areaCode);
}

```
 

##### 해시코드를 지연 초기화하는 hashCode 메서드 - 스레드의 안정성까지 고려해야 한다.

```java
public int hashCode; // 자동으로 0으로 초기화 된다.

@Override
public int hashCode(){

    int result = hashCode;
    if(result ==0) {
         result = Short.hashCode(areaCode);
         result = 31 * result + Short.hashCode(prefix);
         result = 31 * result + Short.hashCode(lineNum);
         hashCode = result; 
    }
    return result; 

}
```

<br> <br>

> 성능을 높인답시고 해시코드를 계산할 때 핵심 필드를 생략해서는 안된다. 속도야 빨라지겠지만, 해시 품질이 나빠져 해시테이블의 성능을 심각하게 떠러 뜨릴 수도 있다.



> hashCode가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 공표하지 말자. 그래야 클라이언트가 이 값에 의지하지 않게 되고, 추후에 계산 방식을 바꿀 수도 있다.

