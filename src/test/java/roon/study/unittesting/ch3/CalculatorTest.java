package roon.study.unittesting.ch3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private int a, b;
    private Calculator sut;

    @BeforeEach
    public void init_dependencies() {
        a = 1;
        b = 1;
        sut = new Calculator();
    }

    @Test
    public void sum_of_two_numbers() {
        // AAA 패턴

        // arrange
        // SUT(System Under Test, 테스트 대상 시스템)과 관련 의존성을 원하는 상태로 만들기

        // act
        // 메서드 호출, 준비된 의존성을 전달?, 출력값 캡쳐
        var ret = sut.sum(a, b);
        System.out.println("sum= " + ret);

        // assert
        // 결과 검증
        assertEquals(2, ret);

        // given-when-then과 같음. 근데 given-when-then은 비기술자들이 좀 더 선호하는 거라고 함.
    }

    @Test
    public void subtract_of_two_numbers() {

        var ret = sut.subtract(a, b);
        System.out.println("subtraction = " + ret);

        assertEquals(0, ret);

    }

}