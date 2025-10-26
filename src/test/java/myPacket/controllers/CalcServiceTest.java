package myPacket.controllers;

import myPacket.service.CalculateService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalcServiceTest {

    @InjectMocks
    private CalculateService calcService;

    @ParameterizedTest
    @CsvSource({
            "136, 391, _", "142, 252, 0",
            "8936, 1904, )", "4763, 4652, @",
            "713, 5143, **", "2522, 713, !",
            "993, 143, ^", "2378, 239, \"",
            "124, 51, ;", "245, 252, f",
            "13, 561, %", "142, 4782, //",
            "512, 14, null", "41, 12, <",
            //null => false
            "14, 25, ,", "242, 52, \b", "42, 252, \r",
             "27, 14, ", "52, 242, \n", //ломает следующую строку
            "123, 2234, null", "null, null, null"
    })
    void testCalculateResult_IncorrectSymbol(int a, int b, String c) {
        assertEquals("error", calcService.calculateResult(c, a, b));
    }

    @ParameterizedTest //повтор tetsFilterNumbers_expectFalse
    @CsvSource({
            "2147483647, 2, *", "1073741824, 2, *",
            "1073741824, 1073741824, +", "-1073741824, -1073741824, +",
            "-2147483647, 2, -", "-1073741824, -1073741825, +", "-2147483648, -1, *"
    })
    void testCalculateResult_IncorrectNumbers(int a, int b, String c) {
        assertEquals("error", calcService.calculateResult(c, a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "2147483647, 2, *", "1073741824, 2, *",
            "1073741824, 1073741824, +", "-1073741824, -1073741824, +",
            "-2147483647, 2, -", "-1073741824, -1073741825, +", "-2147483648, -1, *"
    })
    void tetsFilterNumbers_expectFalse(int a, int b, String c) {
        assertFalse(calcService.filterNumbers(a, b, c));
    }

    @ParameterizedTest
    @CsvSource({
            "1073741823, 2, *", "-2147483647, 1, +",
            "-1073741824, 2, *", "1073741824, -2, *",
            "1073741824, 1073741823, +", "2147483647, 1, *",
            "-1073741824, 2, *", "-1, -2147483648, -"
    })
    void tetsFilterNumbers_expectTrue(int a, int b, String c) {
        assertTrue(calcService.filterNumbers(a, b, c));
    }
}