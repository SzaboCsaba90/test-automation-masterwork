package Pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ProductListPage extends BasePage{
  private static String productListUrl = "http://test-automation-shop2.greenfox.academy/";

  public ProductListPage (WebDriver driver){
    super(driver, ProductListPage.productListUrl);
  }

  public WebElement getDesktopsButton() {
    return this.driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a"));
  }

  public WebElement getShowAllDesktops() {
    return this.driver.findElement(By.className("see-all"));
  }

  public String getProductListHeader() {
    WebElement header = this.driver.findElement(By.cssSelector("#content h2"));
    String headerText = header.getText();
    return headerText;
  }

  public WebElement getNextPageButton() {
    return this.driver.findElement(By.xpath("//*[@id=\"content\"]/div[5]/div[1]/ul/li[4]/a"));
  }

  public int getPageNumber() {
    return Integer.parseInt(this.driver.findElement(By.className("pagination")).findElement(By.className("active")).getText());
  }

  public void listDesktopProducts() {
    this.getDesktopsButton().click();
    this.getShowAllDesktops().click();
  }

  public List<WebElement> getListedProducts() {
    return this.driver.findElements(By.className("product-thumb"));
  }

  public List<String> getListedProductNames() {
    List<String> productNames = new ArrayList<String>();
    for (WebElement product: this.getListedProducts()) {
      productNames.add(product.findElement(By.tagName("h4")).getText());
    }
    return productNames;
  }

  public void sortByPrice() {
    Select sortBy = new Select(this.driver.findElement(By.id("input-sort")));
    sortBy.selectByVisibleText("Price (Low > High)");
  }

  public void listLaptopsNotebooks() {
    List<WebElement> navItems = this.driver.findElement(By.id("menu")).findElements(By.tagName("a"));
    Actions actions = new Actions(this.driver);
    for (WebElement navItem: navItems) {
      if (navItem.getText().equals("Laptops & Notebooks")) {
        actions.moveToElement(navItem);
        actions.click(navItem).build().perform();
        break;
      }
    }

    List<WebElement> navItems2 = this.driver.findElement(By.id("menu")).findElements(By.className("see-all"));
    for (WebElement navItem: navItems2) {
      if (navItem.getText().endsWith("Laptops & Notebooks")) {
        actions.moveToElement(navItem);
        actions.click(navItem).build().perform();
        break;
      }
    }
  }
}
