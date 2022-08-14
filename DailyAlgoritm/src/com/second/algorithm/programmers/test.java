package com.second.algorithm.programmers;

public class test {
    public static void main(String[] args) {
        Person person = new Person(10,"Test");
        System.out.println("person.getAge() = " + person.getAge());
        System.out.println("person.getName() = " + person.getName());
        updateAge(person);

        String name = "name";
        updateName(name);
        System.out.println("After update, person.getAge() = " + person.getAge());
        System.out.println("After person.getName() = " + name);

    }

    private static void updateName(String name) {
//        name = new String("zayson");
        name = "맹";
    }

    private static void updateAge(Person person) {
        person.setAge(20);
    }
    static class Person {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
