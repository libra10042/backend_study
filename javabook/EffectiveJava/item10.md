# equals는 일반 규약을 지켜 재정의하라

equals 메서드는 재정의하기 쉬워 보이지만 곳곳이 함정이 도사리고 있어 자칫하면 끔찍한 결과를 초래한다. ( 문제를 회피하는 방법은 재정의 하지 않는 것이다.) 

<br>

그냥 두면 그 클래스의 인스턴스는 오직 자신과만 같게된다. 그러니 다음에서 열거한 상황 중 하나에 해당한다면 재정의하지 않는 것이 최선이다.

- 각 인스턴스가 본질적으로 고유하다. 값을 표현하는 게 아니라 동작하는 개체를 표현하는 클래스가 여기에 해당한다. Thread가 좋은 예로, Object의 equals 메서드는 이러한 클래스에 딱 맞게 구현되었다.

- 인스턴스의 '논리적 동치성(logical equality)' 을 검사할 일이 없다. 예컨대 java.util.regex.Pattern를 재정의해서 두 Pattern의 인스턴스가 같은 정규표현식을 나타내는지를 검사하는, 즉 논리적 동치성을 검사하는 방법도 있다.

- 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다. 예컨대 대부분의 set 구현체는 AbstractSet이 규현한 equals 를 상속받아 쓰고, list 구현체들은 abstractlist로부터, map 구현체들은 abstractmap으로 부터 상속받아 그대로 쓴다.

- 클래스가 private 이거나 package-private 이고 equals 메서드를 호출할 일이 없다



---

### euqals 메서드는 동치관계 (equivalence relation)를 구현하면, 다음을 만족한다.

- 반사성(reflexivity) : null이 아닌 모든 참조 값 x에 대해, x.equals(x) 는 true다. 

- 대칭성(symmetry) : null이 아닌 모든 참조 값 x, y 에 대해, x.equals(y)가 true면 y.equals(x)도 true다.

- 추이성(transitivity) : null 이 아닌 모든 참조 값 x, y, z 에 대해, x.equals(y)가 true 이고 y.equals(z)도 true면 x.equals(z)도 true다.

- 일관성(consistency) : null이 아닌 모든 참조 값 x, 에 대해, x.equals(y) 를 반복해서 호출하면 항상 true를 반환하거나 항상 false 를 반환한다.

- null - 아님 : null 이 아닌 모든 참조 값 x에 대해 , e.quals(null)은 false다. 


## 꼭 필요한 경우가 아니면 equals를 정의하지 말자. 많은 경우에 Object의 equals가 원하는 비교를 정확히 수행해준다. 재정의해야 할 때는 그 클래스의 핵심 필드 모두를 빠짐없이, 다섯가지 규약을 확실히 지켜가며 비교해야 한다.
