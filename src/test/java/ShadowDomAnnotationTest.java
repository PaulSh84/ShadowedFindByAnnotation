import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.seljup.SingleSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.stream.Collectors;

@ExtendWith(SeleniumExtension.class)
@SingleSession
public class ShadowDomAnnotationTest {

    private final WebDriver driver;
    private ShadowDomPage page;

    public ShadowDomAnnotationTest(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        this.driver.manage().window().maximize();
        this.page = new ShadowDomPage(driver);
    }

    @Test
    public void runTest() throws InterruptedException {
        page.clickConfirmButton();
        Thread.sleep(1000);
        page.getMenuProducts().click();
        Thread.sleep(3000);
        Assertions.assertIterableEquals(
                page.getMenuList().stream().map(WebElement::getText).collect(Collectors.toList()),
                Arrays.asList("Products", "Applications & Insights", "Service & Support","About QIAGEN"));
    }

    @AfterEach
    void storageCleanup() {
        ((JavascriptExecutor)driver).executeScript("window.localStorage.clear()");
    }

}
