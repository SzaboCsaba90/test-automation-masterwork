import Pages.AddAddressPage;
import Pages.AddressBookPage;
import Pages.LoginLogoutPage;
import Pages.MyAccountPage;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;

public class NewDataInputTest extends BaseTest{

  @BeforeAll
  public void newDataInputSetUp() {
    this.createUser();
  }

  @Test
  public void shouldStoreNewAddress() {
    //TC_08_01
    LoginLogoutPage loginLogoutPage = PageFactory.initElements(driver, LoginLogoutPage.class);
    loginLogoutPage.open();
    loginLogoutPage.login(this.loginEmail, this.loginPassword);
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.open();
    myAccountPage.getAddressBook().click();
    AddressBookPage addressBookPage = PageFactory.initElements(driver, AddressBookPage.class);
    addressBookPage.getNewAddressButton().click();
    AddAddressPage addAddressPage = PageFactory.initElements(driver, AddAddressPage.class);

    HashMap<String, String> inputFields = new HashMap<>();
    inputFields.put("firstname", "Test");
    inputFields.put("lastname", "Test");
    inputFields.put("company", "GreenFoxAcademy");
    inputFields.put("address-1", "asd");
    inputFields.put("city", "test");
    inputFields.put("postcode", "Test");
    inputFields.put("zone", "Aberdeen");

    for (String key: inputFields.keySet()){
      addAddressPage.getInputField(key).sendKeys(inputFields.get(key));
    }
    addAddressPage.getContinueButton().click();

    String expectedAddress = inputFields.get("firstname") + " " + inputFields.get("lastname") + "\n" +  inputFields.get("company") + "\n" + inputFields.get("address-1") + "\n" + inputFields.get("city") + " " + inputFields.get("postcode") + "\n" + inputFields.get("zone") + "\nUnited Kingdom";
    Assertions.assertThat(addAddressPage.getFirstAddressBookEntry().getText().equals(expectedAddress)).isTrue();
  }

}
