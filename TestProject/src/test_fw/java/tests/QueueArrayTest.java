/**
 * FILE		    : QueueArrayTest.java
 * AUTHOR		: Nuri Can OZTURK
 * LAST UPDATE	: 28.05.2023
 * QueueArrayTest class is test class for QueueArray class.
 * Copyleft (c) DSA-Lib
 * All Rights Free
 */
package tests;

import nuricanozturk.dev.annotation.BeforeEach;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.annotation.TestClass;
import nuricanozturk.dev.annotation.UnitTest;
import nuricanozturk.dev.check.Check;
import org.example.QueueArray;


import java.util.Arrays;


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
