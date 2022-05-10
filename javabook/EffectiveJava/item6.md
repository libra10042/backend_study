# 불필요한 객체 생성을 피하라

주어진 문자열이 유요한 로마 숫자인지를 확인하는 메서드를 작성한다고 가정해보면 정규표현식을 활용한 방식이 가장 쉬운 해법이다.

##### 성능을 끌어올릴 수 있다.

```java
static boolean isRomanNumeral(String s){
    return s.matches("^(?=.)M*(C[MD] |DC{0.3})"
        + "(X[CL]||L?X{0.3}) (I[XV] |V?I{0.3})$");
```

string.matches 는 정규표현식으로 문자열 형태를 확인하는 가장 쉬운 방법이지만, 성능이 중요한 상황에서 반복해 사용하기엔 적합하지 않다.


<br>

성능을 개선하려면 필요한 정규표현식을 표현하는 (불변인) Pattern 인스턴스를 클래스 초기화 ( 정적 초기화 ) 과정에서 직접 생성해 캐싱해두고, 나중에 isRomanNumeral 메서드가 호출될 때마다 이 
인스턴스를 재사용한다.


##### 값비싼 객체를 재사용해 성능을 개선한다.

```java
public clas RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile(
          "^(?=.)M*(C[MD] | D?C{0,3})"
          + "(X[CL] | L?X{0,3}) (I[XV] |V?I{0,3})$");
       
    )
    
    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches(); 
    }
}
```



