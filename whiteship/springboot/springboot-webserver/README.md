## 스프링 부트는 웹서버가 아니다
- 톰캣 객체 생성
- 포트 설정
- 톰캣에 컨텍스트 추가
- 서블릿 생성
- 톰캣에 서블릿 추가
- 컨텍스트에 서블릿 매핑
- 톰캣 실행 및 대기

## 스프링부트 웹서버 사용 종류
- Tomcat, Undertow, Jetty
## 스프링 부트 기본 실행시 embedded tomcat이 내장

## 프로그래밍적인 방법으로 톰캣 서버 생성 및 띄우기
```java
public class Application {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("/", "/");
        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("<html><head><title>");
                writer.println("Hello World");
                writer.println("</title></head>");
                writer.println("<body><h1><Hello Tomcat</h1><body>");
                writer.println("</html>");
            }
        };
        String servletName = "helloServlet";
        tomcat.addServlet("/", servletName, servlet);
        context.addServletMappingDecoded("/hello", servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}
```
## 스프링 부트 자동설정
- 스프링부트 실행시 자동설정으로 설정이 다 되서 톰캣이 만들어지고 서블릿이 추가된다
- ServletWebServerFactoryAutoConfiguration.class : 서블릿 웹서버 생성
    - TomcatServletWebServerFactoryCustomizer.class : 서블릿 커스터마이징

```java
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnClass(ServletRequest.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@EnableConfigurationProperties(ServerProperties.class)
@Import({ ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class,
		ServletWebServerFactoryConfiguration.EmbeddedTomcat.class,
		ServletWebServerFactoryConfiguration.EmbeddedJetty.class,
		ServletWebServerFactoryConfiguration.EmbeddedUndertow.class })
public class ServletWebServerFactoryAutoConfiguration {
}
```

## TomcatServletWebServerFactory.class 안에서 톰캣을 생성

```java
public class TomcatServletWebServerFactory {
    @Override
    public WebServer getWebServer(ServletContextInitializer... initializers) {
        if (this.disableMBeanRegistry) {
            Registry.disableRegistry();
        }
        Tomcat tomcat = new Tomcat();
        File baseDir = (this.baseDirectory != null) ? this.baseDirectory : createTempDir("tomcat");
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        Connector connector = new Connector(this.protocol);
        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
        customizeConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        configureEngine(tomcat.getEngine());
        for (Connector additionalConnector : this.additionalTomcatConnectors) {
            tomcat.getService().addConnector(additionalConnector);
        }
        prepareContext(tomcat.getHost(), initializers);
        return getTomcatWebServer(tomcat);
    }
}

```

## 생성된 서블릿 컨테이너에 서블릿을 생성하여 추가해 줘야 한다
- 스프링에서는 DispatcherServlet을 생성하여 서블릿 컨테이너에 등록해 준다 
- 스프링부트에서 DispatcherSevlet을 자동설정 해주는 것이 DispatcherServletAutoConfiguration
- 이 것도 결국 HttpServlet이다
- 여기서 만들고 서블릿 컨테이너에 등록하는 일이 이뤄진다

## 서블릿 컨테이너 생성과 DispatcherServlet 생성이 왜 따로 이루어 지는지
- 서블릿 컨테이너는 다 달라질 수 있다. 서블릿은 변하지 않기 때문에 둘이 분리되어 있음
- 서블릿 컨테이너에 상관없이 디스패처 서블릿은 생성되서 컨테이너에 등록

## 서블릿 컨테이너 변경하는 방법
```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-undertow</artifactId>
		</dependency>
```

### 참고로 버전을 명시하지 않는 디펜던시의 경우는 스프링부트에서 버전관리를 해주는 라이브러리
### IntelliJ Ultimate 버전에서는 pom.xml 왼쪽에 표시되어 있음

## 스프링부트에서 웹 애플리케이션 서버 사용하지 않는 방법
- 스프링부트는 기본적으로 의존성에 웹 관련 모듈이 들어와 있으면 웹애플리케이션으로 만드려고 시도
1. 디펜던시 제거
   - springboot-starter-web 디펜던시 제거

2. 스프링부트 애플리케이션 수정
```java
new SpringApplicationBuilder(SpringbootTomcatApplication.class)
    .web(WebApplicationType.NONE)
    .run(args);
```
또는 
```java
SpringApplication application = new SpringApplication(SpringbootTomcatApplication.class);
application.setWebApplicationType(WebApplicationType.NONE);
application.run(args);
```

3. application.properties 설정
```properties
spring.main.web-application-type=none
```

## 포트 변경하는 방법
```properties
server.port=7070
```
- 위 설정에 0으로 세팅하면 랜덤 포트로 설정된다.

### 랜덤포트 확인하는 방법
```java
@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ServletWebServerApplicationContext applicationContext = servletWebServerInitializedEvent.getApplicationContext();
        System.out.println(applicationContext.getWebServer().getPort());
    }
}
```
- 서블릿 웹서버가 생성이 되면 위 이벤트리스너가 호출된다.

### HTTP Connector 추가 설정하기
- 톰캣의 경우
```java
@Bean
public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sslConnectorCustomizer() {
        return (tomcat) -> tomcat.addAdditionalTomcatConnectors(createSslConnector());
}

private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            URL keystore = ResourceUtils.getURL("keystore");
            URL truststore = ResourceUtils.getURL("truststore");
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(8443);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.toString());
            protocol.setKeystorePass("changeit");
            protocol.setTruststoreFile(truststore.toString());
            protocol.setTruststorePass("changeit");
            protocol.setKeyAlias("apitester");
            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("Fail to create ssl connector", ex);
    }
}
```
- Undertow의 경우
```java
public class UndertowConfiguration {

    @Bean
    public WebServerFactoryCustomizer<UndertowServletWebServerFactory> undertowListenerCustomizer() {
        return (factory) -> factory.addBuilderCustomizers(this::addHttpListener);
    }

    private Builder addHttpListener(Builder builder) {
        return builder.addHttpListener(8080, "0.0.0.0");
    }

}
```
