import Exception.ArrayIsFullException;
import Exception.IncorrectIndexException;
import Exception.NullItemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private final IntegerListImpl underTest = new IntegerListImpl();

    IntegerListImpl otherList = new IntegerListImpl();

    Integer integer1 = 11111;
    Integer integer2 = 22222;
    Integer integer3 = 33333;
    Integer integer4 = 44444;
    Integer integer5 = 55555;
    Integer integer6 = 66666;


    @Test
    void add_shouldAddIntegerElementToArrayAndReturnIntegerElement() {
        underTest.add(integer1);
        assertEquals(underTest.get(0), integer1);
    }

    @Test
    void add_shouldThrowArrayIsFullExceptionWhenSizeIsFull() {
        underTest.add(integer1);
        underTest.add(integer2);
        underTest.add(integer3);
        underTest.add(integer4);
        underTest.add(integer5);
        assertThrows(ArrayIsFullException.class, () -> underTest.add(integer6));
    }


    @Test
    void AddWithIndex_shouldAddElementWithIndexAndReturnInteger() {
        underTest.add(integer1);
        underTest.add(integer2);
        underTest.add(integer3);
        Integer expectedResult = 77777;
        underTest.add(4, expectedResult);
        assertEquals(expectedResult, underTest.get(4));
    }

    @Test
    void AddWithIndex_shouldThrowArrayIsFullExceptionWhenSizeIsFull() {
        underTest.add(integer1);
        underTest.add(integer2);
        underTest.add(integer3);
        underTest.add(integer4);
        underTest.add(integer5);
        assertThrows(ArrayIsFullException.class, () -> underTest.add(5, integer6));
    }

    @Test
    void AddWithIndex_shouldThrowNullItemException() {
        assertThrows(NullItemException.class, () -> underTest.add(1, null));
    }

    @Test
    void set_shouldSetStringInArray() {
        underTest.add(integer1);
        underTest.add(integer2);
        underTest.add(integer3);
        underTest.add(integer4);
        underTest.add(integer5);
        Integer expectedResult = 77777;
        underTest.set(1, expectedResult);
        assertEquals(expectedResult, underTest.get(1));
    }

    @Test
    void set_shouldThrowIncorrectIndexExceptionWhenIndexIsBiggerThatSizeOfArray() {
        assertThrows(IncorrectIndexException.class, () -> underTest.set(6, 77777));
    }

    @Test
    void removeByItem_shouldReturnItemAfterRemove() {
        underTest.add(integer1);
        Integer expectedResult = 11111;
        assertEquals(expectedResult, underTest.remove(integer1));
    }

    @Test
    void testRemoveByIndex_shouldReturnItemAfterRemove() {
        underTest.add(integer1);
        Integer expectedResult = 11111;
        assertEquals(expectedResult, underTest.remove(0));
    }


    @Test
    void contains_shouldReturnTrueWhenArrayContainsElement() {
        underTest.add(integer1);
        assertTrue(underTest.contains(11111));
    }

    @Test
    void contains_shouldReturnFalseWhenArrayContainsElement() {
        underTest.add(integer1);
        assertFalse(underTest.contains(22222));
    }


    @Test
    void indexOf_shouldReturnMinusOneWhenElementAreNotInArray() {
        int expectedResult = -1;
        assertEquals(expectedResult, underTest.indexOf(11111));
    }

    @Test
    void indexOf_shouldReturnIndexWhenElementAreInArray() {
        underTest.add(integer1);
        int expectedResult = 0;
        assertEquals(expectedResult, underTest.indexOf(11111));
    }

    @Test
    void lastIndexOf_shouldReturnMinusOneWhenElementAreNotInArray() {
        int expectedResult = -1;
        assertEquals(expectedResult, underTest.lastIndexOf(11111));
    }

    @Test
    void lastIndexOf_shouldReturnIndexWhenElementAreInArray() {
        underTest.add(integer1);
        int expectedResult = 0;
        assertEquals(expectedResult, underTest.indexOf(11111));
    }

    @Test
    void get_shouldThrowIncorrectIndexExceptionWhenWrongIndexInjected() {
        assertThrows(IncorrectIndexException.class, () -> underTest.get(6));
    }

    @Test
    void get_shouldReturnElementWhenIndexIsCorrect() {
        underTest.add(integer1);
        underTest.add(integer2);
        Integer expectedResult = 11111;
        assertEquals(expectedResult, underTest.get(0));
    }

    @Test
    void testEquals_shouldReturnTrueWhenOtherlistContainsUndertest() {
        underTest.add(integer1);
        underTest.add(integer2);
        otherList.add(integer1);
        otherList.add(integer2);

        boolean expectedResult = otherList.equals(underTest);
        assertEquals(expectedResult, true);
    }

    @Test
    void testEquals_shouldReturnFalseWhenOtherlistNotContainsUndertest() {
        underTest.add(integer4);
        underTest.add(integer3);
        otherList.add(integer1);
        otherList.add(integer2);

        boolean expectedResult = otherList.equals(underTest);
        assertEquals(expectedResult, false);
    }


    @Test
    void size_shouldReturnTwoWhenAreTwoElementInArray() {
        underTest.add(integer1);
        underTest.add(integer2);
        int expectedResult = 2;
        assertEquals(expectedResult, underTest.size());
    }

    @Test
    void isEmpty_returnTrueWhenArrayIsEmpty() {
        assertTrue(underTest.isEmpty());
    }

    @Test
    void isEmpty_returnFalseWhenArrayIsNotEmpty() {
        underTest.add(integer1);
        assertFalse(underTest.isEmpty());
    }

    @Test
    void clear_shouldReturnNullWhenArrayAreCleared() {
        underTest.add(integer1);
        underTest.add(integer2);
        int expectedResult = 0;
        underTest.clear();
        assertEquals(expectedResult, underTest.size());
    }

    @Test
    void toArray_shouldReturnRightSizeOfArraysWithoutNull() {

        underTest.add(integer1);
        underTest.add(integer2);

        underTest.toArray();
        assertEquals(2, underTest.size());

    }
}