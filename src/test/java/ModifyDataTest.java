import Pages.AddAddressPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import java.util.HashMap;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ModifyDataTest extends BaseTest {

  @BeforeAll
  public void dataModifySetUp() {
    this.createUser();
  }

  @DisplayName("Modify existing data")
  @Description("Should modify existing First name input in addresses")
  @Feature("Data modification feature")
  @Test
  public void shouldChangeFirstNameInAddressBook() {
    // TC_08_01
    AddAddressPage addAddressPage = PageFactory.initElements(driver, AddAddressPage.class);

    String firstnameBefore = "Test1";
    String firstnameAfter = "NewName";

    HashMap<String, String> inputFields = new HashMap<>();
    inputFields.put("firstname", firstnameBefore);
    inputFields.put("lastname", "Test1");
    inputFields.put("company", "GreenFoxAcademy");
    inputFields.put("address-1", "asd");
    inputFields.put("city", "test");
    inputFields.put("postcode", "Test");
    inputFields.put("zone", "Aberdeen");

    this.addNewAddress(addAddressPage, inputFields);

    addAddressPage.getLastAddressBookEditButton().click();

    addAddressPage.getInputField("firstname").clear();
    addAddressPage.getInputField("firstname").sendKeys(firstnameAfter);
    addAddressPage.getContinueButton().click();

    String expectedAddress = firstnameAfter + " " + inputFields.get("lastname") + "\n" +  inputFields.get("company") + "\n" + inputFields.get("address-1") + "\n" + inputFields.get("city") + " " + inputFields.get("postcode") + "\n" + inputFields.get("zone") + "\nUnited Kingdom";
    String actualAddress = addAddressPage.getLastAddressBookEntry().getText();
    Assertions.assertThat(actualAddress.equals(expectedAddress)).isTrue();

  }
  @DisplayName("Delete existing address")
  @Description("Should delete a non-default address from addresses")
  @Feature("Deleting from addresses feature")
  @Test
  public void shouldDeleteAddress() {
    // TC_09_01
    AddAddressPage addAddressPage = PageFactory.initElements(driver, AddAddressPage.class);

    HashMap<String, String> inputFields1 = new HashMap<>();
    inputFields1.put("firstname", "Test1");
    inputFields1.put("lastname", "Test1");
    inputFields1.put("company", "GreenFoxAcademy");
    inputFields1.put("address-1", "asd");
    inputFields1.put("city", "test");
    inputFields1.put("postcode", "Test");
    inputFields1.put("zone", "Aberdeen");

    HashMap<String, String> inputFields2 = new HashMap<>();
    inputFields2.put("firstname", "Test2");
    inputFields2.put("lastname", "Test2");
    inputFields2.put("company", "GreenFoxAcademy");
    inputFields2.put("address-1", "asd");
    inputFields2.put("city", "test");
    inputFields2.put("postcode", "Test");
    inputFields2.put("zone", "Aberdeen");

    // need to add two addresses as first (default) address cannot be deleted
    this.addNewAddress(addAddressPage, inputFields1);
    this.addNewAddress(addAddressPage, inputFields2);

    addAddressPage.getLastAddressBookDeleteButton().click();

    //Assert that latest address (inputFields2) is deleted.
    String notExpectedAddress = inputFields2.get("firstname") + " " + inputFields2.get("lastname") + "\n" +  inputFields2.get("company") + "\n" + inputFields2.get("address-1") + "\n" + inputFields2.get("city") + " " + inputFields2.get("postcode") + "\n" + inputFields2.get("zone") + "\nUnited Kingdom";
    List<WebElement> addresses = this.driver.findElements(By.cssSelector(".table-responsive td"));
    String actualAddress = addresses.get(addresses.size() - 2).getText();
    Assertions.assertThat(actualAddress).isNotEqualTo(notExpectedAddress);

  }


}
