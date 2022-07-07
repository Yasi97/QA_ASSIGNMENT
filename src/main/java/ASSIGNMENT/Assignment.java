package ASSIGNMENT;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;

public class Assignment {

    WebDriver driver;
    @BeforeMethod
    public void beforeMethod() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe") ;
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Launch the browser
        driver.get("https://www.amazon.com/");
        // Maximize the window
        driver.manage().window().maximize();



    }
    @Test
    public void testMethod1() {
        /* 3 */
          if(driver.getCurrentUrl().equals("https://www.amazon.com/")){
            System.out.println("Correct page loaded");
        }
        else{
            System.out.println("Incorrect page loaded");
        }
    }

    @Test
    @Parameters({"name", "sEmail", "sPassword"})
    public void testMethod2(String name, String sEmail, String sPassword) throws InterruptedException {
        //4
        Select select = new Select(driver.findElement(By.xpath("//*[@name='url']")));
        select.selectByValue("search-alias=computers-intl-ship");

        //5
        driver.findElement(By.xpath("//*[@name='field-keywords']")).sendKeys("Laptop");

        //6
        driver.findElement(By.xpath("//*[@value='Go']")).click();

        //7
        WebDriverWait w = new WebDriverWait(driver,200);
        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("#p_89\\/HP a.a-link-normal.s-navigation-item")));
        List<WebElement> checkboxElementFilteredBrands = driver.findElements(By.cssSelector("#p_89\\/HP a.a-link-normal.s-navigation-item"));
        if(((List<?>) checkboxElementFilteredBrands).size() > 0) {
            checkboxElementFilteredBrands.get(0).click();
        } else {
            assertEquals(checkboxElementFilteredBrands.size(), 0);
        }
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);





        //8
        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("a.a-link-normal.s-underline-text.s-underline-link-text.s-link-style.a-text-normal")));
        List<WebElement> resultListElements = driver.findElements(By.cssSelector("a.a-link-normal.s-underline-text.s-underline-link-text.s-link-style.a-text-normal"));
        if(resultListElements.size() > 0) {
            resultListElements.get(0).click();
        } else {
            assertEquals(resultListElements.size(), 0);
        }
        //driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

        //9,10
        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("span.a-size-large.product-title-word-break")));
        WebElement productElement = driver.findElement(By.cssSelector("span.a-size-large.product-title-word-break"));
        String productTitle = productElement.getText();
        System.out.println("Product Title = " + productTitle);
        System.out.println("Product Title Length = " + productTitle.length());
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

        //11
        w.until(ExpectedConditions.presenceOfElementLocated (By.id("add-to-cart-button")));
        WebElement btnAddToCartElement = driver.findElement(By.id("add-to-cart-button"));
        btnAddToCartElement.submit();

        //12
        WebElement priceElement = driver.findElement(By.xpath("//*[@id='sw-subtotal']/span/span/span[1]"));
        String productPrice = priceElement.getAttribute("textContent");

        System.out.println("Product Price = " + productPrice);


        //13
        WebElement btnCartElement = driver.findElement(By.cssSelector("a#nav-cart"));
        btnCartElement.click();

        //14
        WebElement cartItemElement = driver.findElement(By.cssSelector("span.a-truncate-full.a-offscreen"));
        String productTitleInCart = cartItemElement.getAttribute("textContent");
        System.out.println("Product Title Cart = " + productTitleInCart);
        assertEquals(productTitle, productTitleInCart);

        WebElement cartItemPriceElement = driver.findElement(By.cssSelector("span.a-size-medium.a-color-base.sc-price.sc-white-space-nowrap.sc-product-price.a-text-bold"));
        String productPriceInCart = cartItemPriceElement.getText();

        assertEquals(productPrice, productPriceInCart);

        //15
        Select selectQuantityElement = new Select(driver.findElement(By.id("quantity")));
        int selectedQuantityValue = Integer.parseInt(selectQuantityElement.getFirstSelectedOption().getText());

        assertNotEquals(selectedQuantityValue, 0);

        System.out.println("Product Item Quantity = " + selectedQuantityValue);

        //16
        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("span.a-size-medium.a-color-base.sc-price.sc-white-space-nowrap")));
        WebElement subtotal = driver.findElement(By.cssSelector("span.a-size-medium.a-color-base.sc-price.sc-white-space-nowrap"));
        String subtotalPrice = subtotal.getText();

        assertEquals(subtotalPrice, productPrice);

        //17
        WebElement btnCheckout = driver.findElement(By.name("proceedToRetailCheckout"));
        btnCheckout.submit();

        //18
        WebElement btnCreateAccount = driver.findElement(By.id("createAccountSubmit"));
        btnCreateAccount.click();


        //19
        WebElement inputNameEle = driver.findElement(By.id("ap_customer_name"));
        inputNameEle.sendKeys(name);

        WebElement inputEmailEle = driver.findElement(By.id("ap_email"));
        inputEmailEle.sendKeys(sEmail);

        WebElement inputPasswordEle = driver.findElement(By.id("ap_password"));
        inputPasswordEle.sendKeys(sPassword);

        WebElement rePasswordEle = driver.findElement(By.id("ap_password_check"));
        rePasswordEle.sendKeys(sPassword);
    }



    @AfterMethod
    public void afterMethod() {
        //20
        driver.close();
    }

}


