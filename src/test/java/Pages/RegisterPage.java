package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage{
  public static String registerUrl = "http://test-automation-shop2.greenfox.academy/index.php?route=account/register";


  public RegisterPage(WebDriver driver) {
    super(driver, RegisterPage.registerUrl);
  }

  public WebElement getFirstName() {
    return this.driver.findElement(By.id("input-firstname"));
  }

  public WebElement getLastName() {
    return this.driver.findElement(By.id("input-lastname"));
  }

  public WebElement getEmail() {
    return this.driver.findElement(By.id("input-email"));
  }

  public WebElement getTelephone() {
    return this.driver.findElement(By.id("input-telephone"));
  }

  public WebElement getPassword() {
    return this.driver.findElement(By.id("input-password"));
  }

  public WebElement getPasswordConfirm() {
    return this.driver.findElement(By.id("input-confirm"));
  }

  public WebElement getPrivacyCheckbox() {
    return this.driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[1]"));
  }

  public WebElement getPrivacyPolicyLink() {
    List<WebElement> links = this.driver.findElements(By.tagName("a"));
    for (WebElement link: links) {
      if (link.getText().equals("Privacy Policy")) {
        return link;
      }
    }
    return null;
  }

  public WebElement getPrivacyPolicyCloseButton() {
    return this.driver.findElement(By.className("close"));
  }

  public WebElement getContinueButton() {
    return this.driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"));
  }



  public void registration(String firstname, String lastname, String email, String telephone, String password) {
    this.getFirstName().sendKeys(firstname);
    this.getLastName().sendKeys(lastname);
    this.registration(email, telephone, password);
  }

  public void registration(String email, String telephone, String password) {
    this.getEmail().sendKeys(email);
    this.getTelephone().sendKeys(telephone);
    this.getPassword().sendKeys(password);
    this.getPasswordConfirm().sendKeys(password);
    this.getPrivacyCheckbox().click();
    this.getContinueButton().click();
  }


  public boolean isLoaded() {

    String currentUrl = this.driver.getCurrentUrl();
    if (currentUrl.equals(this.registerUrl)) {
      return true;
    }
    return false;
  }

  public List<WebElement> getErrorMessages() {
    return this.driver.findElements(By.className("text-danger"));
  }

  public List<WebElement> getCurrentDialogs() {
    return this.driver.findElements(By.className("modal-dialog"));
  }
}
