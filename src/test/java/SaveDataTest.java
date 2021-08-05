import Pages.ProductListPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SaveDataTest extends BaseTest {

  @DisplayName("Save products")
  @Description("Should save listed products name")
  @Feature("Save data feature")
  @Test
  public void shouldSaveProductsFromCurrentPage() throws IOException {
    //TC_10_01
    ProductListPage productListPage = PageFactory.initElements(driver, ProductListPage.class);
    productListPage.open();
    productListPage.listLaptopsNotebooks();
    productListPage.sortByPrice();

    Path productsFile = Paths.get("products.txt");

    Files.write(productsFile, productListPage.getListedProductNames());

    List<String> productsFileContent = Files.readAllLines(productsFile);

    int fileRow = 0;
    // iterate over products on page
    // and check if its name equals with the name in file
    List<WebElement> products = productListPage.getListedProducts();
    for (WebElement product : products) {
      String productName = product.findElement(By.tagName("h4")).getText();
      Assertions.assertThat(productName.equals(productsFileContent.get(fileRow))).isTrue();
      fileRow++;
    }
  }

}
