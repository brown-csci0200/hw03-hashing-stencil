package sol;

import org.junit.Assert;
import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotEquals;

/**
 * Test suite for ChainingHashTable
 * NOTE:  Your tests for Yulp go in a separate file:
 * YulpTests.java
 */
public class Homework3TestSuite {

    @Test
    public void testExample() {
        Assert.assertEquals(2, 1 + 1);
    }

    @Test
    public void testAssertThrows() {
        // Here's an example of how to write a test for a method that throws an exception
        Assert.assertThrows(KeyNotFoundException.class, () -> this.throwsExceptionExample());
    }

    /**
     * This method throws an exception to provide an
     * example for testAssertThrows
     * @throws KeyNotFoundException
     */
    private void throwsExceptionExample() throws KeyNotFoundException {
        throw new KeyNotFoundException();
    }

}
