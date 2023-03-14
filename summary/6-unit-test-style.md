### 0. 개요
- 4장  
좋은 단위테스트의 4대 요소  
회귀 방지, 리팩터링 내성, 빠른 피드백, 유지 보수성이라는 `기준틀` 정의했음

- 5장
목과 테스트 취약성 사이의 관계 알아봤음

- 6장  
동일한 기준틀을 단위 테스트 스타일에 대해 적용해 본다고 함  
출력 기반, 상태 기반, 통신 기반 3가지 스타일이 있다고 함

### 1. 단위 테스트의 3가지 스타일
- 출력 기반
  - SUT(테스트 대상 시스템)에 입력을 넣고 생성되는 출력을 점검하는 방식  
  ```java
  public void Discount_of_two_products() {
    var product1 = new Product("p1");
    var product2 = new Product("p2");
    var sut = new PriceEngine();
    
    var discount = sut.calcuateDiscount(product1, product2);
    assertEquals(0.02, discount);
  }
  ```
  - 출력 기반 단위 테스트 스타일을 함수형이라고도 한다?  
    함수형 프로그래밍이 사이드 이펙트가 없는 것을 강조해서 이렇게 부른다고 함 
- 상태 기반
  - 작업이 완료된 후 시스템의 상태를 확인하는 방식
  ```java
  public void adding_a_product_to_an_order() {
    var product = new Product("p1");
    var sut = new Order();
    
    sut.addProduct(product);
  
    assertEquals(1,sut.getProducts.count());
    assertEquals(product,sut.getProducts.get(0));
  }
  ```
- 통신 기반
  - mock을 사용해 SUT와 협력자 간의 통신을 검증..!
  ```java
  public void sending_a_greetings_email() {
    var emailGatewayMock = mock(EmailGateway.class);
    var sut = new Controller(emailGatewayMock);
    
    sut.greetUser("user@naver.com");
    
    verify(emailGatewayMock, times(1)).sendGreetingsEmail("user@naver.com");
  }
  ```

### 2. 비교
- 회귀 방지와 피드백 속도 지표 기준
  - 다 비슷하다고 함..  
  회귀 방지는 실행되는 코드의 양, 복잡도, 도메인 유의성 등이 영향을 주는데 스타일이랑 상관없으니까  
  테스트 속도랑도 크게 `상관없다`고 함
- 리팩터링 내성 기준
  - 리팩터링 내성의 지표인 거짓 양성은 `식별할 수 있는 동작` 대신 구현 세부 사항과 결합되었을 때의 사이드 이펙트이므로  
    `출력 기반` > 상태 기반 > 통신 기반 순으로 리팩터링 내성이 좋음!
- 유지보수 기준
  - 유지 보수성 지표는 단위 테스트 스타일과 밀접한 관령이 있다고 함..!  
    `테스트의 유지비`는 테스트가 이해하기 쉬운지, 실행하기 쉬운지로 결정된다고 함  
    그래서 이 경우에도 `출력 기반` 테스트가 제일 좋다고 함

### 3. 함수형 아키텍처
- 함수형 프로그래밍은 수학에서의 함수를 사용한 프로그래밍 방식이라고 함
- 하나의 입력에 하나의 출력
  `숨은 입출력`(사이드 이펙트)이 없는 메서드는 수학에서의 함수 정의를 준수해서 `수학적 함수`라고 부른다고 함!  
  메서드가 수학적 함수인지 판별하는 좋은 방법은 사이드 이펙트가 없으면 되는거니까 `값`으로 대체해도 동일한지 보면 됨!
- 함수형 아키텍처
  - 함수형 프로그래밍의 목표는 `비즈니스 로직을 처리하는 코드`와 `사이드 이펙트를 일으키는 코드`를 `분리`하는 것?
  - **함수형 아키텍처는 사이드 이펙트를 비즈니스 연산의 끝으로 몰아서 비즈니스 로직과 사이드 이펙트를 분리한다고 함!!**  
    functional core(결정을 내리는 코드, 사이드 이펙트 없는?)와 mutable shell(해당 결정에 따라 작용하는 코드)로 분리할 수 있다고 함..  
    `정확히 이해 안됨`
   
### 4. 함수형 아키텍처와 출력 기반 테스트로의 전환
- 감사시스템. 작성한 샘플 코드 참고
- 사이드 이펙트를 따로 떼어내기
- AuditManager가 `함수형 코어` 역할이고, Persister가 가변 셸 역할이라 보면 되는듯. 가변 셸이 헥사고날 아키텍처에서 인프라스트럭처 역할이라 보면 되나.  
  이렇게 비즈니스 로직과 사이드 이펙트를 분리한 거라고 함..
- 함수형 아키텍처의 힘이 느껴지네  
  확실히 간단해짐. 큰 장점이다  
  테스트 실행 속도, 유지 보수 지표 향상. 복잡한 목 설정 제거, 단순한 입출력만 있으면 됨. 테스트 가독성 증가.


### 5. 함수형 아키텍처의 단점