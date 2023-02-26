https://martinfowler.com/articles/mocksArentStubs.html

### 1. Overview

- mock 객체: 테스트를 위해 실제 객체를 흉내내는 것
- mock은 특별한 형태의 테스트 객체일 뿐이다, mock은 다른 스타일의 테스트를 가능하게 한다고 함.
- mock 동작 방식, 행위 검증을 기반으로 어떻게 테스트를 용이하게 하는지, mock을 이용해서 다른 스타일로 테스트 하는 법을 알려주는 글이라고 함
- mock과 stub이 혼동되고 있는거 같다. 2가치 측면에서 차이가 있다고 함.
    - 테스트 결과를 검증하는 방식(행위 기반 vs 상태 기반)
    - 고전파 vs mockist

### 2. 기존의 테스트 방식
- Order, Warehouse 예시
  - Order가 `SUT`(OUT, Object Under Test)이고 Warehouse가 `collaborator`
  - `state verification`: 테스트 메서드 실행 후 SUT, collaborator의 상태를 검증하는 방식

### 3. Mock 객체를 사용한 테스트 방식 


### 4. Mock과 Stub의 차이
- unit testing은 소프트웨어의 하나의 요소에 집중하는 것인데, 종종 warehouse처럼 다른 unit이 필요한 경우가 있어서 문제임
- `Test Double` Gerard Meszaros의 책에서 나온 개념이라 함. dummy, fake, stubs, spies, mock 개념 소개함.
  - 이 중에서 mock만 behavior 검증을 하고 나머지는 주로 state 검증..? 
  - `stubs`: 미리 준비된 답변?
  - `mock`: 객체임. expectation이 미리 설정됨. 항상 행동을 검증


### 5. classical and mocking test
- mock을 언제 쓸것인가?
- order와 warehouse 예제에서 
  - classcial TDD 스타일은 warehouse는 실제 객체를 쓰고, mailer는 Double을 썼을거라고 함..
  - mockist TDD 스타일은 `어떤 객체에든 mock을` 쓴다고 함
    
    mockist의 특징은 `행동`을 검증한다는 것.

### 6. Choosing Between the Differences
- SUT와 collaborator가 연동이 쉬운지를 생각해보라고 함
- state vs behavior 검증보단 classical vs mockist가 더 큰 이슈라고 함
  - 다만, cache의 경우에는 상태보단 행위 검증이 낫다고 함..

- `Test isolation`
  - mockist 테스트에서 오류가 발견되면 원인은 SUT에 있는거임. 근데 classical 테스트는 원인이 SUT 외에 collaborators(클라이언트)에 있을 수도 있어서 원인 파악이 힘들 수도 있음
  - 근데 classical 테스트는 클라이언트에서 미처 예상못한 오류를 검출할 수도 있다는 장점도 있음
  - 그리고 mockist 테스트는 가정이 잘못되면 `false positive` 발생 가능한 듯..!

- Coupling Tests to Implementations
  - 클래시컬 테스트는 결과 상태값만 검증하는 반면, mockist 테스트는 행동을 검증하기 때문에 테스트가 구현에 더 `결합`되어 있다..!
  - 테스트가 구현과 결합되는게 trade off가 있음.. `리팩터링 내성`이 낮아지긴하겠다..

- Design Style
  - 잘 이해 안됨.... 나중에..
