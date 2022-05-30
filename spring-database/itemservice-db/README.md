## 데이터 접근 기술 - 시작
### DTO(Data Transfer Object)
- 데이터 전송 객체
- DTO는 기능은 없고 데이터를 전달만 하는 용도로 하용되는 객체

#### DTO는 어디에 두는 것이 좋을까
- DTO를 사용하는 가장 마지막 Layer에 놓는것이 좋다
- Repository나 Service에 마지막 계층에서 사용하는 쪽에 놓으면 됨

### @EventListener(ApplicationReadyEvent.class)
```java
public class TestDataInit {
    private final ItemRepository itemRepository;
    
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
```
- 스프링 컨테이너가 완전히 초기화를 다 끝내고, 실행 준비가 되었을 때 발생하는 이벤트, 스프링이 이 시점에 해당 애노테이션이 붙은 initData() 메서드를 호출해준다
  - 참고로 이 기능 대신 @PostConstruct를 사용할 경우 AOP 같은 부분이 아직 다 처리되지 않은 시점에 호출될 수 있기 때문에 간혹 문제가 발생할 수 있다. 예를 들어서 @Transactional과 관련된 AOP가 적용되지 않은 상태로 호출될 수 있다
  - @EventListener(ApplicationReadyEvent.class)는 AOP를 포함한 스프링 컨테이너가 완전히 초기화 된 이후에 호출되기 때문에 이런 문제가 발생하지 않는다.

### 권장하는 식별자 선택 전략
데이터베이스 기본키는 다음 3가지 조건을 모두 만족해야 한다.
1. null값은 허용하지 않는다
2. 유일해야 한다
3. 변해선 안된다

테이블의 기본키를 선택하는 전략은 2가지가 있다
- 자연키(natual key)
  - 비즈니스에 의미가 있는 키
  - 예 : 주민등록번호, 이메일, 전화번호
- 대리키(surrogate key)
  - 비즈니스와 관련 없는 임의로 만들어진 키, 대체 키로도 불린다
  - 예 : 오라클 시퀀스, auto_increment, identity, 키 생성 테이블 사용
