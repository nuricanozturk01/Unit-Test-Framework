# Unit-Test-Framework

 This framework allows you to create and run unit tests in Java, similar to JUnit. Below, you will find basic information and examples on how to use the Unit-Test-Framework.

### Package:

- Package structure must be below. This is an example Test Project. One important thing to note is that there must be a
  **"test_fw"** directory in the "src" directory, and this directory **must be marked as the "source"** Java package.
  _You can change name the _test_ package_ under the Java package as you wish.
-
- ![package](https://github.com/nuricanozturk01/Unit-Test-Framework/assets/62218588/45713e5e-b505-4e46-b055-1ba65ee3873a)


## Usage

1. Firstly, you should install project to local using maven then add the Unit-Test-Framework library to your project.

   ```` 
        <dependency>
            <groupId>nuricanozturk.dev</groupId>
            <artifactId>Unit-Test-Framework</artifactId>
            <version>1.0.0</version>
        </dependency>
   ````

2. Create Test class and annotate with `@TestFrameworkApplication` and in main method,you should
   call`TestFrameworkApp.run(TestApplication.class, DisplayType.Console)`. You can see an example below.

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

1. `@UnitTest` annotation

       - If you use @UnitTest annotation, you cannot use parameter on the method and you can use ONLY @DisplayName annotation with @UnitTest annotation.

2. `@ParameterizedTest` annotation

       - When using the ParameterizedTest annotation, you must always use either the @CsvSource annotation or the @CsvFile annotation. Additionally, you can use the DisplayName annotation if desired. However, when using the ParameterizedTest annotation, you cannot use the @UnitTest annotation or any others except for the ones mentioned above.

3. `@BeforeEach, @BeforeAll, @AfterEach, @AfterAll` annotations

       - If you are using any of these annotations, you can only use the @DisplayName annotation alongside them.
4. `@TestClass` annotation

        - You should use only Testing class.

## Annotation Table

| Annotation                | Has Method Parameter | Usable Annotations                      | Unsuable Annotations                                                                         |
|---------------------------|:--------------------:|-----------------------------------------|----------------------------------------------------------------------------------------------|
| @UnitTest                 |          NO          | @DisplayName                            | @ParameterizedTest, @CsvFile, @CsvSource<br/>@BeforeAll, @Before Each, @AfterAll, @AfterEach |
| @ParameterizedTest        |     YES(Only 1)      | @CsvFile OR @CsvSource<br/>@DisplayName | @UnitTest,@BeforeAll, @BeforeEach, @AfterAll, @AfterEach                                     |
| @DisplayName              |          NO          | All of them                             | DOES NOT EXISTS                                                                              |
| @BeforeEach               |          NO          | @DisplayName                            | Others                                                                                       |
| @BeforeAll                |          NO          | @DisplayName                            | Others                                                                                       |
| @AfterEach                |          NO          | @DisplayName                            | Others                                                                                       |
| @AfterAll                 |          NO          | @DisplayName                            | Others                                                                                       |
| @TestClass                |          NO          | NOT EXISTS                              | Others                                                                                       |
| @TestFrameworkApplication |          NO          | NOT EXISTS                              | Others                                                                                       |

## Contributing and Contact

If you have any bug reports, suggestions, or would like to contribute to the Unit-Test-Framework, please contact me with
email: **canozturk309@gmail.com**

## Example

```java
@TestClass
public class LinkedListTest {
    private LinkedList<String> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();
    }

    @UnitTest
    void testInsertFirst() {
        linkedList.addFirst("Nuri");
        linkedList.addFirst("Can");
        Check.checkTrue(2 == linkedList.size());
        Check.checkEqual("Can", linkedList.removeFirst());
        Check.checkEqual("Nuri", linkedList.removeFirst());
        Check.checkTrue(linkedList.isEmpty());
    }

    @UnitTest
    void testInsertLast() {
        linkedList.addLast("John");
        linkedList.addLast("Wick");
        Check.checkEqual(2, linkedList.size());
        Check.checkEqual("John", linkedList.removeFirst());
        Check.checkEqual("Wick", linkedList.removeFirst());
        Check.checkTrue(linkedList.isEmpty());
    }
}
```

```java
@TestClass
public class QueueArrayTest {
    private static final String[] stringArr = new String[]{"Nuri", "Can", "ozturk", "stack", "array"};
    private QueueArray<String> m_queue;

    @BeforeEach
    public void setup() {
        m_queue = new QueueArray<>();
        Arrays.stream(stringArr).forEach(m_queue::enqueue);
    }

    @DisplayName("Validate Enqueue Operation Size")
    @UnitTest
    public void testPushOperationSize() {
        Check.checkEqual(stringArr.length, m_queue.getSize());
    }

    @DisplayName("Validate Queue Items")
    @UnitTest
    public void testQueueItems() {
        int index = 0;
        while (!m_queue.isEmpty())
            Check.checkEqual(stringArr[index++], m_queue.dequeue().orElse(null));
    }

    @DisplayName("Dequeue all item and check size of Queue")
    @UnitTest
    public void popAndSizeZeroTest() {
        while (!m_queue.isEmpty())
            m_queue.dequeue();
        Check.checkEqual(0, m_queue.getSize());
    }
}
```

```java
@TestClass
public class MyTest {
    private List<String> stringList;
    private List<Integer> integerList;

    @BeforeAll
    public void startMessage() {
        System.out.println("Start Test");
    }

    @BeforeEach
    public void setUp() {
        stringList = List.of("Nuri", "Can", "OZTURK", "Halil", "Java");
        integerList = List.of(12, 52, 66, 88, 122);
    }

    @CsvSource("Nuri, Java")
    @ParameterizedTest
    public void isIncludeName(String name) {
        Check.checkTrue(stringList.contains(name));
    }

    @UnitTest
    public void isDivideTwo() {
        IntStream.range(0, integerList.size()).forEach(i -> Check.checkTrue(integerList.get(i) % 2 == 0));
    }

    @CsvFile("/Users/nuricanozturk/IdeaProjects/TestProject/src/main/resources/armstrong.csv")
    @ParameterizedTest
    public void isArmstrongNumber(int number) {
        Check.checkTrue(Armstrong.isArmstrongNumber(number));
    }

    @CsvFile("/Users/nuricanozturk/IdeaProjects/TestProject/src/main/resources/not_armstrong.csv")
    @ParameterizedTest
    public void isNotArmstrongNumber(int number) {
        Check.checkFalse(Armstrong.isArmstrongNumber(number));
    }

    @UnitTest
    private void test() {
        Check.checkEqual(stringList.get(0), "Nuri");
    }

    @AfterAll
    public void finishMessage() {
        System.out.println("Finish!");
    }
}
```

### Test Result
#### Success
![1](https://github.com/nuricanozturk01/Unit-Test-Framework/assets/62218588/26498caf-2a71-47d6-89e5-14c6527043aa)

![2](https://github.com/nuricanozturk01/Unit-Test-Framework/assets/62218588/280bc2e6-f8ae-407e-9570-8c76007d9dc9)

#### Fail
![fail](https://github.com/nuricanozturk01/Unit-Test-Framework/assets/62218588/e58fe064-34b8-4766-8de9-6fb221572d16)


### Bugs and Features
 - If you detect bug, contact me.
 - No new feature will be added.
