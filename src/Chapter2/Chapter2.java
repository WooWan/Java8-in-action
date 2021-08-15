package Chapter2;

import java.util.ArrayList;
import java.util.List;

public class Chapter2 {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();

        Apple apple1 = new Apple("Green");
        Apple apple2 = new Apple("Blue");
        Apple apple3 = new Apple("Red");
        Apple apple4 = new Apple("Red");
        inventory.add(apple1);
        inventory.add(apple2);
        inventory.add(apple3);
        inventory.add(apple4);
        List<Apple> result = filter(inventory, (Apple apple) -> "Red".equals(apple.getColor()));
        for (Apple apple : result) {
            System.out.println("apple = " + apple.getColor());
        }
    }
    public interface Predicate<T>{
        boolean test(T t);
    }
    //parameter에 generic 타입이 있다면, method앞에 <T>(generic 타입)을 선언해주어야 한다
    private static <T>List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T item : inventory) {
            if (p.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    static class Apple {
        public String color;

        public Apple(String color) {
            this.color = color;
        }

        public String getColor(){
            return color;
        }
    }
}
