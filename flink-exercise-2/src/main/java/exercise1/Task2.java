package exercise1;


import java.util.Arrays;

public class Task2 {
    //Imperative solution

    private static class Counter {
        int lines;
        int words;
        int characters;

        public Counter(int lines, int words, int characters) {
            this.lines = lines;
            this.words = words;
            this.characters = characters;
        }

        public Counter reduce (Counter other){
            return new Counter(this.lines + other.lines,
                    this.words + other.words,
                    this.characters + other.characters);
        }

        @Override
        public String toString() {
            return String.format("Lines: %d Words: %d Characters: %d", lines, words, characters);
        }
    }
    public static void wc1 (String fileContent){
        int linesC=0, wordsC=0, charsC=0;
        String [] lines = fileContent.split("\n");
        linesC = lines.length;

        for (String line : lines){
            String [] words = line.split("\\s+");
            wordsC+=words.length;
            charsC+=line.length();
        }

        System.out.printf("Lines: %d Words: %d Characters: %d", linesC, wordsC, charsC);
    }

    //Functional solution with reduce operator
    public static void wc2 (String fileContent) {
        Counter result = Arrays.stream(fileContent.split("\n"))
                .map(line -> new Counter(1, line.split("\\s+").length, line.length()))
                .reduce(
                        new Counter(0,0,0),
                        (leftCounter, rightCounter) -> leftCounter.reduce(rightCounter)
                );

        System.out.println(result.toString());
    }
    public static void main(String[] args) {

    }
}
