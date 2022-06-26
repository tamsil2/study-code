## 타임리프 - 기본 기능
### 타임리프 특징
- 서버 사이드 HTML 렌더링(SSR)
- 네츄럴 템플릿
- 스프링 통합 지원

### 타임리프 기본 기능
- 타임리프 사용 선언
```html
<html xmlns:th="http://www.thymeleaf.org"></html>
```

## 텍스트 - text, utext
HTML의 콘텐츠에 데이터를 출력
```html
<span th:text="${data}"></span>
```

HTML 태그의 속성이 아니라 HTML 콘텐츠 영역 안에서 직접 데이터를 출력하고 싶으면 다음과 같이 [[...]]를 사용하면 된다
```html
[[${data}]]
```

### Escape
- HTML 엔티티
  - 웹 브라우저는 <를 HTML 태그의 시작으로 인식한다. 따라서 <를 태그의 시작이 아니라 문자로 표현할 수 있는 방법이 필요한데 이것을 HTML 엔티티라 한다
  - HTML에서 사용하는 특수 문자를 HTML 엔티티로 변경하는 것을 이스케이프(escape)라 한다
- < -> &lt;
- > -> &gt;

### Unescape
타임리프는 다음 두 기능을 제공한다
- th:text -> th:utext
- [[...]] -> [(..)]

## 변수 - SpringEL
타임리프에서 변수를 사용할때는 변수 표현식을 사용할수 있다
- 변수 표현식 : ${...}

SpringEL 다양한 표현식 사용

Object
- user.username : user의 username을 프로퍼티 접근
- user['username'] : 위와 같음 user.getUsername()
- user.getUsername() : user의 getUsername() 을 직접 호출

List
- users[0].username : List에서 첫 번째 회원을 찾고 username 프로퍼티 접근 list.get(0).getUsername()
- users[0]['username'] : 위와 같음
- users[0].getUsername() : List에서 첫 번째 회원을 찾고 메서드 직접 호출
- user.getUsername()

Map
- userMap['userA'].username : Map에서 userA를 찾고, username 프로퍼티 접근 map.get("userA").getUsername()
- userMap['userA']['username'] : 위와 같음
- userMap['userA'].getUsername() : Map에서 userA를 찾고 메서드 직접 호출

### 지역변수 선언
```html
<h1>지역 변수 - {th:with}</h1>
<div th:with="first=${users[0]}">
    <p>처음 사람의 이름은 <span th:text="${first.username}"></span></p>
</div>
```

## 기본 객체들
### 타임리프가 제공하는 기본객체
- ${#request}
- ${#response}
- ${#session}
- ${#servletContext}
- ${#locale}

### 타임리프가 제공하는 편의객체
- HTTP 요청 파라미터 접근 : param
  - ${param.paramData}
- HTTP 세션 접근 : session
  - ${session.sessionData}
- 스프링 빈 접근 : @
  - ${@helloBean.hello('Spring')}

## 유틸리티 객체와 날짜
타임리프는 문자, 숫자, 날짜, URI 등을 편리하게 다루는 다양한 유틸리티 객체들을 제공한다

타임리프 유틸리티 객체들
- #message : 메시지, 국제화 처리
- #uris : URI 이스케이프 지원
- #dates : java.util.Date 서식 지원 #calendars : java.util.Calendar 서식 지원 #temporals : 자바8 날짜 서식 지원
- #numbers : 숫자 서식 지원
- #strings : 문자 관련 편의 기능
- #objects : 객체 관련 기능 제공
- #bools : boolean 관련 기능 제공
- #arrays : 배열 관련 기능 제공
- #lists , #sets , #maps : 컬렉션 관련 기능 제공 #ids : 아이디 처리 관련 기능 제공, 뒤에서 설명

타임리프 유틸리티 객체 레퍼런스
[유틸리티레퍼런스](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-utility-objects)

타임리프 유틸리티 예시 레퍼런스
[유틸리티예시](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression-utility-objects)

### 자바8 날짜
타임리프에서 자바8 날짜인 LocalDate, LocalDateTime, Instant를 사용하려면 추가 라이브러리가 필요하다. 스프링 부트 타임리프를 사용하면 해당 라이브러리가 자동으로 추가되고 통합된다.

- 타임리프 자바8 날짜 지원 라이브러리 : thymeleaf-extras-java8time
- 자바8 날짜용 유틸리티 객체 : @temporals

## URL 링크
타임리프에서 URL을 생성할 때는 @{...} 문법을 사용하면 된다.

단순한 URL
- @{/hello} -> /hello

쿼리 파라미터
- @{/hello(param1=${param1}, param2=${param2})}
  - /hello?param1=data1&param2=data2
  - ()에 있는 부분은 쿼리 파라미터로 처리된다

경로 변수
- @{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}
  - /hello/data1/data2
  - URL 경로상에 변수가 있으면 () 부분은 경로 변수로 처리된다

경로변수 + 쿼리 파라미터
- @{/hello/{param1}(param1=${param1}, param2=${param2})}
  - /hello/data1?param2=data2
  - 경로 변수와 쿼리 파라미터를 함꼐 사용할 수 있다

[링크 참고](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#link-urls)

## 리터럴
