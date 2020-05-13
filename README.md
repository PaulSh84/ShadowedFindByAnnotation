# Custom ShadowDom FindBy Annotation

### How to extend Selenium FindBy Annotation?

####1. Create your Annotation class as shown in
``` /src/main/java/annotations/ShadowFindBy.java ```

####2. Create Page object class including new Annotation as shown in
``` /src/main/java/ShadowDomPage.java ```

####3. Create CustomPageFactory, add CustomLocatorFactory to choose between DefaultElementLocator and CustomElementLocator.
``` /src/main/java/shadowdom/*```

####4. Create CustomFieldDecorator and update isDecoratableList() method to include your custom annotation.
``` /src/main/java/shadowdom/*```

####5. Create CustomElementLocator and override 2 methods with your custom implementation:
``` /src/main/java/shadowdom/*```
