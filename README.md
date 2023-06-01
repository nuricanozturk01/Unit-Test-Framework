# Unit-Test-Framework

### This framework allows you to create and run unit tests in Java, similar to JUnit. Below, you will find basic information and examples on how to use the Unit-Test-Framework.

## Usage

### Package:
 - Package structure must be below:
 ![package.png](pictures%2Fpackage.png)
### Trigger Method
```java
@TestFrameworkApplication
public class TestApplication {
    public static void main(String[] args) {
        TestFrameworkApp.run(TestApplication.class, DisplayType.Console);
    }
}

```
