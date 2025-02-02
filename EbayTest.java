import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import java.util.Set;



public class EbayTest {

    public static void main(String[] args) {

	WebDriverManager.chromedriver().setup();

	WebDriver driver= new ChromeDriver();
        

        try {

            driver.get("https://www.ebay.com");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



            WebElement searchBox = driver.findElement(By.id("gh-ac"));

            searchBox.sendKeys("book");

            searchBox.submit();

            WebElement firstBook = driver.findElement(By.xpath("(//li[contains(@class,'s-item')]//a[@class='s-item__link'])[1]"));

            firstBook.click();


            String parentWindow = driver.getWindowHandle();

            WebElement addToCart = driver.findElement(By.xpath("//a[contains(text(), 'Add to cart')] | //button[contains(text(), 'Add to cart')]"));

            addToCart.click();

            Set<String> allWindows = driver.getWindowHandles();



            for (String window : allWindows) {

                if (!window.equals(parentWindow)) {

                    driver.switchTo().window(window);

                    break;

                }

            }

            WebElement cartCount = driver.findElement(By.id("gh-minicart-count"));

            if (Integer.parseInt(cartCount.getText()) > 0) {

                System.out.println("Test Passed");

            } else {

                System.out.println("Test Failed");

            }

            driver.close(); 

            driver.switchTo().window(parentWindow);


        } catch (Exception e) {

            System.out.println("Test Failed: " + e.getMessage());

        } finally {

            driver.quit();

        }

    }

}