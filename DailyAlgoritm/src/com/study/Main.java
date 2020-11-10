package com.study;

public class Main {
    public static void main(String[] args) {
        //1. 인터페이스를 직접 클래스로 구현 후 메인 메소드에서 생성 후 호출
//        MaxNumber maxNumber = new MaxNumberImpl();
//        System.out.println(maxNumber.getMaxNumber(3,1));

        //2. 익명함수로 메인 클래스 내에서 구현하여 호출
//        MaxNumber maxNumber = new MaxNumber() {
//            @Override
//            public int getMaxNumber(int x, int y) {
//                return x >= y ? x : y;
//            }
//        };
//        System.out.println(maxNumber.getMaxNumber(3,1));

        //3. 람다식을 이용하여 호출 방식
//        MaxNumber maxNumber = (x, y) -> x >= y ? x : y;
//        System.out.println(maxNumber.getMaxNumber(3,1));

        Car car = new Tucson();
//        Car car = new Tico();

        System.out.println("투싼 신형은 하이브리드인가? : "+ car.isHybrid());
//        System.out.println("티코는 하이브리드인가? : " + car.isHybrid());
    }
}
