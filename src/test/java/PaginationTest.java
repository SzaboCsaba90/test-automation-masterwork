import Pages.ProductListPage;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PaginationTest extends BaseTest{

  @Test
  public void shouldGoToNextPage() {
    //TC_05_01
    ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
    productListPage.open();
    productListPage.listDesktopProducts();
    List<String> productsOnPage1 = productListPage.getListedProductNames();
    productListPage.getNextPageButton().click();
    Assertions.assertThat(productListPage.getPageNumber() == 2).isTrue();
    List<String> productsOnPage2 = productListPage.getListedProductNames();
    for (String productName: productsOnPage2) {
      Assertions.assertThat(productsOnPage1.contains(productName)).isFalse();
    }
  }
}
