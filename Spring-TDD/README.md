## TDD란?

테스트 주도 개발이란 의미를 가진다. 테스트를 먼저 설계 및 구축 후에 테스트를 통과할 수 있는 코드를 작성하는 것. 코드 작성 후 테스트를 진행하는 일반적인 방식과 차이가 있다.

``애자일 개발``방식 중 하나로
-  코드 설계 시 원하는 단계적 목표에 대해 설정. 진행하고자 하는 것에 대한 결정 방향의 갭을 줄이고자 한다.
-  최초 목표에 맞춘 테스트를 구축하여 그에 맞게 코드를 설계하기 때문에 적은 의견 충돌을 기대 할 수 있다.

**작성 목적**

1. 코드의 안정성을 높일 수 있다.
2. 기능을 추가하거나 변경하는 과정에서 생기는 Side-Effect를 줄일 수 있다.
3. 해당 코드가 작성된 목적을 명확하게 표현할 수 있다.

## JUnit이란?

자바 진영의 대표적 테스트 프레임워크이다. 단위 테스트(Unit Test)를 위한 도구를 제공

> **단위 테스트**
> 코드의 특정 모듈이 의도된대로 동작하는지 테스트하는 절차를 의미한다. 모든 함수와 메소드에 대한 각각의 테스트 케이스(Test Case)를 작성하는 것

어노테이션 기반으로 테스트를 지원한다. 단정문(Assert)으로 테스트 케이스의 기대 값에 대해 수행 결과를 확인 가능하다. Spring Boot 2.2버전부터는 JUnit 5 버전을 사용한다.

JUnit은 크게 Jupiter, Platform, Vintage 모듈로 구성된다.

### JUnit 모듈

**JUnit Jupiter**

TestEngine API의 구현체로 JUnit 5를 구현하고 있다. 테스트의 실제 구현체는 별도 모듈 역할을 수행하는데, 그 모듈 중 하나가 Jupiter-Engine이다. 이 모듈은 Jupiter-API를 사용하여 작성한 테스트 코드를 발견하고 실행하는 역할을 수행한다. 개발자가 테스트 코드를 작성할 때 사용된다.

**JUnit Platform**

Test를 실행하기 위한 뼈대이다. Test를 발견하고 테스트 계획을 생성하는 TestEngine 인터페이스를 가지고 있다. TestEngine을 통해 Test를 발견하고, 수행 및 결과를 보고한다. 그리고 각종 IDE 연동을 보조하는 역할을 수행한다. (콘솔 출력 등)
(PlatForm = TestEngine API + Console Launcher + JUnit 4 Based Runner 등)

**JUnit Vintage**

TestEngine API 구현체로 JUnit 3, 4를 구현하고 있다. 기존 JUnit 3, 4 버전으로 작성된 테스트 코드를 실행할 때 사용된다. Vintage-Engine 모듈을 포함하고 있다.

![](https://taegyunwoo.github.io/assets/img/2021-09-15-TestFramework_JUnit5_SummaryAndBasicAnnotation/Untitled.png)

### JUnit LifeCycle Annotation

JUnit 5는 아래와 같은 테스트 라이프 사이클을 가지고 있다.

| Annotation  | Desription                                |
| ----------- | ----------------------------------------- |
| @Test       | 테스트용 메소드를 표현하는 어노테이션                      |
| @BeforeEach | 각 테스트 메소드가 시작되기 전에 실행되어야 하는 메소드를 표현       |
| @AfterEach  | 각 테스트 메소드가 시작된 후 실행되어야 하는 메소드를 표현         |
| @BeforeAll  | 테스트 시작 전에 실행되어야 하는 메소드를 표현 (static 처리 필요) |
| @AfterAll   | 테스트 종료 후에 실행되어야 하는 메소드를 표현 (static 처리 필요) |

### JUnit Main Annotation

**@SpringBootTest**

-  통합 테스트 용도로 사용된다.
-  @SpringBootApplication을 찾아가 하위의 모든 Bean을 스캔하여 로드한다.
-  그 후 Test용 Application Context를 만들어 Bean을 추가하고, MockBean을 찾아 교체한다.

**@ExtendWith**

-  JUnit4에서 @RunWith로 사용되던 어노테이션이 ExtendWith로 변경되었다.
-  @ExtendWith는 메인으로 실행될 Class를 지정할 수 있다.
-  @SpringBootTest는 기본적으로 @ExtendWith가 추가되어 있다.

**@WebMvcTest(Class명.class)**

-  ()에 작성된 클래스만 실제로 로드하여 테스트를 진행한다.
-  매개변수를 지정해주지 않으면 @Controller, @RestController, @RestControllerAdvice 등 컨트롤러와 연관된 Bean이 모두 로드된다.
-  스프링의 모든 Bean을 로드하는 @SpringBootTest 대신 컨트롤러 관련 코드만 테스트할 경우 사용한다.

**@Autowired about Mockbean**

-  Controller의 API를 테스트하는 용도인 MockMvc 객체를 주입받는다.
-  perform() 메소드를 활용하여 컨트롤러의 동작을 확인할 수 있다. .andExpect(), andDo(), andReturn() 등의 메소드를 같이 활용한다.

**@MockBean**

-  테스트할 클래스에서 주입 받고 있는 객체에 대해 가짜 객체를 생성해주는 어노테이션이다.
-  해당 객체는 실제 행위를 하지 않는다.
-  given() 메소드를 활용하여 가짜 객체의 동작에 대해 정의하여 사용할 수 있다.

**@AutoConfigureMockMvc**

-  spring.test.mockmvc 의 설정을 로드하면서 MockMvc의 의존성을 자동으로 주입받는다.
-  MockMvc 클래스는 REST API 테스트를 할 수 있는 클래스이다.

**@Import**

-  필요한 Class들을 Configuration으로 만들어 사용할 수 있다.
-  Configuration Component 클래스도 의존성 설정할 수 있다.
-  Import된 클래스는 주입으로 사용 가능하다.

### 통합 테스트

통합 테스트는 여러 기능을 조합하여 전체 비즈니스 로직이 제대로 동작하는지 확인하는 것을 의미한다.

통합 테스트의 경우 @SpringBootTest를 사용하여 진행한다.
-  @SpringBootTest는 @SpringBootApplication을 찾아가서 모든 Bean을 로드하게 된다.
-  이 방법을 대규모 프로젝트에서 사용할 경우, 테스트를 실행할 때마다 모든 빈을 스캔하고 로드는 작업이 반복되어 매번 무거운 작업을 수행해야 한다.

### 단위 테스트

단위 테스트는 프로젝트에 필요한 모든 기능에 대한 테스트를 각각 진행하는 것을 의미한다.

일반적으로 스프링 부트에서는
``org.springframework.boot:spring-boot-starter-test`` 디펜던시만으로 의존성을 모두 가질 수 있다.

**F.I.R.S.T 원칙**

-  Fast : 테스트 코드의 실행은 빠르게 진행되어야 한다.
-  Independent : 독립적인 테스트가 가능해야 한다.
-  Repeatable : 테스트는 매번 같은 결과를 만들어야 한다.
-  Self-Validating : 테스트는 그 자체로 실행하여 결과를 확인할 수 있어야 한다.
-  Timely : 단위 테스트는 비즈니스 코드가 완성되기 전에 구성하고 테스트가 가능해야 한다.
		 > 코드가 완성되기 전부터 테스트가 따라와야 한다는 TDD의 원칙을 담고있다.
