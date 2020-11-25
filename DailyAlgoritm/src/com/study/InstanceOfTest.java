package com.study;

public class InstanceOfTest {
    static class Suv {
        public void isType() {
            System.out.println("yes, Type of Car");
        }
    }
    static class Santafe extends Suv {
        @Override
        public void isType() {
            System.out.println("Santafe is Suv Type");
        }
    }
    public static void main(String[] args) {

        Suv suv = new Suv();
        suv.isType();
        System.out.println(suv instanceof Suv);     //true
        System.out.println(suv instanceof Santafe); //Suv 클래스는 자신만 참조하므로 false

        //Suv 부모 클래스가 Santafe 클래스를 참조함을 instanceof로 확인가능
        Suv suv1 = new Santafe();
        suv1.isType();
        System.out.println(suv1 instanceof Suv);      //true
        System.out.println(suv1 instanceof Santafe);  //true

    }
}
