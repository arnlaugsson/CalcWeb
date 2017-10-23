package is.ru.hugb.calcWeb;

import org.testng.annotations.*;
import static org.testng.Assert.*;

public class CalculatorTest {
  private Calculator calc = new Calculator();

  @Test
  public void testEmptyString() {
    assertEquals(calc.add(""), 0);
  }

  @Test
  public void testSingleNumber() {
    assertEquals(calc.add("1"), 1);
  }

  @Test
  public void testTwoNumbers() {
    assertEquals(calc.add("1,2"), 3);
  }

  @Test
  public void testMultipleNumbers() {
    assertEquals(calc.add("1,2,3"), 6);
  }

  @Test
  public void testWithNewLineDelimiter() {
    assertEquals(calc.add("1\n2\n3"), 6);
  }

  @Test(
    expectedExceptions = IllegalArgumentException.class
  )
  public void testNegativeThrowsException() {
    calc.add("-1,-2,3");
  }

  @Test
  public void testNegativeThrowsExceptionWithMessage() {
    try {
      calc.add("-1,-2,-3,4,5");
      fail("No exception was thrown, IllegalArgumentException expected.");
    } catch (Exception e) {
       assertEquals(
         e.getMessage(),
         "Negatives not allowed: [-1, -2, -3]"
       );
    }
  }

  @Test
  public void testNoBigNumbers() {
    assertEquals(calc.add("1001,2"), 2);
  }

  @Test
  public void testDifferentDelimiter() {
    assertEquals(calc.add("//;\n1;2;3"), 6);
  }

  @Test
  public void testNewMultiCharDelimter() {
    assertEquals(calc.add("//&&&\n1&&&2"), 3);
  }
}
