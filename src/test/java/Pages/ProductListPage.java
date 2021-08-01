package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

  public String getDesktopsHeader() {
    WebElement desktopsHeader = this.driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
    String desktopsHeaderValue = desktopsHeader.getAttribute("value");
    return desktopsHeaderValue;
  }

  public WebElement getNextPageButton() {
    return this.driver.findElement(By.xpath("//*[@id=\"content\"]/div[5]/div[1]/ul/li[4]/a"));
  }

  public void listProducts() {
    this.getDesktopsButton().click();
    this.getShowAllDesktops().click();
  }
}
