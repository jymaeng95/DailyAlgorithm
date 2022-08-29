package com.second.algorithm.interview.collection.list;

import java.util.NoSuchElementException;

public class CustomSingleLinkedList<E> implements CustomList<E> {
    // head, tail
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public CustomSingleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // 인덱스로 노드 탐색
    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // head(0)부터 index까지
        Node<E> searchNode = head;
        for (int i = 0; i < index; i++) {
            searchNode = searchNode.next;
        }

        return searchNode;
    }

    // 원소 추가
    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;

        // 사이즈가 1개인 경우
        if (head.next == null)
            tail = head;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        // 저장된 노드가 없는 경우 addFirst
        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public boolean add(E value) {
        addLast(value);

        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) addFirst(value);
        else if (index == size) addLast(value);
        else {
            Node<E> prevNode = search(index - 1);
            Node<E> indexNode = prevNode.next;
            Node<E> newNode = new Node<>(value);

            prevNode.next = null;
            newNode.next = indexNode;
            prevNode.next = newNode;
            size++;
        }
    }

    // 가장 앞 노드 삭제
    public E remove() {
        if (head == null)
            throw new NoSuchElementException();

        // 삭제할 데이터
        E element = head.data;

        // 다음 노드가 head
        Node<E> nextNode = head.next;

        // 현재 head null 처리
        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        // head 삭제 후 원소가 없는 경우 tail null 초기화
        if (size == 0)
            tail = null;

        return element;
    }

    @Override
    public E remove(int index) {
        // index 범위 밖인 경우
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return remove();
        }

        // 인덱스만큼 이동
        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;

        prevNode.next = null;
        prevNode.next = nextNode;
        if (prevNode.next == null)
            tail = prevNode;

        E element = removeNode.data;
        removeNode.data = null;
        removeNode.next = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        Node<E> move = head;
        boolean hasValue = false;

        while (move != null) {
            if (value.equals(move.data)) {
                hasValue = true;
                break;
            }
            prevNode = move;
            move = move.next;
        }

        if (!hasValue) return false;

        // 값이 있는 경우
        if (move.equals(head)) {
            remove();
        } else {
            prevNode.next = null;
            prevNode.next = move.next;

            if (prevNode.next == null) {
                tail = prevNode;
            }
            move.data = null;
            move.next = null;
            size--;
        }
        return true;
    }

    // value index 찾기
    public int indexOf(Object value) {
        Node<E> move = head;
        int index = 0;

        while (move.next != null) {
            if (move.data.equals(value)) return index;
            move = move.next;
            index++;
        }

        return -1;
    }

    // 데이터 조회
    @Override
    public E get(int index) {
        return search(index).data;
    }

    // 노드 대체
    @Override
    public void set(int index, E value) {
        Node<E> searchNode = search(index);
        searchNode.data = null;
        searchNode.data = value;
    }

    // indexOf 값이 -1이 아닌 경우 포함
    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
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
        // 모든 참조 끊어주기
        while (head != null) {
            Node<E> clear = head;
            head = head.next;
            clear.data = null;
            clear.next = null;
        }

        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        Node<E> move = head;
        while (move != null) {
            sb.append(move.data +",");
            move = move.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }
}
