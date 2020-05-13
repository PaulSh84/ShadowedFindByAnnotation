package shadowdom;

import org.openqa.selenium.*;
import java.util.List;

public class MissingWebElement implements WebElement {
    private final NoSuchElementException nsee;

    public MissingWebElement(final NoSuchElementException nsee) {
        this.nsee = nsee;
    }

    @Override
    public void click() {
        throw nsee;
    }

    @Override
    public void submit() {
        throw nsee;
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        throw nsee;
    }

    @Override
    public void clear() {
        throw nsee;
    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String s) {
        return null;
    }

    @Override
    public boolean isSelected() {
        throw nsee;
    }

    @Override
    public boolean isEnabled() {
        throw nsee;
    }

    @Override
    public String getText() {
        throw nsee;
    }

    @Override
    public List<WebElement> findElements(By by) {
        throw nsee;
    }

    @Override
    public WebElement findElement(By by) {
        throw nsee;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        throw nsee;
    }

    @Override
    public Dimension getSize() {
        throw nsee;
    }

    @Override
    public Rectangle getRect() {
        throw nsee;
    }

    @Override
    public String getCssValue(String s) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        throw nsee;
    }

    public NoSuchElementException getNoSuchElementException() {
        return nsee;
    }
}
