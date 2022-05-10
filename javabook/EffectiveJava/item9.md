# try - finally 보다는  try-with-resources를 사용하라. 


자바 라이브러리는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 받다.

InputStream, OutputStream, java.sql.Connection 등이 좋은 예이다.

자원 닫기는 클라이언트가 놓치기 쉬워서 예측할 수 없는 성능 문제로 이어지기도 한다. 이런 자원 중 상당수가 안전망으로 finalizer를 활용하고는 있지만 finalizer는 그리 믿을만 하지 못한다.


#### try-finally  더 이상 자원을 회수하는 최선의 방책이 아니다.

```java
static String firstLineOffFile(String path) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader(path));
    
    try{
        return br.readLine(); 
    }finanlly{
        br.close();
    }

}
```


#### 자원이 둘 이상이면 try-finally 방식은 너무 지저분하다.

```java
static void copy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    
    
     try{
        OutputStream out = new FileOutputStream(dst);
        
        try{
        
            byte[] buf = new byte[BUFFER_SIZE];
            int n; 
            while((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
          
        }finally {
            out.close();
        }
      
     
     }finally{
        in.close();
     
     }
}
```

<br>


#### try-with-resources  자원을 회수하는 최선택!

```java
static String firstLineOfFile(String path) throws IOException {
    try (BufferedReader br = new BufferdReader ( new FileReader(path))){
        return br.readLine();
    }
}
```

#### 복수의 자원을 처지하는 try-with-resources  짧고 매혹적이다.

```java
static void copy(String str, String dst) throws IOException {
    try (InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst)) {
        
        byte[] buf = new byte[BUFFER_SIZE];
        
        int n;
        
        while((n = in.read(buf)) >=0)
            out.write(buf, 0, n);
           
    }
}

```


#### try-with-resources 를 catch 절과 함께 쓰는 모습

```java
static String firstLineOfFile(String path, String defaultVal){

    try(BufferdReader br = new BufferedReader( new FileReader(path))) {
            return br.readLine();
    }catch(IOException e){
            return defaultVal; 
    }

}

```

### 정리

꼭 회수해야 하는 자원을 다룰 떄는 try-finally 말고, try-with-resources를 사용하자.
