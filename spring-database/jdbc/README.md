## 데이터베이스에서의 트랜잭션
- 하나의 거래를 안전하게 처리하도록 보장해주는 것

## 트랜잭션 ACID
트랜잭션은 원자성(Atomicity), 일관성(Consistency), 격리성(Isolation), 지속성(Durability)을 보장해야 한다
- 원자성 : 트랜잭션 내에서 실행한 작업들은 마치 하나의 작업인 것처럼 모두 성공하거나 모두 실패해야 함
- 일관성 : 모든 트랜잭션은 일관성 있는 데이터베이스 상태를 유지해야 한다. 예를 들어 데이터베이스에서 정한 무결성 제약 조건을 항상 만족해야 한다.
- 격리성 : 동시에 실행되는 트랜잭션들이 서로에게 영향을 미치지 않도록 격리한다. 예를 들어 동시에 같은 데이터를 수정하지 못하도록 해야 한다. 격리성은 동시성과 관련된 성능 이슈로 인해 트랜잭션 격리 수준(Isolation Level)을 선택할 수 있다.
- 지속성 : 트랜잭션을 성공적으로 끝내면 그 결과가 항상 기록되어야 한다. 중간에 시스템에 문제가 발생해도 데이터베이스 로그 등을 사용해서 성공한 트랜잭션 내용을 복구해야 한다.

## 트랜잭션 격리 수준 - Isolation level
- READ UNCOMMITED(커밋되지 않은 읽기)
- READ COMMITTED(커밋된 읽기)
- REPEATABLE READ(반복 가능한 읽기)
- SERIALIZABLE(직렬화 기능)

## 자동커밋과 수동 커밋
- 자동 커밋 : 각각의 쿼리 실행 직후에 자동으로 커밋을 호출, 때문에 원하는 트랜잭션 기능을 제대로 활용할 수 없다
- 수동 커밋 : 트랜잭션 기능을 제대로 수행하려면 수동 커밋을 사용해야 한다. 보통 자동 커밋 모드가 기본으로 설정된 경우가 많기 때문에 수동 커밋 모드로 설정하는 것을 트랜잭션을 시작한다고 표현할 수 있다.
```properties
set autocommit true(false);
```
## DB Lock
### 세션 락 타임아웃
```properties
SET LOCK_TIMEOUT <milliseconds>
```

### 조회시에 Lock을 걸고 싶을 경우
```sql
select * from member where member_id='memberA' for update;
```

### 스프링의 트랜잭션 추상화
- PlatformTransactionManager : 스프링 트랜잭션 추상화의 핵신
- 여러 트랜잭션 관리 클래스가 위 PlatformTransactionManager를 구현
  - DataSourceTransactionManager : JDBC트랜잭션 관리
  - JpaTransactionManager : JPA 트랜잭션 관리
  - HibernateTransactionManager : 하이버네이트 트랜잭션 관리

```java
public interface PlatformTransactionManager extends TransactionManager {
	TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
	void commit(TransactionStatus status) throws TransactionException;
	void rollback(TransactionStatus status) throws TransactionException;
}
```
### 스프링의 트랜잭션 동기화
스프링은 쓰레드 로컬(ThreadLocal)을 사용해서 커넥션을 동기화해준다. 트랜잭션 매매니저는 내부에서 이 트랜잭션 동기화 매니저를 사용한다
1. 트랜잭션을 시작하려면 커넥션이필요하다. 트랜잭션 매니저는 데이터소스를 통해서 커넥션을 만들고 트랜잭션을 시작한다.
2. 트랜잭션 매니저는 트랜잭션이 시작된 커넥션을 트랜잭션 동기화 매니저에 보관한다.
3. 리포지토리는 트랜잭션 동기화 매니저에 보관된 커넥션을 꺼내서 사용한다. 따라서 파라미터로 커넥션을 전달하지 않아도 된다
4. 트랜잭션이 종료되면 트랜잭션 매니저는 트랜잭션 동기화 매니저에 보관된 커넥션을 통해 트랜잭션을 종료하고 커넥션을 닫는다.

#### 트랜잭션 동기화 매니저
```java
org.springframework.transaction.support.TransactionSynchronizationManager
```
- 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
- DataSourceUtils.getConnection()
  - 트랜잭션 동기화 매니저가 관리하는 커넥션이 있으면 해당 커넥션을 반환
  - 트랜잭션 동기화 매니저가 관리하는 커넥션이 없는 경우 새로운 커넥션을 생성해서 반환
- DatasourceUtils.releaseConnection()
  - 트랜잭션을 사용하기 위해 동기화된 커넥션은 커넥션을 닫지 않고 그대로 유지
  - 트랜잭션 동기화 매니저가 관리하는 커넥션이 없는 경우 해당 커넥션을 닫는다

### JDBC TransactionManager
- private final PlatformTransactionManager DataSourceTransactionManager
  - JDBC 기술을 사용할때 사용하는 트랜잭션 매니저
- getTransaction()
  - 트랜잭션을 시작한다
  - TransactionStatus status를 반환한다. 현재 트랜잭션의 상태 정보가 포함되어 있다. 이후 트랜잭션을 커밋, 롤백할 때 필요하다
- new DefaultTransactionDefinition()
  - 트랜잭션과 관련된 옵션을 지정할 수 있다
- commit(status), rollback(status)
  - 커밋, 롤백 관련 매서드

### 트랜잭션 템플릿
스프링은 TransactionTEmplate라는 템플릿 클래스를 통해서 템플릿 콜백 패턴을 제공한다

```java
import java.util.function.Consumer;

public class TransactionTemplate {
    private PlatformTrancationManaver trancationManaver;

    public <T> execute(TransactionCallback<T> action) {
    }

    void executeWithoutResult(Consumer<TRansactionStatus> action){
    }
}
```
- execute() : 응답 값이 있을때 사용한다
- executeWithoutResult() : 응답 값이 없을때 사용한다
