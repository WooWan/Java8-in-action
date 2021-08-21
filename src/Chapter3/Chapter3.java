package Chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Chapter3 {
//    List<Apple> apples = filter(inventory, (Apple a) -> a.getWeight() > 150);

    public static void test(){
        System.out.println();
    }
    public static void process(Runnable r){
//        r.run();
        r.run();
        System.out.println(r);

    }

    public static void main(String[] args) {
        test();
        process(() -> System.out.println("hello"));
    }

}
class Apple{
    public String name;

    public Apple(String name) {
        this.name = name;
    }
}
