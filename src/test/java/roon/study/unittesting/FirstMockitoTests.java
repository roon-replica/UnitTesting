package roon.study.unittesting;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.List;

import static org.mockito.Mockito.*;

public class FirstMockitoTests {
    @Test
    public void first_mock_test(){
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

}
