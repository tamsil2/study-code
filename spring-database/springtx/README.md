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
#JPA SQL
logging.level.org.hibernate.SQL=DEBUG
```
### 테이블 자동 생성
- application.properties : spring.jpa.hivernate.ddl-auto 으로 조정
  - none : 테이블을 생성하지 않는다
  - create : 애플리케이션 시작 시점에 테이블을 생성한다

# 스프링 트랜잭션 전파
트랜잭션이 이미 진행중인데 여기에 추가로 트랜잭션을 수행하는 경우 어떻게 동작할지 결정하는 것을 트랜잭션 전파(propagation)라 한다
스프링은 다양한 트랜잭션 전파 옵션을 제공한다
- 외부 트랜잭션은 상대적으로 밖에 있기 때문에 외부 트랜잭션이라 한다. 처음 시작된 트랜잭션으로 이해하면 된다
- 내부 트랜잭션은 외부에 트랜잭션이 수행되고 있는 도중에 호출되기 때문에 마치 내부에 있는 것처럼 보여서 내부 트랜잭션이라 한다
- 스프링은 외부 트랜잭션과 내부 트랜잭션을 묶어 하나의 트랜잭션을 만들어준다

## 물리 트랜잭션, 논리 트랜잭션
- 스프링은 이해를 돕기 위해 논리 트랜잭션과 물리 트랜잭션이라는 개념을 나눈다
- 논리 트랜잭션들은 하나의 물리 트랜잭션으로 묶인다
- 물리 트랜잭션은 우리가 실제 데이터베이스에 적용되는 트랜잭션을 뜻한다. 실제 커넥션을 통해서 트랜잭션을 시작(setAutoCommit(false))하고, 실제 커넥션을 통해서 롤백하는 단위이다
- 논리 트랜잭션은 트랜잭션 매니저를 통해 트랜잭션을 사용하는 단위이다

## 원칙
- 모든 논리 트랜잭션이 커밋되어야 물리 트랜잭션이 커밋된다
- 하나의 논리 트랜잭션이라도 롤백되면 물리 트랜잭션은 롤백된다

## 실행 결과
- 내부 트랜잭션을 시작할때 Participating in existing transaction 이라는 메시지를 확인할 수 있다. 이 메시지는 내부 트랜잭션이 기존에 존재하는 외부 트랜잭션에 참여한다는 뜻이다
- 실행 결과를 보면 외부 트랜잭션을 시작하거나 커밋할 때는 DB 커넥션을 통한 물리 트랜잭션을 시작(manual commit)하고, DB 커넥션을 통해 커밋하는 것을 확인할 수 있다.
- 외부 트랜잭션만 물리 트랜잭션을 시작하고 커밋한다
- 스프링은 이렇게 여러 트랜잭션이 함께 사용되는 경우, 처음 트랜잭션을 시작한 외부 트랜잭션이 실제 물리 트랜잭션을 관리하도록 한다. 이를 통해 트랜잭션 중복 커밋 문제를 해결한다

[트랜잭션 요청흐름-외부 트랜잭션](./image/transaction1.png)
## 요청 흐름 - 외부 트랜잭션
1. transactionManager.getTransaction() 를 호출해서 외부 트랜잭션을 시작한다
2. 트랜잭션 매니저를 데이터소스를 통해 커넥션을 생성한다
3. 생성한 커넥션을 수동 커밋 모드로 설정한다
4. 트랜잭션 매니저는 트랜잭션 동기화 매니저에 커넥션을 보관한다
5. 트랜잭션 매니저는 트랜잭션을 생성한 결과를 TransactionStatus에 담아서 반환하는데, 여기에 신규 트랜잭션의 여부가 담겨 있다. isNewTransaction 를 통해 신규 트랜잭션 여부를 확인할 수 있다. 트랜잭션을 처음 시작했으므로 신규 트랜잭션이다.(true)
6. 로직1이 사용되고, 커넥션이 필요한 경우 트랜잭션 동기화 매니저를 통해 트랜잭션이 적용된 커넥션을 획득해서 사용한다

[트랜잭션 요청흐름-내부 트랜잭션](./image/transaction2.png)
## 요청 흐름 - 내부 트랜잭션
7. transactionManager.getTransaction() 를 호출해서 내부 트랜잭션을 시작한다
8. 트랜잭션 매니저는 트랜잭션 동기화 매니저를 통해서 기존 트랜잭션이 존재하는지 확인한다
9. 기존 트랜잭션이 존재하므로 기존 트랜잭션에 참여한다. 기존 트랜잭션에 참여한다는 뜻은 아무것도 하지 않는다는 뜻이다
10. 트랜잭션 매니저를 트랜잭션을 생성한 결과를 TransactionStatus에 담아서 반환하느나데, 여기에서 isNewTransaction 를 통해 신규 트랜잭션 여부를 확인할 수 있다. 여기서는 기존 트랜잭션에 참여했기 때문에 신규 트랜잭션이 아니다(false)
11. 로직2가 사용되고 커넥션이 필요한 경우 트랜개션 동기화 매니저를 통해 외부 트랜잭션이 보관한 커넥션을 획득해서 사용한다

## 응답 흐름 - 내부 트랜잭션
12. 로직2가 끝나고 트랜잭션 매니저를 통해 내부 트랜잭션을 커밋한다
13. 트랜잭션 매니저는 커밋 시점에 신규 트랜잭션 여부에 따라 다르게 동작한다. 이 경우 신규 트랜잭션이 아니기 때문에 실제 커밋을 호출하지 않는다.

## 응답 흐름 - 외부 트랜잭션
14. 로직1이 끝나고 트랜잭션 매니저를 통해 외부 트랜잭션을 커밋한다
15. 트랜잭션 매니저는 커밋 시점에 신규 트랜잭션 여부에 따라 다르게 동작한다. 외부 트랜잭션은 신규 트랜잭션이다. 따라서 DB 커넥션에 실제 커밋을 호출한다
16. 트랜잭션 매니저에 커밋하는 것이 논리적인 커밋이라면, 실제 커넥션에 커밋하는 것을 물리 커밋이라고 할수 있다. 실제 데이터베이스에 커밋이 반영되고 물리 트랜잭션도 끝난다

# 스프링 트랜잭션 전파 - 롤백
- 논리 트랜잭션이 하나라도 롤백되면 물리 트랜잭션을 롤백된다
- 내부 논리 트랜잭션이 롤백되면 롤백 전용 마크를 표시한다
- 외부 트랜잭션을 커밋할 때 롤백 전용 마크를 확인한다. 롤백 전용 마크가 표시되어 있으면 물리 트랜잭션을 롤백하고, UnexpectedRollbackException 예외를 던진다

# 스프링 트랜잭션 전파 - REQUIRES_NEW
- 물리 트랜잭션을 분리하려면 내부 트랜잭션을 시작할때 REQUIRES_NEW 옵션을 사용하면 된다
- 외부 트랜잭션과 내부 트랜잭션이 각각 별도의 물리 트랜잭션을 가진다
- 별도의 물리 트랜잭션을 가진다는 뜻은 DB 커넥션을 따로 사용한다는 뜻이다
- REQUIRES_NEW 옵션을 사용하면 물리 트랜잭션이 명확하게 분리된다
- REQUIRES_NEW를 사용하면 데이터베이스 커넥션이 동시에 2개 사용된다는 점을 주의해야 한다

# 스프링 트랜잭션 전파 - 다양한 전파 옵션
실무에서는 대부분 REQUIRED 옵션을 사용한다. 그리고 가끔 REUIRES_NEW 을 사용하고 나머지는 거의 사용하지 않는다

## REQUIRED
- 가장 많이 사용하는 기본 설정이다. 기존 트랜잭션이 없으면 생성하고, 있으면 참여한다
- 트랜잭션이 필수라는 의미로 해석하면 된다
- 기존 트랜잭션 없음 : 새로운 트랜잭션을 생성한다
- 기존 트랜잭션 있음 : 기존 트랜잭션에 참여한다

## REQUIRES_NEW
- 항상 새로운 트랜잭션을 생성한다
- 기존 트랜잭션 없음 : 새로운 트랜잭션을 생성한다
- 기존 트랜잭션 있음 : 새로운 트랜잭션을 생성한다

# 스프링 트랜잭션 전파2 - 활용
## @Transactional과 REQUIRED
- 트랜잭션 전파의 기본 값은 REQUIRED이다. 따라서 다음 둘은 같다
  - @Transactional(propagation = Propagation.REQUIRED)
  - @Transactional
- REQUIRED는 기존 트랜잭션이 없으면 새로운 트랜잭션을 만들고, 기존 트랜잭션이 있으면 참여한다

## 트랜잭션 전파 커밋
스프링은 @Transactinal이 적용되어 있으면 기본으로 REQUIRED라는 전파 옵션을 사용한다.
