package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

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

  public WebElement getLastAddressBookEntry() {
    List<WebElement> addresses = this.driver.findElements(By.cssSelector(".table-responsive td"));
    return addresses.get(addresses.size() - 2);
  }

  public WebElement getLastAddressBookEditButton() {
    List<WebElement> addresses = this.driver.findElements(By.cssSelector(".table-responsive td"));
    return addresses.get(addresses.size() - 1).findElement(By.tagName("a"));
  }

  public WebElement getLastAddressBookDeleteButton() {
    List<WebElement> addresses = this.driver.findElements(By.cssSelector(".table-responsive td"));
    return addresses.get(addresses.size() - 1).findElements(By.tagName("a")).get(1);
  }
}

