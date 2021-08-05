package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressBookPage {
  private WebDriver driver;

  public AddressBookPage(WebDriver driver) {
    this.driver = driver;
  }

  public WebElement getNewAddressButton() {
    for (WebElement link: this.driver.findElements(By.tagName("a"))) {
      if (link.getText().equals("New Address")) {
        return link;
      }
    }
    return null;
  }
}
