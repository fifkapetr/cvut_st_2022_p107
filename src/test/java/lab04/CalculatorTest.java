package lab04;

import lab031.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "2, 3, 5", "1, 1, 3"})
    public void add_addsAandB_returnsC(int a, int b, int c) {
        // arrange
        Calculator calc = new Calculator();
        int expectedResult = c;

        // act
        int result = calc.add(a, b);

        // assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "{0} multiple {1} should be equal to {2}")
    @CsvSource({"1, 2, 2", "2, 3, 6", "1, 9, 9"})
    public void multiple_multipliesAandB_returnsC(int a, int b, int c) {
        // arrange
        Calculator calc = new Calculator();
        int expectedResult = c;

        // act
        int result = calc.multiply(a, b);

        // assert
        assertEquals(expectedResult, result);
    }

}
