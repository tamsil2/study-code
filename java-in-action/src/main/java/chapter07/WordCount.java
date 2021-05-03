package chapter07;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {
    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita "
                    + "mi  ritrovai in una  selva oscura"
                    + " che la  dritta via era   smarrita ";

    public static void main(String[] args) {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("Found " + countWords(stream) + " words");
        System.out.println("Found " + countWords(stream2) + " words");
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()) {
            if(Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if(lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    private static class WordCounter {
        private final int counter;
        private final boolean lastSpace;
        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if(Character.isWhitespace(c)) {
                return lastSpace ? this : new WordCounter(counter, true);
            } else {
                return lastSpace ? new WordCounter(counter+1, false) : this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }

    private static class WordCounterSpliterator implements Spliterator<Character> {
        private final String string;
        private int currentChar = 0;

        public WordCounterSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if(currentSize < 10) {
                return null;
            }
            for(int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
                if(Character.isWhitespace(string.charAt(splitPos))) {
                    Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
