package com.second.algorithm.interview.collection.list;

import java.util.Arrays;

/**
 * ArrayList는 Object[] 배열을 이용한다.
 * 사이즈를 동적으로 할당하는 것이 가능하다.
 * 리스트 원소 사이에 null 값을 갖지 않는다.
 * @param <E>
 */
public class CustomArrayList<E> implements CustomList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_EMPTY_ARRAY = {};

    private int size;  //원소 개수

    Object[] array;

    // ArrayList는 사이즈를 지정하는 것이 가능하다.
    public CustomArrayList () {
        array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // 초기 용량을 지정하는 경우
    public CustomArrayList(int capacity) {
        array = new Object[capacity];
        this.size = 0;
    }

    //  동적 할당을 위한 배열 사이즈 조정
    private void resize() {
        // 현재 배열의 최대 용적 개수
        int presentCapacity = array.length;

        // 원소가 가득 찬 경우 사이즈를 두배 늘려준다.
        if(size == presentCapacity) {
            int newCapacity = presentCapacity * 2;
            array = Arrays.copyOf(array, newCapacity);
            return;
        }

        // 원소가 하나도 없는 경우
        if (Arrays.equals(array, DEFAULT_EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 원소가 Capacity 절반으로 있는 경우
        if (size < (presentCapacity / 2)) {
            // default 배열 사이즈로 줄지 /2한 용량으로 줄지 최대값 비교
            int newCapacity = Math.max(DEFAULT_CAPACITY, presentCapacity / 2);
            array = Arrays.copyOf(array, newCapacity);
            return;
        }
    }

    // 원소 가장 뒤에 추가
    @Override
    public boolean add(E value) {
        // 용량이 가득찬 경우 사이즈 증가
        addLast(value);
        return true;
    }

    // 원소 마지막에 넣기
    private void addLast(E value) {
        if(size == array.length) {
            resize();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(int index, E value) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        // 마지막에 원소가 추가되는 경우
        if(index == size) {
            addLast(value);
        }
        // 그 외의 경우 (원소를 넣고 하나씩 뒤로 밀어 줘야한다.)
        else {
            // 용량이 가득 찬 경우
            if(size == array.length) {
                resize();
            }

            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = value;
            size++;
        }
    }

    public void addFirst(E value) {
        add(0, value);
    }

    // 파라미터 인덱스 삭제
    @Override
    public E remove(int index) {
        if(index >= size || index < 0 )
            throw new IndexOutOfBoundsException();

        // 해당 인덱스 삭제 후 댕겨주기
        E element = (E) array[index];
        array[index] = null;

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        size--;
        resize();

        return element;
    }

    // 동일 값 존재 시 가장 처음에 마주치는 원소 삭제
    @Override
    public boolean remove(Object value) {
        int removeIndex = indexOf(value);

        // 원소가 없는 경우
        if(removeIndex < 0) return false;
        remove(removeIndex);

        return true;
    }

    // 데이터 조회
    @Override
    public E get(int index) {
        if(index >= size || size < 0)
            throw new IndexOutOfBoundsException();

        return (E) array[index];
    }

    // 해당하는 인덱스에 데이터 변경
    @Override
    public void set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = value;
    }

    // 데이터 포함하는지 확인(indexOf)
    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    // 파라미터 값이 존재하는 경우 인덱스 리턴
    public int indexOf(Object value) {
        for (int index = 0; index < size; index++) {
            if(array[index].equals(value)) return index;
        }

        return -1;
    }

    // 파라미터 값이 존재하는 마지막 인덱스 리턴
    public int lastIndexOf(Object value) {
        for (int index = size - 1; index >= 0; index--) {
            if(array[index].equals(value)) return index;
        }

        return -1;
    }

    // 사이즈 조회
    @Override
    public int size() {
        return size;
    }

    // 원소가 비었는지 확인
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
        resize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object value : array) {
            sb.append(value + ", ");
        }
        sb.trimToSize();
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
