import Pages.ProductListPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

public class PaginationTest extends BaseTest{

  @Test
  public void shouldGoToNextPage() {
    ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
    productListPage.open();
    productListPage.listDesktopProducts();
    productListPage.getNextPageButton().click();
  }
}
