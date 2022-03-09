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

## Part7. Optional
### Optional - 있을수도 있고 없을수도 있다
### Optional 만드는 법
- of : Null이 아닌 오브젝트를 이용해 Optional을 만들 때
- Empty : 빈 Optional을 만들 때
- ofNullable : Null인지 아닌지 알 지 못하는 오브젝트로 Optional을 만들 때
### Optional 안에 있는 값을 확인하고 꺼내는 법
- isPresent : 안의 오브젝트가 null인지 아닌지 체크, Null이 아닐 시 true
- get : Optional 안의 값을 추출, Null이라면 에러
- orElse : Optional이 null이 아니라면 Optional안의 값을, null이라면 other로 공급된 값을 리턴
- orElseGet : Optional이 null이 아니라면 Optional 안의 값을, null이라면 exceptionSupplier로 공급되는 exception을 던짐

### Optional 응용을 위해 알아야 할 것들
- ifPresent : Optional이 null이 아니라면 action을 실행
- map : Optional이 null이 mapper를 적용
- flatMap : mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌

## Part8. Advanced Stream
### Max / Min / Count - Stream안의 데이터에 최대값 / 최소값 / 개수
- max : Stream 안의 데이터 중 최대값을 반환. Stream이 비어있다면 빈 Optional을 반환
- min : Stream 안의 데이터 중 최소값을 반환. Stream이 비어있다면 빈 Optioanl을 반환
- count : Stream 안의 데이터의 개수를 반환

### All Match / Any Match
- allMatch : Stream 안의 모든 데이터가 predicate을 만족하면 true
- anyMatch : Stream 안의 데이터 중 하나라도 predicate을 만족하면 true

### Find First / Find Any
- findFirst : Stream 안의 첫번째 데이터를 반환. Stream이 비어있다면 비어있는 Optional을 반환
- findAny : Stream 안의 아무 데이터나 리턴. 순서가 중요하지 않고 Parallel Stream을 사용할 때 최적화를 할 수 있다. 마찬가지로 Stream이 비어있다면 빈 Optional을 반환

### Reduce
```java
Optional<T> reduce(BinaryOperator<T> accumulator);
```
- 주어진 accumulator를 이용해 데이터를 합침. Stream이 비어있을 경우 빈 Optional을 반환

```java
T reduce(U identity, BinaryOperator<T> accumulator);
```
- 주어진 초기값과 accumulator를 이용. 초기값이 있기 때문에 항상 반환값이 존재

```java
<U> U reduce(U identity, Bifunction<U, ? super T, U> accumulator, BinaryOperator<T> combiner);
```
- 합치는 과정에서 타입이 바뀔 경우 사용. Map + reduce로 대체 가능

### To Map
- Stream 안의 데이터를 map의 형태로 반환해주는 collector
- keyMapper : 데이터를 map의 key로 변환하는 Function
- valueMapper : 데이터를 map의 value로 변환하는 Function

### Grouping by
- Stream 안의 데이터에 classifier를 적용했을 때 결과값이 같은 값끼리 List로 모아서 Map의 형태로 반환해주는 Collector. 이때 key는 classifier의 결과값, value는 그 결과값을 갖는 데이터들
- 예를 들어 stream에 {1, 2, 5, 7, 9, 12, 13}이 있을 때 classifier가 x -> x % 3이라면 반환되는 map은 {0=[9, 12], 1=[1, 7, 13], 2=[2, 5]}
- 두 번째 매개변수로 downstream collector를 넘기는 것도 가능
- 그 경우 List 대신 collector를 적용시킨 값으로 map의 value가 만들어짐
- 이 때 자주 쓰이는 것이 mapping / reducing 등의 collector

### Partitioning By
```java
public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitionaingBy(Predicate<? super T> predecate)

public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream)
```
- GroupingBy와 유사하지만 Function 대신 Predicate을 받아 true와 false 두 key가 존재하는 map을 반환하는 collector
- 마찬가지로 downstream collector를 넘겨 List 이외의 형태로 map의 value를 만드는 것 역시 가능

### For Each
```java
void forEach(Consumer<? super T> action);
```
- 제공된 action을 Stream의 각 데이터에 적용해주는 종결 처리 메서드
- Java의 Iterable 인터페이스에도 forEach가 있기 때문에 Stream의 중간처리가 필요 없다면 iterable collection(Set, List 등)에서 바로 쓰는것도 가능

### Parallel Stream - Stream을 병렬로
```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
Stream<Integer> parallelStream = numbers.parallelStream();
Stream<Integer> parallelStream2 = numbers.stream().parallel();
```
- Sequential VS Parallel
- 여러개의 스레드를 이용하여 stream의 처리 과정을 병렬화(parallelize)
- 중간 과정은 병렬 처리 되지만 순서가 있는 Stream의 경우 종결 처리 했을때의 결과물이 기존의 순차적 처리와 일치하도록 종결 처리과정에서 조정된다. 즉 List로 Collect한다면 순서가 항상 올바르게 나온다는 것
- 장점
  - 굉장히 간단하게 병렬 처리를 사용할 수 있게 해준다
  - 속도가 비약적으로 빨라질 수 있다
- 단점
  - 항상 속도가 빨라지는 것은 아니다
  - 공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 아예 오류가 날 수도 있다(deadlock)
  - 이를 막기 위해 mutex, semaphore등 병렬 처리 기술을 이용하면 순차처리보다 느려질 수도 있다

## Part9. 함수형 프로그래밍의 응용
### 1. Scope, Closure & Curry
#### Scope (스코프/유효범위) : 변수에 접근할 수 있는 범위
  - 함수 안에 함수가 있을 때 내부 함수에서 외부 함수에 있는 변수에 접근이 가능하다(lexical scope). 그 반대는 불가능하다
#### Closure
- 내부 함수가 존재하는 한 내부 함수가 사용한 외부 함수의 변수들 역시 계속 존재한다. 이렇게 lexical scope를 포함하는 함수를 closure라 한다.
- 이 때 내부 함수가 사용한 외부 함수의 변수들은 내부 함수 선언 당시로부터 변할 수 없기 때문에 final로 선언되지 않더라도 암묵적으로 final로 취급된다
#### Curry
- 여러 개의 매개변수를 받는 함수를 중첩된 여러 개의 함수로 쪼개어 매개 변수를 한 번에 받지 않고, 여러 단계에 걸쳐 나눠 받을 수 있게 하는 기술
- Closure의 응용

### Lazy Evaluation
- Lambda의 계산은 그 결과값이 필요할 때가 되어서야 계산된다
- 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 실행 순서를 의도적으로 미룰 수 있다

### Function Composition - 함수 합성
- 여러 개의 함수를 합쳐 하나의 새로운 함수로 만드는 것
```java
<V> Function<T, R> compose(Function<? super V, ? extends T> before)
<V> Function<T, V> andThen(Function<? super R, ? extends V> after)
```

## Part10. 함수형 프로그래밍을 이용한 디자인 패턴
### 1. Design Pattern - 디자인 패턴이란
- 반복해서 등장하는 프로그래밍 문제들에 대한 해법들을 패턴화 해놓은 것
- 패턴들을 숙지해놓으면 비슷한 문제가 생겼을 때 패턴들이 이정표가 되어준다
#### Design Pattern - 디자인 패턴의 종류
- 생성 패턴(Creational Patterns) : 오브젝트의 생성에 관련된 패턴
- 구조 패턴(Structural Patterns) : 상속을 이용해 클래스/오브젝트를 조합하여 더 발전된 구조로 만드는 패턴
- 행동 패턴(Behavioral Patterns) : 필요한 작업을 여러 객체에 분배하여 객체간 결합도를 줄이게 해주는 패턴

### 2. Builder Pattern
- 대표적인 생성 패턴
- 객체의 생성에 대한 로직과 표현에 대한 로직을 분리해준다
- 객체의 생성 과정을 유연하게 해준다
- 객체의 생성 과정을 정의하고 싶거나 필드가 많아 constructor가 복잡해질 때 유용

### 3. Decorator Pattern
- 구조 패턴의 하나
- 용도에 따라 객체에 기능을 계속 추가(decorate)할 수 있게 해준다

### 4. Strategy Pattern
- 대표적인 행동 패턴
- 런타임에 어떤 전략(알고리즘)을 사용할 지 선택할 수 있게 해준다
- 전략들을 캡슐화 하여 간단하게 교체할 수 있게 해준다

### 5. Template Method Pattern
- 또 하나의 대표적인 행동 패턴
- 상위 클래스는 알고리즘의 뼈대만을 정의하고 알고리즘의 각 단계는 하위 클래스에게 정의를 위임하는 패턴
- 알고리즘의 구조를 변경하지 않고 세부 단계들을 유연하게 변경할 수 있게 해준다
