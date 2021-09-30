## 다이내믹 프록시와 팩토리 빈. 

### 프록시와 프록시 패턴, 데코레이터 패턴. 
> 비즈니스 로직 코드에 트랜잭션 기능을 부여해주는 것이 바로 그런 대표적인 경우다. 
> 마치 자신이 클라이언트가 사용하려고 하는 실제 대상인 것처럼 위장해서 클라이언트의 요청을 받아주는 것을 대리자, 대리인과 같은 역할을 한다고 해서 프록시라고 부른다.


- 프록시의 2가지 사용목록
첫번째) 클라이언트가 타깃에 접근하는 방법을 제어하기 위해서다. 
두번째) 타깃에 부가적인 기능을 부여해주기 위해서다. 
" 두가지 모두 대리 오브젝트라는 개념의 프록시를 두고 사용한다는 점을 동일하지만, 목적에 따라서 디자인 패턴에서는 다른 패턴으로 구분한다. "



### 데코레이터 패턴. 
> 타깃에 부가적인 기능을 런타임 시 다이내믹하게 부여해주기 위해 프록시를 사용하는 패턴을 말한다. 
> 데코레이터 패턴에서는 프록시가 한개로 고정되지 않는다.
> 프록시로서 동작하는 각 데코레이터는 위임하는 대상에도 인터페이스로 접근하기 때문에 자신이 최종 타깃에 위임하는지, 아니면 다음 단계의 데코레이터 프록시로 위임하는지는 알지 못한다.
> 그래서 데코레이터의 다음 위임 대상은 인터페이스로 선언하고 생성자나 수정자 메소드를 통해 위임 대상을 외부에서 런타임 시에 주입받을 수 있도록 만들어야 한다.
> 자바 IO 패키지의 InputStream과 OutputStream 구현 클래스는 데코레이터 패턴이 사용된 대표적인 예이다. 

예) InputStream 이라는 인터페이스를 구현한 타깃인 FileInputStream에 버퍼 읽기 기능을 제공해주는 BufferedInputStream이라는 데코레이터를 적용한 예
```java
InputStream is = new BufferedInputStream(new FileInputStream("a.txt"));
```
##### 데코레이터 패턴을 위한 DI 설정.
```java
<!-- 데코레이터 -->
<bean id="userService" class="springbook.user.service.UserServiceTx">
    <property name="transactionManager" ref="transactionManager" />
    <property name="userService" ref="userServiceImpl" />
</bean>

<!-- 타깃 -->
<bean id="userServiceImpl" class="springbook.user.service.UserServiceImpl">
    <property name="userDao" ref="userDao" />
    <property name="mailSender" fer="mailSender" />
</bean>
```
> "데코레이터 패턴은 타킷의 코드에 손대지 않고 클라이언트가 호출하는 방법도 변경하지 않은 채로 새로운 기능을 추가할 때 유용한 방법이다." 

<br><br>

### 프록시 패턴. 
> 일반적으로 사용하는 프록시라는 용어와 디자인 패턴에서 사용하는 프록시 패턴은 구분되어야 한다. 
> 전자는 클라이언트와 사용  대상 사이에 대리 역할을 맡은 오브젝트를 두는 방법을 총칭한다면, 후자는 프록시를 사용하는 방법 중에서 타깃에 대한 접근 방법을 제어하려는 목적을 가진 경우를 가리킨다.
> 프록시 패턴의 프록시는 타깃의 기능을 확장하거나 추가하지 않는다.대신 클라이언트가 타깃에 접근하는 방식을 변경해준다. 
