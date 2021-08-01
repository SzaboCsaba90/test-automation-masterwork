import Pages.LoginLogoutPage;
import Pages.RegisterPage;
import java.time.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginLogoutPageTest extends BaseTest {
  private String loginEmail;
  private String loginPassword;

  @BeforeAll
  public void createUser() {
    String timestamp = "" + System.currentTimeMillis();
    this.loginEmail = "test"+ timestamp +"@test.com";
    this.loginPassword = "12345";
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.open();
    registerPage.registration("Peter", "Kovacs", this.loginEmail, "1234", this.loginPassword);
    System.out.println(this.loginEmail + " has been created!");
    LoginLogoutPage.logout(driver);
  }

  @Test
  public void shouldLoginExistingUser() {
    //TC_02_01
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(this.driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login(this.loginEmail, this.loginPassword);
    Assertions.assertThat(loginLogoutPage.isLoaded()).isFalse();
  }

  @Test
  public void shouldFailToLoginUser() {
    //TC_02_02
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(this.driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login("asdtest.com", "1234");
    Assertions.assertThat(loginLogoutPage.getWarningMessageForWrongEmail().getText().equals("Warning: No match for E-Mail Address and/or Password.")).isTrue();
    Assertions.assertThat(loginLogoutPage.getWarningMessageForWrongEmail().isDisplayed()).isTrue();
  }

}
