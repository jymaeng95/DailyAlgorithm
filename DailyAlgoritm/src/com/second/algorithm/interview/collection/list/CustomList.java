package com.second.algorithm.interview.collection.list;

public interface CustomList<E> {
    /**
     * 리스트 원소 추가
     *
     * @param value 추가할 원소
     * @return 리스트 내 중복 원소있는 경우 {@code false}, 없는 경우 {@code true}
     */
    boolean add(E value);

    /**
     * index 위치에 원소 추가
     * @param index 추가 위치
     * @param value 추가할 원소
     */
    void add(int index, E value);

    /**
     * index 위치 원소 삭제
     * @param index 삭제 위치
     * @return 삭제한 원소
     */
    E remove(int index);

    /**
     * 특정 원소 삭제, 동일 원소 여러 개인 경우 처음 발견한 원소 삭제
     * @param value 삭제할 원소
     * @return 원소가 없거나, 실패하는 경우 {@code false}, 삭제 성공하는 경우 {@code true}
     */
    boolean remove(Object value);

    /**
     * index 위치 원소 조회
     * @param index 조회할 위치
     * @return 조회한 원소
     */
    E get(int index);

    /**
     * 리스트에서 특정 위치 원소 대체
     * @param index 대체할 위치
     * @param value 대체할 원소
     */
    void set(int index, E value);

    /**
     * 원소 포함하는지 여부
     * @param value 찾을 원소
     * @return 원소 존재하는 경우 {@code true}, 존재하지 않는 경우 {@code false}
     */
    boolean contains(Object value);

    /**
     * 리스트 원소 개수
     * @return 원소 개수 반환
     */
    int size();

    /**
     * 리스트가 비어있는지 여부
     * @return 비었으면 {@code true} 원소가 있는 경우 {@code false}
     */
    boolean isEmpty();

    /**
     * 원소 전체 삭제
     */
    public void clear();

}
