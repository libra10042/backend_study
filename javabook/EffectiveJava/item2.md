# 생성자에 매개변수가 많다면 빌더를 고려하라. 

: 정적 팩토리와 생성자에는 똑같은 제약이 있다. 선택적 매개변수가 많을 때 적절히 대응하기 어렵다는 점이다. 



### 점층적 생성자 패턴과 자바빈즈 패턴. <br>
- 점층적 생성자 패턴도 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다. <br>
- 자바빈즈 패턴에서는 클래스를 불변 으로 만들 수 없으며 스레드 안정성을 얻으려면 프로그래머가 추가 작업을 해줘야 한다.



<br>

빌더 패턴이란 ? <br>

점층적 생성자 패턴과 자바빈즈 패턴의 장점만 취했다.


```java
public class NutritionFacts {
    private final int servingSize; 
    private final int servings; 
    private final int calories; 
    private final int fat; 
    private final int sodium; 
    private final int carbohydrate; 
    
    private static class Builder {
    
        // 필수 매개변수
        private final int servingSize; 
        private final int servings; 
        
        // 선택 매개변수 - 기본값으로 초기화한다.
        private int calories = 0; 
        private int fat = 0; 
        private int sodium = 0; 
        private int carbohydrate = 0; 
        
        
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize; 
            this.servings = servings; 
        
        }
        
        
        public Builder calories(int val){
            calories = val; 
            return this; 
        
        }
    
        public Builder fat(int val){
            fat = val; 
            return this; 
        }
        
        public Builder sodium(int val){
            sodium = val; 
            return this; 
        }
        
        public Builder carbohydrate(int val){
            carbohydrate = val; 
            return this; 
        }
        
        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    
    }
    
    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize; 
        servings = builder.servings; 
        calories = budiler.calories; 
        fat = builder.at;
        sodium = builder.sodium;
        carbohydrate = buidler.carbohydrate;
  
    
    }

}
```


#### 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기가 좋다.

```java
public abstract class Pizza {

    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    
    final Set<Topping> toppings; 
    
    
    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class); 
         
        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping)); 
            return self(); 
        
        }
        
        abstract Pizza build(); 
    
        // 하위 클래스는 이 메서드를 재정의 하여 "this"를 반환하도록 해야 한다.
        protected abstract T self();
    
    
    }
    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone(); // 아이템 50 참조
    
    }

}

```


##### 뉴욕피자 

```java
public class MyPizza extends Pizza { 

        public enum Size { SAMLL, MEDIUM, LARGE };
        private final Size size;
        
        public static class Builder extends Pizza.Builder<Builder> {
        
            private final Size size; 
            
            public Builder(Size size){
                this.sizes = Object.requireNonNull(size);
            
            }
            
            @Override public NyPizza build(){
                return new NyPizza(this);
            }
            
            @Override protected Builder self() { return this; }
            
        
        }
           
}
```

##### 칼초네 피자 

```
private class Calzone extends Pizza {
        private final boolean sauceInside;

        public static class Builder extends Pizza.Builder<Builder>{

            private boolean sauceInside = false; // 기본값. 

            public Builder sauceInside(){
                sauceInside = true; 
                return this; 

            }

            @Override public Calzone build(){
                return new Calzone(this);
            }

            @Override protected Builder self() { return this;}


        }

    


    private CalzoneBuilder builder(){
        super(builder);
        sauceInside = builder.sauceInside; 
    }
}
```



> 처리 해야할 생성자나 매개변수가 많다면 빌더 패턴을 선택하는 것이 더 낫다.
