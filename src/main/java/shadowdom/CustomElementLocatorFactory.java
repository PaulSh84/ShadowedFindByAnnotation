package shadowdom;

import annotations.ShadowFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class CustomElementLocatorFactory implements ElementLocatorFactory {

    private final WebDriver driver;

    public CustomElementLocatorFactory(final WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ElementLocator createLocator(final Field field) {
        if (field.isAnnotationPresent(ShadowFindBy.class)) {
            return new CustomElementLocator(driver, field);
        } else {
            return new DefaultElementLocator(driver, field);
        }
    }
}
