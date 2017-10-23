package is.ru.hugb.calcWeb;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestCalcWeb extends SeleniumTestWrapper {
  @Test
  public void testTitleMatches() {
    driver.get(baseUrl);
    assertEquals("StringCalculator", driver.getTitle());
  }

  @Test
  public void testSimpleAdd() throws Exception {
    driver.get(baseUrl);
    /* Remove Thread.sleep... */
    Thread.sleep(2000);
    WebElement input = driver.findElement(By.id("number"));
    input.sendKeys("1");
    /* Remove Thread.sleep... */
    Thread.sleep(2000);
    /* ... finish test! */
  }
}
