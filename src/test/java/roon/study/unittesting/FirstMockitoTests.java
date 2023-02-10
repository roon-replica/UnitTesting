package roon.study.unittesting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class FirstMockitoTests {

    @Test
    public void first_mock_test() {
        // create mock
        List<String> mockList = mock(List.class);

        // use mock object
        mockList.add("one");
        mockList.clear();

        // verify
        // 실행 횟수를 검증하는거인듯.. 실행 횟수를 왜 검증하는지는 모르겠지만..
        verify(mockList).add("one");
        // verify(mockList, VerificationModeFactory.atLeast(2)).add("one");
        verify(mockList).clear();
    }

    @Test
    public void first_mock_test_with_stubbing() {
        // create mock
        List<String> mockedList = mock(ArrayList.class);

        // stubbing
        // 가정하기라 보면 될듯? (pre-programmed)
        when(mockedList.get(0)).thenReturn("first(stubbed)");
        when(mockedList.get(1)).thenThrow(new IndexOutOfBoundsException("no second item"));

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(2));

        verify(mockedList).get(0);
    }

    @Test
    public void test_argument_matchers() {
        List<String> mockedList = mock(ArrayList.class);

        System.out.println(anyInt());
        when(mockedList.get(anyInt())).thenReturn("item");

        assertThat(mockedList.get(0)).isEqualTo("item");
    }

}
