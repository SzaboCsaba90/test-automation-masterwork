import Pages.LoginLogoutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class LogoutPageTest extends BaseTest{

  @BeforeAll
  public void logoutSetUp() {
    this.createUser();
  }

  @DisplayName("Successful user logout.")
  @Description("Should logout user from account")
  @Feature("Logout feature")
  @Test
  public void shouldLogoutUser() {
    //TC_11_01
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(this.driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login(this.loginEmail, this.loginPassword);
    loginLogoutPage.getLogoutButton().click();
    Assertions.assertThat(loginLogoutPage.getAccountLogoutMessage().getText().equals("Account Logout")).isTrue();
    Assertions.assertThat(driver.findElement(By.tagName("h1")).isDisplayed()).isTrue();
  }

}
