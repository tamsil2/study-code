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

## Part5. 메서드 레퍼런스(Method Reference)
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

## Part6. Stream
### 1. Stream
- 데이터의 흐름
- 컬렉션(Collection) 형태로 구성된 데이터를 람다를 이용해 간결하고 직관적으로 프로세스하게 해줌
- For, while 등을 이용하던 기존 loop을 대체
- 손쉽게 병렬 처리를 할 수 있게 해줌

### 2. Filter
- 만족하는 데이터만 걸러내는데 사용
- Predicate에 true를 반환하는 데이터만 존재하는 stream을 리턴

### 3. Map : 데이터의 변형
- 데이터를 변형하는데 사용
- 데이터에 해당 함수가 적용된 결과물을 제공하는 stream을 리턴

### 4. Stream의 구성요소
- Source (소스) : 컬렉션, 배열 등
- Intermediate Operations(중간 처리) : 0개 이상의 filter, map 등의 중간처리
- Terminal Operation(종결 처리) : Collect, reduce 등

### 5. Sorted 데이터의 정렬
- 데이터가 순서대로 정렬된 stream을 리턴
- 데이터의 종류에 따라 Comparator가 필요할 수 있음

### 6. Distinct 중복 제거
- 중복되는 데이터가 제거된 stream을 리턴

### 7. FlatMap 스트림의 스트림을 납작하게
- Map + Flatten
- 데이터에 함수를 적용한 후 중첩된 stream을 연결하여 하나의 stream으로 리턴

### 8. 챕터6 마무리
- 스트림은 데이터의 흐름
- 여러개의 중간 처리를 연결할 수 있음
  - Filter
  - Map
  - Sorted
  - Distinct
  - FlatMap
