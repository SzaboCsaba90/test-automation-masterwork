import Pages.LoginLogoutPage;
import Pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
  protected WebDriver driver;
  protected String loginEmail;
  protected String loginPassword;

  public void createUser() {
    String timestamp = "" + System.currentTimeMillis();
    this.loginEmail = "test" + timestamp + "@test.com";
    this.loginPassword = "12345";
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.open();
    registerPage.registration("Peter", "Kovacs", this.loginEmail, "1234", this.loginPassword);
    System.out.println(this.loginEmail + " has been created!");
    LoginLogoutPage.logout(driver);
  }

  @BeforeAll
  public void setUp() throws IOException {
    String browser;

    Properties properties = new Properties();
    InputStream propertiesStream = this.getClass().getResourceAsStream("/test.properties");
    properties.load(propertiesStream);
    browser = properties.getProperty("browser");

    if (browser.equals("chrome")) {
      WebDriverManager.chromedriver().setup();
      this.driver = new ChromeDriver();
    } else if (browser.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      this.driver = new FirefoxDriver();
    } else {
      WebDriverManager.edgedriver().setup();
      this.driver = new EdgeDriver();
    }

  }

  @AfterEach
  public void teardown() {
    driver.quit();
  }
}
