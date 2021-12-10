package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test1 {

    public  WebDriver driver;

    @Before
    public void setupDriver(){

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("disable-infobars");
        driver = new FirefoxDriver(options);
        System.setProperty("webdriver.gecko.driver","C:\\Users\\MONSTER\\Desktop\\LcWaikiki\\geckodriver.exe");
        String url = "https://www.lcwaikiki.com/tr-TR/TR";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void TestHome(){

        WebElement signBtnDrp= driver.findElement(By.className("dropdown-toggle"));//
        signBtnDrp.click();
        WebElement mailbox= driver.findElement(By.id("LoginEmail"));
        mailbox.click();
        mailbox.sendKeys("adamhf88@hotmail.com");

        WebElement password = driver.findElement(By.id("Password"));
        password.click();
        password.sendKeys("trytry123");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
        driver.findElement(By.id("loginLink")).click();

        /* Arama çubuğunda 'Pantolan' ifadesinin aranması */
        WebElement searchBox = driver.findElement(By.id("search_input"));
        searchBox.click();
        searchBox.sendKeys("pantolan");
        driver.findElement(By.className("searchButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        // Arama sonuç sayfalarında 2. sayfanın açılması
        WebElement loadMore = driver.findElement(By.className("lazy-load-button"));
        loadMore.click();


        //rastgele bir ürünün açılması
        WebElement products=driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/div[7]/div/div[1]"));
        List<WebElement> l1 = products.findElements(By.tagName("a"));
        l1.get(new Random().nextInt(l1.size())).click();


        //rastgele beden seç
        WebElement size=driver.findElement(By.xpath("//*[@id='option-size']"));
        List<WebElement> sizeList = size.findElements(By.tagName("a"));
        sizeList.get(4).click();
        //sizeList.get(new Random().nextInt(sizeList.size())).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //ürün fiyatı
        WebElement price= driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div/div[2]/div/div/div/span[2]"));
        String priceText= price.getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        /* Açılan ürün sayfasında ürünün sepete eklenmesi*/
        WebElement addToCart = driver.findElement(By.xpath("//*[@id='pd_add_to_cart']"));
        addToCart.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[4]/div/div[4]/a")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        /* Ürün sayfasındaki fiyat ile sepetteki fiyatın karşılaştırılması */
        WebElement priceBasket = driver.findElement(By.tagName("span"));
        String priceText2= priceBasket.getText();
        if(priceText.compareTo(priceText2)>0){

            /* Sepetteki ürün adetinin artırılması */
            WebElement quantityBasket = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[1]/div/input"));
            quantityBasket.click();
            quantityBasket.clear();
            quantityBasket.sendKeys("2");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }
        /* Sepetteki ürünlerin boşaltılması */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.id("Cart_ProductDelete_731252032")).click();

    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.quit();
    }
}
