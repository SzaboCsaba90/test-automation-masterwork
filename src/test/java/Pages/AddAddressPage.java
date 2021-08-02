package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddAddressPage {
  private WebDriver driver;

  public AddAddressPage(WebDriver driver) {
    this.driver = driver;
  }

  public WebElement getContinueButton() {
    return this.driver.findElement(By.className("btn-primary"));
  }

  public WebElement getInputField(String inputName) {
    return this.driver.findElement(By.id("input-" + inputName));
  }

  public WebElement getFirstAddressBookEntry() {
    return this.driver.findElement(By.cssSelector(".table-responsive td"));
  }
}

