import Pages.RegisterPage;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.assertj.core.api.Assertions;

public class RegisterPageTest extends BaseTest {

  @Test
  public void shouldRegisterANewUserSuccessfully() {
    //TC_01_01
    String timestamp = "" + System.currentTimeMillis();
    RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
    registerPage.open();
    registerPage.registration("Peter", "Kovacs", "test"+ timestamp +"@test.com", "1234", "12345");
    Assertions.assertThat(driver.getCurrentUrl().equals("http://test-automation-shop2.greenfox.academy/index.php?route=account/success")).isTrue();
  }

  @Test
  public void shouldFailToRegisterANewUser() {
    //TC_01_02
    String timestamp = "" + System.currentTimeMillis();
    RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
    registerPage.open();
    registerPage.registration("test" + timestamp +"@test.com", "88888888", "333344");
    List<WebElement> errorMessages = registerPage.getErrorMessages();
    Assertions.assertThat(errorMessages.get(0).getText().equals("First Name must be between 1 and 32 characters!")).isTrue();
    Assertions.assertThat(errorMessages.get(1).getText().equals("Last Name must be between 1 and 32 characters!")).isTrue();
    Assertions.assertThat(errorMessages.get(0).isDisplayed()).isTrue();
    Assertions.assertThat(errorMessages.get(1).isDisplayed()).isTrue();
  }

}
