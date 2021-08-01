import Pages.RegisterPage;
import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenPrivacyPolicyTest extends BaseTest {

  @Test
  public void shouldOpenPrivacyPolicy() {
    RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
    registerPage.open();
    registerPage.getPrivacyPolicyLink().click();
    new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(By.className("close")));
    System.out.println(registerPage.getCurrentDialogs().get(0).findElement(By.className("modal-title")).getText());
    Assertions.assertThat(registerPage.getCurrentDialogs().get(0).findElement(By.className("modal-title")).getText().equals("Privacy Policy")).isTrue();
    registerPage.getPrivacyPolicyCloseButton().click();
    registerPage.getPrivacyCheckbox().click();
    Assertions.assertThat(registerPage.getPrivacyCheckbox().isSelected()).isTrue();
  }
}
