package shadowdom;

import annotations.ShadowFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecorator extends DefaultFieldDecorator implements FieldDecorator {
    public CustomFieldDecorator(final ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        if (!WebElement.class.isAssignableFrom(field.getType()) && !isDecoratableList(field)) {
            return null;
        }
        final ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }
        if (WebElement.class.isAssignableFrom(field.getType())) {
            final WebElement proxy = proxyForLocator(loader, locator);
            try {
                proxy.isDisplayed();
                return proxy;
            } catch (final NoSuchElementException nsee) {
                return new MissingWebElement(nsee);
            }
        } else {
            return List.class.isAssignableFrom(field.getType()) ? this.proxyForListLocator(loader, locator) : null;
        }

    }

    protected boolean isDecoratableList(final Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }
        final Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }
        final Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        if (!WebElement.class.equals(listType)) {
            return false;
        }
        return field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null || field.getAnnotation(ShadowFindBy.class) != null;
    }

    @Override
    protected WebElement proxyForLocator(final ClassLoader loader, final ElementLocator locator) {
        final InvocationHandler handler = new CustomLocatingElementHandler(locator);
        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(loader, new Class[] { WebElement.class, WrapsElement.class, Locatable.class }, handler);
        return proxy;
    }
    @Override
    protected List<WebElement> proxyForListLocator(final ClassLoader loader, final ElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);
        List<WebElement> proxy = (List)Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }
}