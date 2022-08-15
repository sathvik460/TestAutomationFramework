package org.selenium.pom.tests;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.base.BaseTest;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    //@Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        /*BillingAddress billingAddress=new BillingAddress().
                setFirstName("test1").
                setLastName("user").
                setStreetAddress("Down Town").
                setCity("Texas").
                setZipCode("10020").
                setEmailAddress("test1user@gmail.com");*/
        /*BillingAddress billingAddress=new BillingAddress("test1","user","Down Town",
                "Texas","10020","test1user@gmail.com"); */

        String searchFor="Blue";
        BillingAddress billingAddress=JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product=new Product(1215);
        StorePage storePage=new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu().
                enterTextInSearchFld(searchFor).
                clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage=storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage=cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");
       }

    //@Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor="Blue";
        BillingAddress billingAddress=JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product=new Product(1215);
        User user=new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
        StorePage storePage=new HomePage(getDriver()).
                load().
                getMyHeader().navigateToStoreUsingMenu().
                enterTextInSearchFld(searchFor).
                clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage=storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage=cartPage.checkout();
        checkoutPage.clickHereToLoginBtn();
        checkoutPage.
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();
        Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");
    }
}

