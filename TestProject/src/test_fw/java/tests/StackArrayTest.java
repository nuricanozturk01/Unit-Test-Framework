package tests;

import nuricanozturk.dev.annotation.BeforeEach;
import nuricanozturk.dev.annotation.TestClass;
import nuricanozturk.dev.annotation.UnitTest;
import nuricanozturk.dev.check.Check;

import java.util.LinkedList;

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
