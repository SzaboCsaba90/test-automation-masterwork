import Pages.LoginLogoutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;
import org.assertj.core.api.Assertions;

public class LoginPageTest extends BaseTest {

  @BeforeAll
  public void loginSetUp() {
    this.createUser();
  }

  @DisplayName("Successful user login.")
  @Description("Should successfully login registered user.")
  @Feature("Login feature")
  @Test
  public void shouldLoginExistingUser() {
    //TC_02_01
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(this.driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login(this.loginEmail, this.loginPassword);
    Assertions.assertThat(loginLogoutPage.isLoaded()).isFalse();
  }

  @DisplayName("Fail to login user.")
  @Description("Should fail to login user with incorrect email address.")
  @Feature("Login feature")
  @Test
  public void shouldFailToLoginUser() {
    //TC_02_02
    String timestamp = "" + System.currentTimeMillis();
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(this.driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login("asdtest" + timestamp + ".com", "1234");
    Assertions.assertThat(loginLogoutPage.getWarningMessageForWrongEmail().getText().equals("Warning: No match for E-Mail Address and/or Password.")).isTrue();
    Assertions.assertThat(loginLogoutPage.getWarningMessageForWrongEmail().isDisplayed()).isTrue();
  }
}
