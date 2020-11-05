package com.study;

public class MaxNumberImpl implements MaxNumber {
    @Override
    public int getMaxNumber(int x, int y) {
        return x >= y ? x : y;
    }
}
