package training_course;

import org.apache.flink.api.java.operators.Operator;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SquareFunction implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer * integer;
    }
}

public class Primeri {

    long factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    int sum(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        Function<Integer, Integer> squareFunction = integer -> integer * integer;

        Random random = new Random();
        //anonymous class
        Supplier<Integer> supplier1 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return random.nextInt(100);
            }
        };
        //lambda expression
        Supplier<Integer> supplier2 = () -> random.nextInt(100);

        //anonymous class
        Consumer<Integer> consumer1 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.printf("Printing the integer [%d] through anonymous function", integer);
            }
        };

        //lambda expression
        Consumer<Integer> consumer2 = integer -> System.out.printf("Printing the integer [%d] through anonymous function", integer);

        //anonymous class
        Predicate<Integer> isEven = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        };

        //lambda expression
        Predicate<Integer> isEven2 = integer -> integer % 2 == 0;


        UnaryOperator<Integer> increment = new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        };

        UnaryOperator<Integer> decrement = integer -> integer - 1;

        BinaryOperator<Integer> sum = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };

        BinaryOperator<Integer> sum1 = (integer, integer2) -> integer + integer2;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = list.stream();

//        stream.reduce()
        Stream<Integer> val = list.stream();
//        Optional<Integer> min = stream.min(Comparator.naturalOrder());
//        if (min.isPresent()){
//            System.out.println(min.get());
//        } else {
//            System.out.println("Empty stream");
//        }

//        Stream<Character> letterStream = stream.map(integer -> (char) ('A' + integer - 1));
//        stream = stream.filter(integer -> integer % 2 == 0);
//        stream.forEach(integer -> System.out.println(integer));


        List<String> words = Arrays.asList("FINKI", "HPC", "MK");
        Stream<Character> lettersInWord = words.stream()
                .flatMap(word -> word.chars().mapToObj(i -> (char) i));
        Set<Character> uniqueLetters = lettersInWord
                .collect(Collectors.toCollection(() -> new TreeSet<>()));

    }

}
