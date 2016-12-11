/**
 * Created by jifeiqian on 12/10/16.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> words = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            words.addLast(word.charAt(i));
        }
        return words;
    }

    public static boolean isPalindrome(String word) {
        Deque<Character> words = wordToDeque(word);
        while (words.size() > 1) {
            Character head = words.removeFirst();
            Character tail = words.removeLast();
            if (head != tail) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> words = wordToDeque(word);
        while (words.size() > 1) {
            Character head = words.removeFirst();
            Character tail = words.removeLast();
            if (!cc.equalChars(head, tail)) {
                return false;
            }
        }

        return true;
    }
}
