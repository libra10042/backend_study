## AOP

> AOP는 IoC/DI, 서비스 추상화와 더불어 스프링의 3대 기반 기술의 하나다. 
> 스프링에 적용된 가장 인기 있는 AOP 의 적용 대상은 바로 선언적 트랜잭션 기능이다.


###### 비지니스 로직과 트랜잭션 경계설정의 분리 
```

public void upgradeLevels() throws Exception{
  TransactionStatus status = this.transactionManager
        .getTransaction(new DefaultTransactionDefinition());
        
  try{ 
      upgradeLevelsInternal();
      this.transactionManager.commit(status);
  } catch (Exception e){
      this.transactionManager.rollback(status);
      throw e; 
  }
}

private void upgradeLevelsInterval(){
  List<User> users = userDao.getAll();
  for(User user : users){
      if(canUpgradeLevel(user){
          upgradeLevel(user);
      }
  }
}

```

###### 트랜잭션이 적용된 UserServiceTx
```
public class UserServiceTx implements UserService{
    UserService userService;
    PlatformTransactionManager transactionManager; 
    
    public void setTransactionManager { 
        PlatformTransactionManager transactionManager){ 
          this.transactionManager = transactionManager'
    }
    
    public void setUserService(UserService userService){
        this.userService = userService; 
    }
    
    public void add(User user){
        this.userService.add(user);
    }
    
    public void upgradeLevel(){
        TransactionStatus status = this.transactionManager
          .getTransaction(new DefaultTransactionDefinition());
    
        try {
            userService.upgradeLevels();
            
            this.transactionManager.commit(status);
        
        } catch(RuntimeException e){
            this.transactionManager.rollback(status);
            throws e; 
        }  
    }
}
```

### 트랜잭션 분리에 따른 테스트 수정. 

###### 목 오브젝트 설정이 필요한 테스트 코드 수정. 

```
@Test
public void upgradeLevels() throws Exception {
    MockMailSender mockMailSender = new MockMailSender();
    userServiceImpl.setMailSender(mockMailSender);
}
```


###### 분리된 텥스트 기능이 포함되도록 수정한 upgradeAllOrNoting()
```
@Test
public void upgradeAllOrNothing() throws Exception {
    TestUserServer testUserService = new TestUserService(user.get(3).getId());
    testUserService.setUserDao(userDao);
    testUserService.setMailSender(mailSender);
    
    UserServiceTx txUserService = new UserServiceTx();
    txUserService.setTransactionManager(transactionManager);
    txUserService.setUserService(testUserService);
    
    userDao.deleteAll();
    for(User user : users) userDao.add(user);
    
    try {
    
    }
    ...
}
```

----
### 고립 단위 테스트 
> 가장 편하고 좋은 테스트 방법은 가능한 한 작은 단위로 쪼개서 테스트하는 것이다.
> 하지만 작은 단위로 테스트하고 싶어도 그럴 수 없는 경우가 많다.

```
@Test
public void upgradeLevels() throws Exception{
    userDao.deleteAll();
    for(User user : users) userDao.add(user);    // DB 테스트 데이터 준비 
    
    MockMailSender mockMailSender = new MockMailSender();
    userServiceImpl.setMailSender(mockMailSender);          // 메일 발송 여부 확인을 위해 목 오브젝트 DI
    
    
    userService.upgradeLevels();   // 테스트 대상 실행
    
    
    checkLevelUpgraded(users.get(0), false); 
    checkLevelUpgraded(users.get(1), true);
    checkLevelUpgarded(users.get(2), false);
    checkLevelUpgraded(users.get(3), true);
    checkLevelUpgraded(users.get(4), false);                         // DB에 저장된 결과 확인.
    
    List<String> request = mockMailSender.getRequests();
    assertThat(request.size(), is(2));
    assertThat(request.get(0), is(users.get(1).getEmail());
    assertThat(request.get(1), is(users.get(3).getEmail());         // 목 오브젝트를 이용한 결과 확인. 
}
private void checkLevelUpgraded(User user, boolean upgraded){
    User userUpdate = userDao.get(user.getId());
    ...
}
```

### UserDao 목 오브젝트
##### 사용자 레벨 업그레이드 작업 중에 UserDao를 사용하는 코드

```
public void upgradeLevels(){
    List<User> users = userDao.getAll();        // 업그레이드 후보 사용자 목록을 가져온다.
    for(User user : users){
        if(canUpgradeLevel(user)){
            upgradeLevel(user);
        }
    }
    
    protected void upgradeLevel(User user){
        user.upgradeLevel();
        userDao.update(user);     // 수정된 사용자 정보를 DB에 반영한다.
        sendUpgradeEmail(user);
    }
}
```

##### UserDao 오브젝트

```
static class MockUserDao implements UserDao{
    private List<User> users;    // 레벨 업그레이드 후보 User 오브젝트 목록
    private List<User> updated = new ArrayList();    // 업그레이드 대상 오브젝트를 저장해둘 목록
    
    private MockUesrDao(List<User> users){
        this.users = users; 
    }
    
    public List<User> getUpdated(){
        return this.updated; 
    }
    
    public List<User> getAll(){
        return this.users;                        // 스텁 기능 제공. 
    }
    
    public void update(User user){
        updated.add(user);                        // 목 오브젝트 기능 제공. 
    }
    
    public void add(User user){ throws new UnsupportedOperationException(); }
    public void deleteAll(){ throws new UnsupportedOperationException(); }
    public void get(String id){ throws new UnsupportedOperationException(); }
    public int getCount(){ throw new UnsupportedOperationException(); }                                    // 테스트에 사용되지 않는 메소드 
}
```

##### MockUserDao 를 사용해서 만든 고립된 테스트

```
@Test
public void upgradedLevels() throws Exception{
    UserServiceImpl userServiceImpl = new UserServiceImpl();     // 고립된 테스트에서는 테스트 대상 오브젝트를 직접 생성하면 된다.
    
    MockUserDao mockUserDao = new MockUserDao(this.users);  
    userServiceImpl.serUesrDao(mockUserDao);                     // 목 오브젝트로 만든 UserDao를 직접 DI 해준다.

    MockMailSender mockMailSender = new MockMailSender();
    userServiceImpl.setMailSender(mockMailSender);
    
    userServiceImpl.upgradeLevels();
    
    List<User> updated = mockUserDao.getUpdated();              // MockUserDao로부터 업데이트 결과를 가져온다. 
    assertThat(updated.size(), is(2));
    checkUserAndLevel(updated.get(0), "joytouch", Level.SILVER);      // 업데이트 횟수와 정보를 확인한다.
    checkUserAndLevel(updated.get(1), "madnite1", Level.GOLD); 
    
    
    List<String> request = mockMailSender.getRequests();
    assertThat(request.size(), is(2)); 
    assertThat(request.get(0), is(users.get(1).getEmail()));
    assertThat(request.get(1), is(users.get(3).getEmail()));
    
}

private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel){
    assertThat(updated.getId(), is(expected));
    assertThat(updated.getLevel(), is(expectedLevel)); 
}
```


### 단위 테스트와 통합 테스트 중에서 어떤 방법을 쓸지는 어떻게 결정할까?
> 1) 항상 단위 테스트를 먼저 고려한다.
> 2) 하나의 클래스나 성격과 목적과 같이 긴밀한 클래스 몇 개를 모아서 외부와의 의존관계를 모두 차단하고 필요에 따라 스텁이나 목 오브젝트 등의 테스트 대역을 이용하도록
> 테스트를 만든다. 단위 테스트는 테스트 작성도 간단하고 실행 속도도 빠르며 테스트 대상 외의 코드나 환경으로부터 테스트 결과에 영향을 받지도 않기 때문에 가장 빠른 시간에 효과적인
> 테스트를 작성하기에 유리하다. 

----

### 목 프레임워크
> 단위 테스트를 만들기 위해서는 스텁이나 목 오브젝트의 사용이 필수적이다. 
> 의존관계가 없는 단순한 클래스나 세부 로직을 검증하기 위해 메소드 단위로 테스트할 때가 아니라면, 대부분 의존 오브젝트를 필요로 하는 코드를 테스트하게 되기 때문이다.

##### mockito 프레임워크
> mockito와 같은 목 프레임워크의 특징은 목 클래스를 일일이 준비해둘 필요가 없다는 점이다. 

- 사용 방법
- 1) 인터페이스를 이용해 목 오브젝트를 만든다.
- 2) 목 오브젝트가 리턴할 값이 있으면 이를 지정해준다. 메소드가 호출되면 예외를 강제로 던지게 만들 수도 있다.
- 3) 테스트 대상 오브젝트에 DI 해서 목 오브젝트가 테스트 중에 사용되도록 만든다.
- 4) 테스트 대상 오브젝트를 사용한 후에 목 오브젝트의 특정 메소드가 호출됐는지, 어떤 값을 가지고 몇 번 호출 됐는지를 검증한다.

##### Mockito를 적용한 테스트 코드

```
@Test
public void mockUpgradeLevels() throws Exception{
    UserServiceImpl userServiceImpl = new UserServiceImpl();
    
    UserDao mockUesrDao = mock(UserDao.class);
    when(mockUserDao.getAll()).thenReturn(this.users); 
    userServiceImpl.setUserDao(mockUserDao);                             // 다이나믹한 목 오브젝트 생성과 메소드의 리턴 값 설정. 그리고 DI 까지 세줄이면 충분하다.
    
    MailSender mockMailSender = mock(MailSender.class);
    userServiceImpl.setMailSender(mockMailSender);                       // 리턴 값이 없는 메소드를 가진 오브젝트는 더욱 간단하게 만들 수 있다.
    
    userServiceImpl.upgradeLevels(); 
    
    
    verify(mockUserDao, times(2)).update(and(User.class));
    verify(mockUserDao, times(2)).update(and(User.class));
    verify(mockUserDao).update(users.get(1));
    assertThat(users.get(1).getLevel(), is(Level.SILVER)); 
    verify(mockUserDao).update(users.get(3));
    assertThat(users.get(3).getLevel(), is(Level.GOLD));
    
    
    ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class); 
    verify(mockMailSender, times(2)).send(mailMessageArg.capture());                           // 파라미터를 정밀하게 검사하기 위해 캡처할 수도 있다.
    
    assertThat(mailMessage.get(0).getTo()[0], is(users.get(1).getEmail()));
    assertThat(mailMessage.get(1).getTo()[0], is(users.get(3).getEmail()));
}
```

