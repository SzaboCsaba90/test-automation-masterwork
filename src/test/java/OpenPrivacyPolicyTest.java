import Pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import java.time.Duration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenPrivacyPolicyTest extends BaseTest {
  @DisplayName("Open Privacy Policy")
  @Description("Should open privacy policy statement on registration page.")
  @Feature("Privacy Policy Statement feature")
  @Test
  public void shouldOpenPrivacyPolicy() {
    //TC_03_01
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
