package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginLogoutPage extends BasePage {
  private static String loginUrl = "http://test-automation-shop2.greenfox.academy/index.php?route=account/login";
  private static String logoutUrl = "http://test-automation-shop2.greenfox.academy/index.php?route=account/logout";
  public LoginLogoutPage(WebDriver driver) {
    super(driver, LoginLogoutPage.loginUrl);
  }

  public WebElement getEmailAddress() {
    return this.driver.findElement(By.id("input-email"));
  }

  public WebElement getPassword() {
    return this.driver.findElement(By.id("input-password"));
  }

  public WebElement getLoginButton() {
    return this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));
  }

  public void login(String email, String password) {
    this.getEmailAddress().sendKeys(email);
    this.getPassword().sendKeys(password);
    this.getLoginButton().submit();
  }

  public WebElement getWarningMessageForWrongEmail() {
    return this.driver.findElement(By.className("alert-dismissible"));
  }
  public boolean isLoaded() {

    String currentUrl = this.driver.getCurrentUrl();
    if (currentUrl.equals(this.loginUrl)) {
      return true;
    }
    return false;
  }

  public static void logout(WebDriver driver) {
    driver.get(LoginLogoutPage.logoutUrl);
  }
}
