## ORM

- Object-relational mapping(객체 관계 매핑)
- 객체는 객체대로 설계
- 관계형 데이터베이스는 관계형 데이터베이스대로 설계
- ORM 프레임워크가 중간에서 매핑
- 대중적인 언어에는 대부분 ORM 기술이 존재

## JPA

- Java Persistence API
- 자바 진영의 ORM 기술 표준
- 인터페이스의 모음
- 하이버네이트, EclipseLink, DataNucleus

### JPA의 성능 최적화 기능
- 1차 캐시와 동일성(identity) 보장
  - 같은 트랜잭션 안에서는 같은 엔티티를 반환 - 약간의 조회 성능 향상
  - DB Isolation Level이 Read Commit이어도 애플리케이션에서 Repeatable Read 보장
- 트랜잭션을 지원하는 쓰기 지연 - INSERT
  - 트랜잭션을 커밋할 때까지 INSERT SQL을 모음
  - JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송
```java
transaction.begin(); // [트랜잭션] 시작
        
em.persist(memberA);
em.persist(memberB);
em.persist(memberC);
//여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
        
//커밋하는 순간 데이터베이스에 INSERT SQL을 모아서 보낸다.
transaction.commit(); // [트랜잭션] 커밋
```
- 트랜잭션을 지원하는 쓰기 지연 - UPDATE
  - UPDATE, DELETE로 인한 ROW LOCK 시간 최소화
  - 트랜잭션 커밋 시 UPDATE, DELETE SQL 실행하고 바로 커밋
```java
transaction.begin(); // [트랜잭션] 시작
 
changeMember(memberA);
deleteMember(memberB);
비즈니스_로직_수행(); //비즈니스 로직 수행 동안 DB 로우 락이 걸리지 않는다.
        
//커밋하는 순간 데이터베이스에 UPDATE, DELETE SQL을 보낸다.
transaction.commit(); // [트랜잭션] 커밋
```

- 지연 로딩(Lazy Loading)
  - 지연 로딩 : 객체가 실제 사용될 때 로딩
  - 즉시 로딩 : JOIN SQL로 한번에 연관된 객체까지 미리 조회
```java

```

### 데이터베이스 방언
- JPA는 특정 데이터베이스에 종속되지 않음
- 각각의 데이터베이스가 제공하는 SQL 문법과 함수는 조금씩 다름
- 방언 : SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능
- hibernate.dialect 속성에 지정
  - H2 : org.hibernate.dialect.H2Dialect
  - Oracle : org.hibernate.dialect.OraclexxDialect
  - H2 : org.hibernate.dialect.MySQLxxxDialect
- Hibernate는 40가지 이상의 데이터베이스 방언 지원

## JPA에서 가장 중요한 2가지
- 객체와 관계형 데이터베이스 매핑하기(Object Relational Mapping)
- 영속성 컨텍스트

### 영속성 컨텍스트
- JPA를 이해하는데 가장 중요한 용어
- 엔티티를 영구 저장하는 환경이라는 뜻
- 영속성 컨텍스트는 논리적인 개념
- EntityManager를 통해서 영속성 컨텍스트에 접근

### 엔티티의 생명주기
- 비영속(new/transient) : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속(managed) : 영속성 컨텍스트에 관리되는 상태
- 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제(removed) : 삭제된 상태

### 영속성 컨텍스트의 이점
- 1차 캐시
```java
// 엔티티를 생성한 상태(비영속)
Member member = new Member();
member.setId(1L);
member.setUsername("hong");

// 엔티티를 영속, 1차 캐시에 저장됨
em.persist(member);
```
- 동일성(identity) 보장
  - 1차 캐시로 반복 가능한 읽기(REPEATABLE READ) 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공
```java
Member member1 = em.find(Member.class, 1L);
Member member2 = em.find(Member.class, 1L);

// 동일성 비교 true
System.out.println(member1 == member2);
```

- 트랜잭션을 지원하는 쓰기 지연
```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

// 엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다
tx.begin(); // 트랜잭션 시작

Member member1 = new Member();
member1.setId(1L);
member1.setUsername("hong");

Member member2 = new Member();
member2.setId(2L);
member2.setUsername("hong2");

em.persist(member1);
em.persist(member2);
// 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다

tx.commit();
// 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다
```
- 변경 감지(Dirty Checking)
```java
// 영속 엔티티 조회
Member findMember1 = em.find(Member.class, 1L);
            
// 영속 엔티티 데이터 수정
findMember1.setUsername("hong2");

em.merge(findMember1); // 이렇게 코드를 날리지 않아도 된다
            
tx.commit();
```
- 지연 로딩(Lazy Loading)

------------------------------------------------------------------------------

## JPQL

### JPA는 다양한쿼리 방법을 지원

- JPQL
- JPA Criteria
- QueryDSL
- 네이티브 SQL
- JDBC API 직접 사용, MyBatis, SpringJdbcTemplate 함꼐 사용

### JPQL

- JPA를 사용하면 엔티티 객체를 중심으로 개발
- 문제는 검색 쿼리
- 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
- 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
- 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색조건이 포함된 SQL이 필요
- JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
- SQL과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN
- JPQL을 엔티티 객체를 대상으로 쿼리
- SQL은 데이터베이스 테이블을 대상으로 쿼리

```java
String sql = "select m from Member m where m.name like '%hello%';
List<Member> result = em.createQuery(sql, Member.class).getResultList();
```

- 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
- SQL을 추상화 해서 특정 데이터베이스 SQL에 의존X
- JPQL을 한마디로 정의하면 객체 지향 SQL

## Criteria

```java
CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
CriteriaQuery<Member> query = criteriaBuilder.createQuery(Member.class);

//루트 클래스(조회를 시작할 클래스)
Root<Member> from = query.from(Member.class);

//쿼리 생성
CriteriaQuery<Member> criteriaQuery = query.select(from).where(criteriaBuilder.equal(from.get("username"), "hong"));
List<Member> resultList = em.createQuery(criteriaQuery).getResultList();
```

- 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
- JPQL 빌더 역할
- JPA 공식 기능
- 단점 : 너무 복잡하고 실용성이 없다
- Criteria 대신에 QueryDSL 사용 권장

## QueryDSL

## NativeSQL
