import annotations.ShadowFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import shadowdom.CustomPageFactory;
import java.util.List;

public class ShadowDomPage {

    private WebDriver driver;

    @FindBy(css = "div.privacy-warning.permisive.not-accepted > div.submit > a")
    private WebElement confirmButton;

    @ShadowFindBy(shadowTagName = "qia-header", css = "[data-e2e-shadow='header_main_nav'] > li > a:first-child")
    private WebElement menuProducts;

    @ShadowFindBy(shadowTagName = "qia-header", css = "[data-e2e-shadow='header_main_nav'] > li > a")
    private List<WebElement> menuList;

    public ShadowDomPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        this.navigate();
        CustomPageFactory.initElements(driver, this);
    }

    public void navigate() {
        driver.get("https://www.qiagen.com/us/");
    }

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public WebElement getMenuProducts() {
        return menuProducts;
    }

    public List<WebElement> getMenuList() {
        return menuList;
    }
}
