```java
@TestClass
public class StackArrayTest {
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
    @AfterAll
    public void finishMessage() {
        System.out.println("Finish!");
    }
}

```
### Test Result

![1.png](..%2Fpictures%2F1.png)

![2.png](..%2Fpictures%2F2.png)