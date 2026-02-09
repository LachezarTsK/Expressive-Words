
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final int MIN_SIZE_STRETCHY_GROUP = 3;
    private Word targetWord;

    public int expressiveWords(String target, String[] words) {
        targetWord = createWord(target);
        int countExpressiveWords = 0;

        for (String word : words) {
            Word wordToStretch = createWord(word);
            if (wordIsStretchy(wordToStretch)) {
                ++countExpressiveWords;
            }
        }
        return countExpressiveWords;
    }

    private boolean wordIsStretchy(Word word) {
        if (word.patternLettersInGroups.size() != targetWord.patternLettersInGroups.size()) {
            return false;
        }

        for (int i = 0; i < word.patternLettersInGroups.size(); ++i) {
            char targetLetter = targetWord.patternLettersInGroups.get(i);
            int targetFrequency = targetWord.frequencyLettersInGroups.get(i);

            char letter = word.patternLettersInGroups.get(i);
            int frequency = word.frequencyLettersInGroups.get(i);

            if ((letter != targetLetter)
                    || (frequency > targetFrequency)
                    || (frequency != targetFrequency && targetFrequency < MIN_SIZE_STRETCHY_GROUP)) {
                return false;
            }
        }
        return true;
    }

    private static Word createWord(String input) {
        Word word = new Word();
        word.patternLettersInGroups.add(input.charAt(0));
        word.frequencyLettersInGroups.add(1);

        for (int i = 1; i < input.length(); ++i) {
            if (input.charAt(i) != word.patternLettersInGroups.get(word.patternLettersInGroups.size() - 1)) {
                word.patternLettersInGroups.add(input.charAt(i));
                word.frequencyLettersInGroups.add(1);
            } else {
                word.frequencyLettersInGroups.set(word.frequencyLettersInGroups.size() - 1,
                        word.frequencyLettersInGroups.get(word.frequencyLettersInGroups.size() - 1) + 1);
            }
        }
        return word;
    }
}

class Word {

    /*
    patternLettersInGroups contains single letters representing groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    patternLettersInGroups: a, e, o, e, w, t, w, p
     */
    final List<Character> patternLettersInGroups = new ArrayList<>();

    /*
    frequencyLettersInGroups contains the letter frequency for each groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    frequencyLettersInGroups: 4, 2, 5, 6, 1, 4, 4, 2
     */
    final List<Integer> frequencyLettersInGroups = new ArrayList<>();

}
