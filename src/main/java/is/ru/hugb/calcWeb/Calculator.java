package is.ru.hugb.calcWeb;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Calculator {
  private String delimString = ",|\\n";

  public int add(String input) {
    if (input != null && !input.isEmpty()) {
      String numberStrings[] = splitNumberStringByDelimiters(input);
      int numbers[] = toInt(numberStrings);
      checkNegatives(numbers);
      int smallerNumbers[] = removeLargeNumbers(numbers);
      return sum(smallerNumbers);
    }
    return 0;
  }

  private void checkNegatives(int numbers[]) {
    int negatives[] = Arrays.stream(numbers)
                            .filter(i -> i < 0)
                            .toArray();
    if (negatives.length != 0) {
      throw new IllegalArgumentException(
        "Negatives not allowed: " + Arrays.toString(negatives)
      );
    }
  }

  private String getNumbersAndSetDelim(String numbers) {
    Pattern r = Pattern.compile("//(.*)\n(.*)");
    Matcher m = r.matcher(numbers);

    if (m.find()) {
      setDelim(m.group(1));
      return m.group(2);
    }
    return numbers;
  }

  private int[] removeLargeNumbers(int numbers[]) {
    return Arrays.stream(numbers).filter(i -> i < 1000).toArray();
  }

  private void setDelim(String newDelim) {
    delimString += "|" + newDelim;
  }
  private String[] splitNumberStringByDelimiters(String numbers) {
    return getNumbersAndSetDelim(numbers).split(delimString);
  }

  private int sum(int numbers[]) {
    return Arrays.stream(numbers).sum();
  }

  private int[] toInt(String numbers[]) {
    return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
  }
}
