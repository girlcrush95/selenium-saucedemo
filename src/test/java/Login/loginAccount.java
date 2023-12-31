package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class loginAccount {
    private WebDriver driver;

    @Test
    public void loginsuccess() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");

        System.setProperty("webdriver.chrome.driver", "C:\\Driverapp\\chromedriver_win32_14\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);
        //navigate to URL Testing
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        //Enter Username
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        //Enter Password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        //Click Login Button
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        loginbutton.click();

        WebElement homepage_product = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/span"));
        Assert.assertTrue(homepage_product.isDisplayed());

        //try {
            //Thread.sleep(5000); // 5 seconds
        //} catch (InterruptedException e) {
           // e.printStackTrace();
        //}
        driver.quit();

    }

    @Test
    public void loginfailed() {
        //Scenario 2 = User login with invalid credential
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");

        System.setProperty("webdriver.chrome.driver", "C:\\Driverapp\\chromedriver_win32_14\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);

        //navigate to URL Testing
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        //Enter Username
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        //Enter Password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret");

        //Click Login Button
        WebElement loginbutton = driver.findElement(By.id("login-button"));
        loginbutton.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")));

            System.out.println(errormessage.getText());
        } catch (TimeoutException e) {
            System.out.println("Login Failed");
        }

        //try {
           // Thread.sleep(5000); // 5 seconds
       // } catch (InterruptedException e) {
          //  e.printStackTrace();
       // }

        driver.quit();
    }


}

