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
