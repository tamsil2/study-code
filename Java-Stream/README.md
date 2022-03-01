## Functional Interface
### Supplier
- 공급하는 인터페이스
- 단 하나의 추상메서드를 가지며 인풋은 없고 리턴값만 가지는 인터페이스

### Consumer
- 인풋타입을 받고 아무것도 리턴하지 않는 Supplier와 반대되면 인터페이스

### BiConsumer
- 두개의 인풋을 가지는 인터페이스
- 컨수머이기 때문에 아무것도 리턴하지 않는다

### Predicate
- test 하나의 abstract 메소드를 가진다
- input을 받아서 true나 false를 리턴함

### Comparator
- Abstract Method : compare
- o1이 더 작다면 음수를 리턴, 같다면 0, o1이 더 크다면 양수를 리턴

## 메서드 레퍼런스(Method Reference)
- 기존에 이미 선언되어 있는 메소드를 지정하고 싶을때
- :: 오퍼레이터 사용
- 생략이 많기 때문에 사용할 메서드의 매개변수의 타입과 리턴 타입을 미리 숙지해야 함

### 메서드 레퍼런스의 4가지 케이스
1. 클래스의 static method를 지정할 때
    - ClassName::staticMethodName
    
2. 선언된 객체의 instance method를 지정할 때
    - objectName::instanceMethodName
    
3. 객체의 instance method를 지정할 때
    - ClassName::instanceMethodName
    
4. 클래스의 constructor를 지정할 때
    - ClassName::new
