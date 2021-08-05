import Pages.AddAddressPage;
import Pages.AddressBookPage;
import Pages.LoginLogoutPage;
import Pages.MyAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.PageFactory;

public class DataInputTest extends BaseTest{

  @BeforeAll
  public void dataInputSetUp() {
    this.createUser();
  }

  @DisplayName("Store new data")
  @Description("Store new data in address menu")
  @Feature("Store data feature")
  @Test
  public void shouldStoreNewAddress() {
    //TC_06_01
    //Creating HashMap to store field data
    HashMap<String, String> inputFields = new HashMap<>();
    inputFields.put("firstname", "Test");
    inputFields.put("lastname", "Test");
    inputFields.put("company", "GreenFoxAcademy");
    inputFields.put("address-1", "asd");
    inputFields.put("city", "test");
    inputFields.put("postcode", "Test");
    inputFields.put("zone", "Aberdeen");

    AddAddressPage addAddressPage = PageFactory.initElements(driver, AddAddressPage.class);
    this.addNewAddress(addAddressPage, inputFields);

    String expectedAddress = inputFields.get("firstname") + " " + inputFields.get("lastname") + "\n" +  inputFields.get("company") + "\n" + inputFields.get("address-1") + "\n" + inputFields.get("city") + " " + inputFields.get("postcode") + "\n" + inputFields.get("zone") + "\nUnited Kingdom";
    Assertions.assertThat(addAddressPage.getLastAddressBookEntry().getText().equals(expectedAddress)).isTrue();
  }

  @DisplayName("Multiple data inputs")
  @Description("Should save new addresses from addresses.csv file")
  @Feature("Save addresses feature")
  @ParameterizedTest
  @CsvFileSource(resources = "addresses.csv", numLinesToSkip = 1)
  public void shouldStoreMultipleAddresses(String firstname, String lastname, String company, String address1, String city, String postcode, String zone) {
    // TC_07_01
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login(this.loginEmail, this.loginPassword);

    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.open();
    myAccountPage.getAddressBook().click();
    AddressBookPage addressBookPage = PageFactory.initElements(driver, AddressBookPage.class);
    addressBookPage.getNewAddressButton().click();
    AddAddressPage addAddressPage = PageFactory.initElements(driver, AddAddressPage.class);

    addAddressPage.getInputField("firstname").sendKeys(firstname);
    addAddressPage.getInputField("lastname").sendKeys(lastname);
    addAddressPage.getInputField("company").sendKeys(company);
    addAddressPage.getInputField("address-1").sendKeys(address1);
    addAddressPage.getInputField("city").sendKeys(city);
    addAddressPage.getInputField("postcode").sendKeys(postcode);
    addAddressPage.getInputField("zone").sendKeys(zone);

    addAddressPage.getContinueButton().click();

    String expectedAddress = firstname + " " + lastname + "\n" +  company + "\n" + address1 + "\n" + city + " " + postcode + "\n" + zone + "\nUnited Kingdom";
    Assertions.assertThat(addAddressPage.getLastAddressBookEntry().getText().equals(expectedAddress)).isTrue();
  }
}
