import Pages.ProductListPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ListingDataTest extends BaseTest{

  @DisplayName("Listing data")
  @Description("Should list data from Desktops menu")
  @Feature("Product listing feature")
  @Test
  public void shouldListDataFromMyStore() {
    //TC_04_01
    ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
    productListPage.open();
    productListPage.listLaptopsNotebooks();
    Assertions.assertThat(productListPage.getProductListHeader().equals("Laptops & Notebooks")).isTrue();
    productListPage.sortByPrice();
    // assert that all products price is higher than the previous one in the list
    List<WebElement> products = productListPage.getListedProducts();
    Double previousPrice = 0.0;
    for (WebElement product: products) {
      Double currentPrice = (Double.parseDouble(product.findElement(By.className("price")).getText().split("Ex")[0].replace("$", "").replace("\n", "").replace(",", "")));
      Assertions.assertThat( currentPrice >= previousPrice).isTrue();
      previousPrice = currentPrice;
    }
  }
}
