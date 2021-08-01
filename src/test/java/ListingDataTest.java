import Pages.ProductListPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

public class ListingDataTest extends BaseTest{

  @Test
  public void shouldListDataFromMyStore() {
    ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
    productListPage.open();
    productListPage.listProducts();
    Assertions.assertThat(productListPage.getDesktopsHeader().equals("Desktops")).isTrue();

  }
}
