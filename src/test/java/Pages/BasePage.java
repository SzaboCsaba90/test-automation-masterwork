package Pages;
import org.openqa.selenium.WebDriver;

public class BasePage {
  protected WebDriver driver;
  private String url;

  public BasePage(WebDriver driver, String url) {

    this.driver = driver;
    this.url = url;
  }

  public void open() {

    this.driver.get(this.url);
    this.driver.manage().window().maximize();
  }
}
