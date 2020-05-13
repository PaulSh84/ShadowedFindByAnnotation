package shadowdom;

import static java.util.Optional.ofNullable;

import annotations.ShadowFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class CustomElementLocator implements ElementLocator {

    private final WebDriver driver;
    private final ShadowFindBy shadowDomLocator;


    public CustomElementLocator(final WebDriver driver, final Field field) {
        this.driver = driver;
        this.shadowDomLocator = field.getAnnotation(ShadowFindBy.class);
    }

    @Override
    public WebElement findElement() {
        final WebElement shadow = driver.findElement(By.cssSelector(shadowDomLocator.shadowTagName()));
        final Object jsObject = ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].shadowRoot.querySelector(\""
                        + shadowDomLocator.css() + "\")", shadow);
        return ofNullable((WebElement) jsObject).orElseThrow(()-> new JavascriptException("JSExecutor did not return a valid object"));
    }

    @Override
    public List<WebElement> findElements() {
        final WebElement shadow = driver.findElement(By.cssSelector(shadowDomLocator.shadowTagName()));
        final Object jsObject = ofNullable(((JavascriptExecutor) driver).executeScript(
                "return arguments[0].shadowRoot.querySelectorAll(\""
                        + shadowDomLocator.css() + "\")", shadow))
                .orElseThrow(() -> new JavascriptException("JSExecutor did not return a valid object"));
        final List<WebElement> list = ((List<?>) jsObject).stream()
                .map(element -> (WebElement) element)
                .collect(Collectors.toList());
        return list.isEmpty() ? null : list;
    }
}
