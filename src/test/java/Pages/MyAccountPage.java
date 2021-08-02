package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends BasePage{
  private static String myAccountUrl = "http://test-automation-shop2.greenfox.academy/index.php?route=account/account";

  public MyAccountPage(WebDriver driver) {
    super(driver, MyAccountPage.myAccountUrl);
  }
  public WebElement getAddressBook() {
    for (WebElement link: driver.findElements(By.tagName("a"))) {
      if (link.getText().equals("Modify your address book entries")) {
        return link;
      }
    }
    return null;
  }
}
