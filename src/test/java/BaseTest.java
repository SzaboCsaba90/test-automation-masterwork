import Pages.AddAddressPage;
import Pages.AddressBookPage;
import Pages.LoginLogoutPage;
import Pages.MyAccountPage;
import Pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
  protected WebDriver driver;
  protected String loginEmail;
  protected String loginPassword;

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

  @AfterAll
  public void teardown() {
    this.driver.quit();
  }

  public void addNewAddress(AddAddressPage addAddressPage, HashMap<String, String> inputFields) {
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login(this.loginEmail, this.loginPassword);
    MyAccountPage myAccountPage = PageFactory.initElements(driver,MyAccountPage.class);
    myAccountPage.open();
    myAccountPage.getAddressBook().click();
    AddressBookPage addressBookPage = PageFactory.initElements(driver, AddressBookPage.class);
    addressBookPage.getNewAddressButton().click();

    for (String key: inputFields.keySet()) {
      addAddressPage.getInputField(key).sendKeys(inputFields.get(key));
    }
    addAddressPage.getContinueButton().click();
  }

  public void createUser() {
    String timestamp = "" + System.currentTimeMillis();
    this.loginEmail = "test" + timestamp + "@test.com";
    this.loginPassword = "12345";
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.open();
    registerPage.registration("Peter", "Kovacs", this.loginEmail, "1234", this.loginPassword);
    System.out.println(this.loginEmail + " has been created!");
    LoginLogoutPage.directLogout(driver);
  }
}
