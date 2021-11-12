package training_course;

public class WordCount {
    String word;
    int count = 1;

    public WordCount(String word) {
        this.word = word;
    }

    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WordCount{" +
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
    }

    public WordCount reduce(WordCount value2) {
        return new WordCount(this.word, this.count+value2.count);
    }
}
