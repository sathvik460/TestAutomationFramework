package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;


public class CheckoutPage extends BasePage {

    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By streetAddressFld = By.id("billing_address_1");
    private final By cityFld = By.id("billing_city");
    private final By zipCodeFld = By.id("billing_postcode");
    private final By emailAddressFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By showLoginBtn = By.className("showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.cssSelector("button[value='Login']");
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown = By.id("select2-billing_state-container");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By productName = By.cssSelector("td[class='product-name']");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName){
        WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        webElement.clear();
        webElement.sendKeys(firstName);
        return this;
    }
    public CheckoutPage enterLastname(String lastName){
        WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        webElement.clear();
        webElement.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
        /*Select select=new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);*/
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[text()='" +countryName+ "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",webElement);
        webElement.click();
        return this;
    }
    public CheckoutPage enterStreetAddress(String streetAddress){
        WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(streetAddressFld));
        webElement.clear();
        webElement.sendKeys(streetAddress);
        return this;
    }
    public CheckoutPage enterCity(String city){
        WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld));
        webElement.clear();
        webElement.sendKeys(city);
        return this;
    }
    public CheckoutPage selectState(String stateName){
        /*Select select=new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);*/
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement webElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" +stateName+ "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",webElement);
        webElement.click();
        return this;
    }
    public CheckoutPage enterZipCode(String zipCode){
       WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(zipCodeFld));
       webElement.clear();
       webElement.sendKeys(zipCode);
        return this;
    }
    public CheckoutPage enterEmailAddress(String email){
        WebElement webElement=wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressFld));
        webElement.clear();
        webElement.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName()).
                enterLastname(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterStreetAddress(billingAddress.getStreetAddress()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterZipCode(billingAddress.getZipCode()).
                enterEmailAddress(billingAddress.getEmailAddress());
    }
    public CheckoutPage clickPlaceOrderBtn(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }
    public String getSuccessNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }
    public CheckoutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }
    public CheckoutPage enterUsername(String userName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(userName);
        return this;
    }
    public CheckoutPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }
    public CheckoutPage clickHereToLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(showLoginBtn)).click();
        return this;
    }
    public CheckoutPage login(User user){
        return enterUsername(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn();
    }

    public CheckoutPage selectDirectBankTransfer(){
        WebElement webElement=wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!webElement.isSelected()){
            webElement.click();
        }
        return this;
    }
    public String getProductName() throws Exception {
        int i = 5;
        while(i > 0){
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
            }catch (StaleElementReferenceException e){
                System.out.println("NOT FOUND. TRYING AGAIN" + e);
            }
            Thread.sleep(5000);
            i--;
        }
        throw new Exception("Element not found");
    }

}
