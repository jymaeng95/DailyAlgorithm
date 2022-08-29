package com.second.algorithm.interview.collection.list;

import java.util.NoSuchElementException;

public class CustomDoubleLinkedList<E> implements CustomList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public CustomDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;

    }

    // 노드찾기
    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // index가 head랑 가까운 경우
        if (size / 2 >= index) {
            Node<E> searchNode = head;
            for (int i = 0; i < index; i++) {
                searchNode = searchNode.next;
            }

            return searchNode;
        }

        // index가 tail이랑 가까운 경우
        Node<E> searchNode = tail;
        for (int i = size - 1; i > index; i--) {
            searchNode = searchNode.prev;
        }

        return searchNode;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);

        newNode.next = head;

        // head가 null인 경우 prev에 접근 불가
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;

        // 최초 노드로 삽입되는 경우
        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(E value) {
        // 노드가 없는 경우
        if (size == 0) {
            addFirst(value);
            return;
        }
        Node<E> newNode = new Node<>(value);

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    // 인덱스 탐색 뒤 삽입
    @Override
    public void add(int index, E value) {
        // 인덱스 에러
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            addLast(value);
            return;
        }

        Node<E> prevNode = search(index - 1);
        Node<E> indexNode = prevNode.next;
        Node<E> newNode = new Node<>(value);

        // 연결 끊기
        prevNode.next = null;
        indexNode.prev = null;

        // 이전노드 인덱스 노드 연결 하기
        indexNode.prev = newNode;
        prevNode.next = newNode;

        // 새로운 노드 연걸하기
        newNode.next = indexNode;
        newNode.prev = prevNode;
        size++;
    }

    public E remove() {
        // 값 없는 경우
        if(size == 0) {
            throw new NoSuchElementException();
        }

        Node<E> removeNode = head;
        Node<E> nextNode = head.next;

        E element = removeNode.data;

        if (nextNode != null) {
            nextNode.prev = null;
        }

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        // size == 0인 경우 tail도 null 처리
        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {
        // 인덱스 체크
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // 인덱스가 0인경우 맨앞 제거
        if (index == 0) {
            return remove();
        }

        Node<E> removeNode = search(index);
        Node<E> prevNode = removeNode.prev;
        Node<E> nextNode = removeNode.next;
        E element = removeNode.data;

        // 연결 해제
        removeNode.prev = null;
        removeNode.next = null;
        removeNode.data = null;

        // 마지막 노드가 아닌 경우에만 이전 노드 연결
        if (nextNode != null) {
            // 연결
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        } else{
            prevNode.next = null;
            tail = prevNode;
        }
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> move = head;
        Node<E> prev = head;
        boolean hasValue = false;
        while (move != null) {
            if (move.data.equals(value)) {
                hasValue = true;
                break;
            }

            prev = move;
            move = move.next;
        }

        // 찾는 값이 존재하지 않는 경우
        if (!hasValue) {
            return false;
        }

        // 값이 존재하고 head인 경우
        if (head.equals(move)) {
            remove();
        } else {
            Node<E> nextNode = move.next;

            // 연결 해제
            move.data = null;
            move.next = null;
            move.prev = null;

            if (nextNode != null) {
                prev.next = nextNode;
                nextNode.prev = prev;
            }
            // 마지막 노드가 삭제되는 경우
            else {
                prev.next = null;
                tail = prev;
            }
            size--;
        }
        return true;
    }

    @Override
    public E get(int index) {

        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> node = search(index);
        node.data = null;
        node.data = value;
    }

    @Override
    public boolean contains(Object value) {

        return indexOf(value) >= 0;
    }

    public int indexOf(Object value) {
        Node<E> move = head;
        int index = 0;

        while (move != null) {
            if(move.data.equals(value)) return index;
            move = move.next;
            index++;
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        Node<E> move = tail;
        int index = size - 1;

        while (move != null) {
            if(move.data.equals(value)) return index;
            move = move.prev;
            index--;
        }

        return -1;
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
        Node<E> move = head;

        while (move != null) {
            Node<E> remove = move;
            remove.data = null;
            remove.prev = null;
            remove.next = null;

            move = move.next;
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
