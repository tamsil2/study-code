## ThreadLocal
쓰레드 로컬은 해당 쓰레드만 접근할 수 있는 특별한 저장소를 말한다.

### ThreadLocal 사용법

```java
class Sample {
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public void sample() {
        nameStore.set("TEST");
        String test = nameStore.get();
    }
}
```
- 값 저장 : ThreadLocal.set(xxx)
- 값 조회 : ThreadLocal.get()
- 값 제거 : ThreadLocal.remove()

> ThreadLocal 주의사항
> 쓰레드 로컬의 값을 사용후 제거하지 않고 그냥 두면 WAS(Tomcat)처럼 쓰레드 풀을 사용하는 경우에 심각한 문제가 발생할 수 있다.
> 해당 쓰레드가 쓰레드 로컬을 모두 사용하고 나면 ThreadLocal.remove()를 호출해서 쓰레드 로컬에 저장된 값을 제거해주어야 한다.

## 템플릿 메서드 패턴과 콜백 패턴
### 템플릿 메서드 패턴
템플릿 메서드 패턴은 이름 그대로 템플릿을 사용하는 방식이다. 템플릿은 기준이 되는 거대한 틀이다. 템플릿이라는 틀에 변하지 않는 부분을 몰아둔다. 그리고 일부 변하는 부분을 별도로 호출해서 해결한다.
템플릿 메서드 패턴은 부모 클래스에 변하지 않는 템플릿 코드를 둔다. 그리고 변하는 부분을 자식 클래스에 두고 상속과 오버라이딩을 사용해서 처리한다.

### 전략 패턴
GOF 디자인 패턴에서 정의한 전략패턴
> 알고리즘 제품군을 정의하고 각각을 캡슐화하여 상호 교환 가능하게 만들자. 전략을 사용하면 알고리즘을 사용하는 클라이언트와 독립적으로 알고리즘을 변경할 수 있다

템플릿 메서드 패턴은 부모 클래스에 변하지 않는 템플릿을 두고, 변하는 부분을 자식 클래스에 두어서 상속을 사용해서 문제를 해결했다.
전략패턴은 변하지 않는 부분을 'Context'라는 곳에 두고, 변하는 부분을 'Strategy'라는 인터페이스를 만들고 해당 인터페이스를 구현하도록 문제를 해결한다. 상속이 아니라 위임으로 문제를 해결하는 것이다.
전략 패턴에서 'Context'는 변하지 않는 템플릿 역할을 하고, 'Strategy'는 변하는 알고리즘 역할을 한다.

## 템플릿 콜백 메서드 패턴
### 콜백 정의
- 프로그래밍에서 콜백 또는 콜애프터 함수는 다른 코드의 인수로서 넘겨주는 실행 가능한 코드를 말한다. 콜백을 넘겨받는 코드는 이 콜백을 필요에 따라 즉시 실행할 수도 있고, 아니면 나중에 실행할 수도 있다
### 자바 언어에서 콜백
- 자바 언어에서 실행 가능한 코드를 인수로 넘기려면 객체가 필요하다. 자바8부터는 람다를 사용할 수 있다.
- 자바8 이전에는 보통 하나의 메소드를 가진 인터페이스를 구현하고, 주로 익명 내부 클래스를 사용했다.
- 최근에는 주로 람다를 사용한다

### 템플릿 콜백 패턴
- 스프링에서는 아래와 같은 방식의 전략패턴을 템플릿 콜백 패턴이라 한다. 전략패턴에서 Context가 템플릿 역할을 하고, Strategy 부분이 콜백으로 넘어온다 생각하면 된다
```java
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        strategy.call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
```
- 템플릿 콜백 패턴은 GOF 패턴은 아니고, 스프링 내부에서 이런 방식을 자주 사용하기 때문에, 스프링 안에서만 이렇게 부른다. 전략패턴에서 템플릿과 콜백 부분이 강조된 패턴이라 생각하면 된다.
- 스프링에서는 JdbcTemplate, RestTemplate, TransactionTemplate, RedisTemplate처럼 다양한 템플릿 콜백 패턴이 사용된다
