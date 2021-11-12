package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {

    public static List<Integer> lessThan1 (List<Integer> list, int n){
        List<Integer> result = new ArrayList<>();
        for (int i=0;i<n;i++){
            int element = list.get(i);
            if (element<n)
                result.add(element);
        }
        return result;
    }

    public static List<Integer> lessThan2 (List<Integer> list, int n){
        return list.stream()
                .filter(element -> element<n)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }
}
