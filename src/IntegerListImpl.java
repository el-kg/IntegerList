import java.util.Arrays;

import Exception.ArrayIsFullException;
import Exception.IncorrectIndexException;
import Exception.NullItemException;

import static java.lang.System.*;

import static java.lang.System.arraycopy;

public class IntegerListImpl implements IntegerList {
    public final Integer[] array;
    private int size = 0;

    public IntegerListImpl() {
        array = new Integer[5];
    }

    public IntegerListImpl(int desiredSize) {
        array = new Integer[desiredSize];
    }

    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        array[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            array[size++] = item;
            return item;
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = array[index];
        if (index != size) {
            System.arraycopy(array, index + 1, array, index, size - index);
            size--;
        }
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] arrayCopy = toArray();
        sort(arrayCopy);
        return binarySearch(arrayCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("!!!");
        }
    }

    private void validateSize() {
        if (size == array.length) {
            throw new ArrayIsFullException("Нет больше места!!!");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > array.length) throw new IncorrectIndexException("Нет элемента с таким индексом!!!");
    }

    private void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arr[mid])) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
