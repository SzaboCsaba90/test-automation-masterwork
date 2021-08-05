package Pages;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

  public static void directLogout(WebDriver driver) {
    driver.get(LoginLogoutPage.logoutUrl);
  }


  public WebElement getLogoutButton() {
    this.driver.findElement(By.className("fa-user")).click();
    List<WebElement> buttons = this.driver.findElements(By.tagName("a"));
    for (WebElement button: buttons) {
      if (button.getText().equals("Logout")) {
        return button;
      }
    }
    return null;
  }

  public WebElement getAccountLogoutMessage() {
    return this.driver.findElement(By.id("content")).findElement(By.tagName("h1"));
  }

  @Override
  public void open(){
    //Makes sure there is no logged in user
    LoginLogoutPage.directLogout(this.driver);
    super.open();
  }
}
