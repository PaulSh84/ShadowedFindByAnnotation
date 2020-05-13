package shadowdom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class CustomPageFactory {

    public static void initElements(final WebDriver driver, final Object page) {
        initElements(new CustomElementLocatorFactory(driver), page);
    }

    public static void initElements(final ElementLocatorFactory factory, final Object page) {
        PageFactory.initElements(new CustomFieldDecorator(factory), page);
    }
}
