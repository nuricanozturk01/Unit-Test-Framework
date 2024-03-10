package tests;

import nuricanozturk.dev.annotation.*;
import nuricanozturk.dev.check.Check;
import org.example.Armstrong;

import java.util.List;
import java.util.stream.IntStream;

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

    @CsvFile("C:\\Users\\hp\\IdeaProjects\\Unit-Test-Framework\\TestProject\\src\\main\\resources\\armstrong.csv")
    @ParameterizedTest
    public void isArmstrongNumber(int number) {
        Check.checkTrue(Armstrong.isArmstrongNumber(number));
    }

    @CsvFile("C:\\Users\\hp\\IdeaProjects\\Unit-Test-Framework\\TestProject\\src\\main\\resources\\not_armstrong.csv")
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
