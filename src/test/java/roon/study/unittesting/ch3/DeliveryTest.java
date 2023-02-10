package roon.study.unittesting.ch3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryTest {
    // public void delivery_for_past_date_is_invalid();
    // public void delivery_for_today_is_invalid();
    // public void delivery_for_tomorrow_is_invalid();
    // public void the_soonest_delivery_date_is_two_days_from_now();
    // parameterzied 테스트를 사용하면 위 4개의 테스트를 하나의 테스트로 작성 가능!

    private Delivery sut;

    @BeforeEach
    public void init_sut() {
        this.sut = new Delivery(LocalDate.now());
    }

    // [paramterized test 사용법]
    // https://stackoverflow.com/questions/61483452/parameterized-test-with-two-arguments-in-junit-5-jupiter
    @ParameterizedTest
    @MethodSource("invalidDeliveryDateParams")
    public void can_detect_an_invalid_delivery_date(int dateFromNow, boolean isValid) {
        LocalDate today = LocalDate.now();
        assertEquals(sut.isDeliveryDateValid(today.plusDays(dateFromNow)), isValid);
    }

    private static List<Arguments> invalidDeliveryDateParams() {
        return List.of(
                Arguments.of(-1, false),
                Arguments.of(0, false),
                Arguments.of(1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("validDeliveryDateParam")
    public void the_soonest_delivery_date_is_two_days_from_now(int dateFromNow, boolean isValid) {
        LocalDate today = LocalDate.now();
        assertEquals(sut.isDeliveryDateValid(today.plusDays(dateFromNow)), isValid);
    }

    private static List<Arguments> validDeliveryDateParam() {
        return List.of(Arguments.of(2, true));
    }
}