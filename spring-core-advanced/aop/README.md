## 스프링 AOP 구현
```java
@Aspect
public class AspectV1 {

    @Around("execution(* hello.aop.order..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
```
- @Around 애노테이션 값인 execution(* hello.aop.order..*(..))는 포인트컷이 된다
- @Around 애누테이션의 메서드인 doLog는 어드바이스(Advice)가 된다
- execution(* hello.aop.order..*(..))는 hello.aop.order 패키지와 그 하위 패키지를 지정하는 AspectJ 포인트컷 표현식이다.

```java
@Aspect
public class AspectV2 {
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {}

    @Around("allOrder()")ll
    
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
```
- @Pointcut에 포인트컷 표현식을 사용한다
- 메서드 이름과 파라미터를 합쳐서 포인트컷 시그니처라 한다
- 메서드의 반환 타입은 void 여야 한다
- 코드 내용은 비워둔다
- 포인트컷 시그니쳐는 allOrder()이다

### 어드바이스 순서
어드바이스는 기본적으로 순서를 보장하지 않는다. 순서를 지정하고 싶으면 @Aspect 적용 단위로 @Order 애노테이션을 적용해야 한다.
문제는 이것을 어드바이스 단위가 아니라 클래스 단위로 적용할 수 있다는 점이다. 그래서 하나의 애스펙트에 여러 어드바이스가 있으면 순서를 보장 받을 수 없다.
따라서 에스팩트를 별도의 클래스로 분리해야 한다

### 어드바이스 종류
- @Around : 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능
- @Before : 조인 포인트 실행 이전에 실행
- @AfterReturning : 조인 포인트가 정상 완료후 실행
- @AfterThrowing : 메서드가 예외를 던지는 경우 실행
- @After : 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)

## 스프링 AOP - 포인트컷
포인트컷 표현식은 AspectJ pointcut expression 즉 에스펙스J가 제공하는 포인트컷 표현식을 줄여서 말하는 것

### 포인트컷 지시자
- 포인트컷 표현식은 execution 같은 포인트컷 지시자(Pointcut Designtor)로 시작한다. 줄여서 PCD라 한다

### 포인트컷 지시자의 종류
- execution : 메소드 실행 조인 포인트를 매칭한다. 스프링 AOP에서 가장 많이 사용하고 기능도 복잡하다
- within : 특정 타입 내의 조인 포인트를 매칭한다
- args : 인자가 주어진 타입의 인스턴스인 조인 포인트
- this : 스프링 빈 객체(스프링 AOP 프록시)를 대상으로 하는 조인 포인트
- target : Target 객체(스프링 AOP 프록시가 가르키는 실제 대상)을 대상으로 하는 조인 포인트
- @target : 실행 객체의 클래스에 주어진 타입의 애노테이션이 있는 조인 포인트
- @within : 주어진 애노테이션이 있는 타입 내 조인 포인트
- @annotation : 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트를 매칭
- @args : 전달된 실제 인수의 런타임 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
- bean : 스프링 전용 포인트컷 지시자, 빈의 이름으로 포인트컷을 지정한다

### execution
#### execution 문법
```properties
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)

execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)
```
- 메소드 실행 조인 포인트를 매칭한다
- ?는 생략할 수 있다
- *같은 패턴을 지정할 수 있다

#### 패키지에서 ., ..의 차이
- . : 정확하게 해당 위치의 패키지
- .. : 해당 위치의 패키지와 그 하위 패키지도 포함

#### execution 파라미터 매칭 규칙
- (String) : 정확하게 String 타입 파라미터
- () : 파라미터가 없어야 한다
- (*) : 정확히 하나의 파라미터, 단 모든 타입을 허용한다
- (*, *) : 정확히 두 개의 파라미터, 단 모든 타입을 허용한다
- (..) : 숫자와 무관하게 모든 파라미터, 모든 타입을 허용한다. 참고로 파라미터가 없어도 된다. 0..*로 이해하면 된다
- (String, ..) : String 타입으로 시작해야 한다. 숫자와 무관하게 모든 파라미터, 모든 타입을 허용한다
  - 예 : (String), (String, Xxx), (String, Xxx, Xxx) 허용

### within
within 지시자는 특정 타입 내의 조인 포인트에 대한 매칭을 제한한다. 해당 타입이 매핑 되면 그 안에 메서드(조인 포인트)들이 자동으로 매칭된다.
* 주의

### args
- args 인자가 주어진 타입의 인스턴스인 조인 포인트로 매칭
- 기본 문법은 execution의 args 부분과 같다

#### execution과 args의 차이점
- execution은 파라미터 타입이 정확하게 매칭되어야 한다. execution은 클래스에 선언된 정보를 기반으로 판단한다
- args는 부모 타입을 허용한다. args는 실제 넘어온 파라미터 객체 인스턴스를 보고 판단한다

### @target, @within
- @target : 실행 객체의 클래스에 주어진 타입의 애노테이션이 있는 조인 포인트
- @within : 주어진 애노테이션이 있는 타입 내 조인 포인트
- @target, @within은 다음과 같이 타입에 있는 애노테이션으로 AOP 적용 여부를 판단한다

### @annotation, @args
- @annotation : 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트를 매칭
- @args : 전달된 실제 인수의 런타입 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
- @bean : 스프링 전용 포인트컷 지시자, 빈의 이름으로 지정한다

# 스프링 AOP 실무 주의사항
## 프록시 기술과 한계 - 타입 캐스팅
- JDK 동적 프록시는 인터페이스가 필수이고, 인터페이스를 기반으로 프록시를 생성한다
- CGLIB는 구체 클래스를 기반으로 프록시를 생성한다
- 스프링이 프록시를 만들때 제공하는 ProxyFactory에 proxyTargetClass 옵션에 따라 둘 중 하나를 선택해서 프록시를 만들 수 있다
  - proxyTargetClass=false : JDK 동적 프록시를 사용해서 인터페이스 기반 프록시 생성
  - proxyTargetClass=true : CGLIB를 사용해서 구체 클래스 기반 프록시 생성
  - 참고로 옵션과 무관하게 인터페이스가 없으면 JDK 동적 프록시를 적용할 수 없으므로 CGLIB를 사용한다

## 프록시 기술과 한계 - CGLIB
