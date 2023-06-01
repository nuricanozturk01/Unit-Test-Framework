# Unit-Test-Framework

### This framework allows you to create and run unit tests in Java, similar to JUnit. Below, you will find basic information and examples on how to use the Unit-Test-Framework.

### Package:

- #### Package structure must be below. This is an example Test Project. One important thing to note is that there must be a
  **"test_fw"** directory in the "src" directory, and this directory **must be marked as the "source"** Java package.
  _You can change name the _test_ package_ under the Java package as you wish.
-
- ![package.png](pictures%2Fpackage.png)

## Usage

#### 1. Add the Unit-Test-Framework library to your project.

#### 2. Create Test class and annotate with `@TestFrameworkApplication` and in main method,you should call`TestFrameworkApp.run(TestApplication.class, DisplayType.Console)`. You can see an example below.

##### Trigger Method

```java

@TestFrameworkApplication
public class TestApplication {
    public static void main(String[] args) {
        TestFrameworkApp.run(TestApplication.class, DisplayType.Console);
    }
}
```

## You need to know about Annotations

#### 1. `@UnitTest` annotation

        - If you use @UnitTest annotation, you cannot use parameter on the method and you can use ONLY @DisplayName annotation with @UnitTest annotation.

#### 2. `@ParameterizedTest` annotation

        - When using the ParameterizedTest annotation, you must always use either the @CsvSource annotation or the @CsvFile annotation. Additionally, you can use the DisplayName annotation if desired. However, when using the ParameterizedTest annotation, you cannot use the @UnitTest annotation or any others except for the ones mentioned above.

#### 3. `@BeforeEach, @BeforeAll, @AfterEach, @AfterAll` annotations

        - If you are using any of these annotations, you can only use the @DisplayName annotation alongside them.

| Annotation         | Has Method Parameter | Usable Annotations                      | Unsuable Annotations                                                                         |
|--------------------|:--------------------:|-----------------------------------------|----------------------------------------------------------------------------------------------|
| @UnitTest          |          NO          | @DisplayName                            | @ParameterizedTest, @CsvFile, @CsvSource<br/>@BeforeAll, @Before Each, @AfterAll, @AfterEach |
| @ParameterizedTest |     YES(Only 1)      | @CsvFile OR @CsvSource<br/>@DisplayName | @UnitTest,@BeforeAll, @BeforeEach, @AfterAll, @AfterEach                                     |
| @DisplayName       |          NO          | All of them                             | DOES NOT EXISTS                                                                              |
| @BeforeEach        |          NO          | @DisplayName                            | Others                                                                                       |
| @BeforeAll         |          NO          | @DisplayName                            | Others                                                                                       |
| @AfterEach         |          NO          | @DisplayName                            | Others                                                                                       |
| @AfterAll          |          NO          | @DisplayName                            | Others                                                                                       |

## Contributing and Contact
#### If you have any bug reports, suggestions, or would like to contribute to the Unit-Test-Framework, please contact me with email: **canozturk309@gmail.com**

## Example
