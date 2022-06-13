# 스프링 트랜잭션의 이해
## 트랜잭션 적용 확인
- TransactionSynchronizationManager.isActualTransactionActive()
  - 현재 쓰레드에 트랜잭션이 적용되어 있는지 확인할 수 있는 기능이다. 결과가 true면 트랜잭션이 적용되어 있는 것이다. 트랜잭션의 적용 여부를 가장 확실하게 확인할 수 있다

## 트랜잭션 적용 위치
스프링에서 우선순위는 항상 더 구체적이고 자세한 것이 높은 우선순위를 가진다.
예를 들어서 메서드와 클래스에 애노테이션을 붙일 수 있다면 더 구체적인 메서드가 더 높은 우선순위를 가진다

## 스프링의 @Transactional은 다음 두 가지 규칙이 있다
1. 우선순위 규칙
2. 클래스에 적용하면 메서드는 자동 적용

### 우선순위
- 클래스보다는 메서드가 더 구체적이므로 메서드에 있는 @Transactional(readOnly = false) 옵션을 사용한 트랜잭션이 적용된다

### TransactionSynchronizationManager.isCurrentTransactionReadOnly()
현재 트랜잭션이 적용된 readOnly 옵션의 값을 반환한다

### 인터페이스에 @Transactional 적용
인터페이스에도 @Transactional을 적용할 수 있다. 이 경우 다음 순서로 적용된다. 구체적인 것이 더 높은 우선순위를 가진다고 생각하면 바로 이해가 갈 것이다
1. 클래스의 메서드(우선순위가 가장 높다)
2. 클래스의 타입
3. 인터페이스의 메서드
4. 인터페이스의 타입(우선순위가 가장 낮다)

클래스의 메서드를 찾고, 만약 없으면 클래스의 타입을 찾고 만약 없으면 인터페이스의 메서드를 찾고 그래도 없으면 인터페이스의 타입을 찾는다
그런데 인터페이스에 @Transactional 사용하는 것은 스프링 공식 메뉴얼에서 권장하지 않는 방법이다. AOP를 적용하는 방식에 따라서 인터페이스에 애노테이션을 두면 AOP가 적용이 되지 않는 경우도 있기 때문이다. 가급적 구체 클래스에 @Transactional을 사용하자

> 참고
> 스프링은 인터페이스에 @Transactional을 사용하는 방식을 스프링 5.0에서 많은 부분 개선했다. 과거에는 구체 클래스를 기반으로 프록시를 생성하는 CGLIB 방식을 사용하면 인터페이스에 있는 @Transactional을 인식하지 못했다
> 스프링 5.0부터는 이 부분을 개선해서 인터페이스에 있는 @Transactional도 인식한다. 하지만 다른 AOP 방식에서 또 적용되지 않을 수 있으므로 공식 메뉴얼의 가이드대로 가급적 구체 클래스에 @Transactional을 사용하자

## 트랜잭션 AOP 주의 사항 - 프록시 내부 호출
### 내부호출로 인하여 트랜잭션이 적용되지 않는 경우
[내부호출](./image/internalinvoke.png)
1. 클라이언트인 테스트 코드는 callService.eternal()을 호출한다. 여기서 callService는 트랜잭션 프록시이다
2. callService의 트랜잭션 프록시가 호출된다
3. external() 메서드에는 @Transcational이 없다. 따라서 트랜잭션 프록시는 트랜잭션을 적용하지 않는다
4. 트랜잭션을 적용하지 않고, 실제 callService 객체 인스턴스의 external()을 호출한다
5. external()은 내부에서 internal() 메서드를 호출한다. 그런데 여기서 문제가 발생한다

>문제 원인
> 자기 자신의 내부 메서드를 호출하는 this.internal()에서 실제 대상 객체(target)의 인스턴스를 호출하면서 프록시를 거치지 않는다

### public 메서드만 트랜잭션 적용
스프링의 트랜잭션 AOP 기능은 public 메서드에만 트랜잭션을 적용하도록 기본 설정이 되어 있다.
그래서 protected, private, package-visible에는 트랜잭션이 적용되지 않는다. 이는 프록시의 내부 호출과는 무관하고 스프링이 막아둔 것이다

## 트랜잭션 AOP 주의 사항 - 초기화 시점
스프링의 초기화 시점에는 트랜잭션 AOP가 적용되지 않을 수 있다
```java
@PostConstruct
@Transactional
    public void initV1() {
        boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("Hello init @PostConstruct tx active={}", isActive);
    }
```
- 초기화 코드가 먼저 호출되고, 그 다음에 트랜잭션 AOP가 적용되기 때문에 초기화 시점에서는 해당 메서드에서 트랜잭션을 획득할 수 없다

### 해결 방법
```java
@EventListener(ApplicationReadyEvent.class)
@Transactional
public void initV2() {
    boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
    log.info("Hello init ApplicationReadyEvent tx active={}", isActive);
}
```
- 이 이벤트는 트랜잭션 AOP를 포함한 스프링이 컨테이너가 완전히 생성되고 난 다음에 이벤트가 붙은 메서드를 호출해준다

## 트랜잭션 옵션 소개
### value, transactionManager
트랜잭션을 사용하려면 먼저 스프링 빈에 등록된 어떤 트랜잭션 매니저를 사용할지 알아야 한다
사용할 트랜잭션 매니저를 지정할 때는 value, transactionManager 둘 중 하나의 트랜잭션 매니저의 스프링 빈의 이름을 적어주면 된다
이 값을 생략하면 기본으로 등록된 트랜잭션 매니저를 사용하기 때문에 대부분 생략한다. 그런데 사용하는 트랜잭션 매니저가 둘 이상이라면 이름을 지정해서 구분해야 한다

### rollbackFor
예외 발생시 스프링 트랜잭션의 기본 정책
- 언체크 예외인 RuntimeException, Error와 그 하위 예외가 발생하면 롤백한다
- 체크 예외인 Exception과 그 하위 예외들은 커밋한다

이 옵션을 사용하면 기본 정책이 추가로 어떤 예외가 발생할 때 롤백할지 지정할 수 있다
```java
@Transaction(rollbackFor = Exception.class)
```

### noRollebackFor
rollbackFor와 반대이다. 기본 정책에 추가로 어떤 예외가 발생했을 때 롤백하면 안되는지 지정할 수 있다

### Propagation
트랜잭션 전파에 대한 옵션

### isolation
트랜잭션 격리 수준을 지정할 수 있다. 기본 값은 데이터베이스에서 설정한 트랜잭션 격리 수준을 사용하는 DEFAULT이다.
대부분 데이터베이스에서 설정한 기준을 따른다.
- DEFAULT : 데이터베이스에서 설정한 격리 수준을 따른다
- READ_UNCOMMITTED : 커밋되지 않은 읽기
- READ_COMMITTED : 커밋된 읽기
- REPEATABLE_READ : 반복 가능한 읽기
- SERIALIZABLE : 직렬화 가능

### readOnly
readOnly=true 옵션을 사용하면 읽기 전용 트랜잭션이 생성된다. 이 경우 등록, 수정, 삭제가 안되고 읽기 기능만 작동한다
readOnly 옵션을 크게 3곳에서 적용된다
- 프레임워크
  - JdbcTemplate은 읽기 전용 트랜잭션 안에서 변경 기능을 실행하면 예외를 던진다
  - JPA(하이버네이트)는 읽기 전용 트랜잭션의 경우 커밋 시점에 플러시를 호출하지 않는다. 읽기 전용이니 변경에 사용되는 플러시를 호출할 필요가 없다. 추가로 변경이 필요 없으니 변경 감지를 위한 스냅샨 객체도 생성하지 않는다

- JDBC 드라이버
  - 읽기 전용 트랜잭션에서 변경 쿼리가 발생하면 예외를 던진다
  - 읽기, 쓰기(마스터, 슬레이브) 데이터베이스를 구분해서 요청한다. 읽기 전용 트랜잭션의 경우 읽기(슬레이브) 데이터베이스의 커넥션을 획득해서 사용한다
- 데이터베이스
  - 데이터베이스에 따라 읽기 전용 트랜잭션의 경우 읽기만 하면 되므로, 내부에서 성능 최적화가 발생한다

## 예외와 트랜잭션 커밋, 롤백 
예외 발생시 스프링 트랜잭션 AOP는 예외의 종류에 따라 트랜잭션을 커밋하거나 롤백한다
- 언체크 예외인 RuntimeException, Error와 그 하위 예외가 발생하면 트랜잭션을 롤백한다
- 체크 예외인 Exception과 그 하위 예외가 발생하면 트랜잭션을 커밋한다
- 물론 정상 응답(리턴)하면 트랜잭션을 커밋한다

### 트랜잭션이 커밋되었는지 롤백되었는지 로그를 통해 확인
```properties
logging.level.org.springframework.transaction.interceptor=TRACE
logging.level.org.springframework.jdbc.datasource.DataSourceTransactionManager=DEBUG
#JPA log
logging.level.org.springframework.orm.jpa.JpaTransactionManager=DEBUG
logging.level.org.hibernate.resource.transaction=DEBUG
```
### 테이블 자동 생성
- application.properties : spring.jpa.hivernate.ddl-auto 으로 조정
  - none : 테이블을 생성하지 않는다
  - create : 애플리케이션 시작 시점에 테이블을 생성한다
