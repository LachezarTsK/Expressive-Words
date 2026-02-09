
#include <vector>
#include <string>
#include <string_view>
using namespace std;

struct Word {

    /*
    patternLettersInGroups contains single letters representing groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    patternLettersInGroups: a, e, o, e, w, t, w, p
     */
    vector<char> patternLettersInGroups;

    /*
    frequencyLettersInGroups contains the letter frequency for each groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    frequencyLettersInGroups: 4, 2, 5, 6, 1, 4, 4, 2
     */
    vector<int> frequencyLettersInGroups;
};

class Solution {

    static const int MIN_SIZE_STRETCHY_GROUP = 3;
    Word targetWord;

public:
    int expressiveWords(string target, vector<string>& words) {
        targetWord = createWord(target);
        int countExpressiveWords = 0;

        for (const auto& word : words) {
            Word wordToStretch = createWord(word);
            if (wordIsStretchy(wordToStretch)) {
                ++countExpressiveWords;
            }
        }
        return countExpressiveWords;
    }

private:
    bool wordIsStretchy(const Word& word) const {
        if (word.patternLettersInGroups.size() != targetWord.patternLettersInGroups.size()) {
            return false;
        }

        for (int i = 0; i < word.patternLettersInGroups.size(); ++i) {
            char targetLetter = targetWord.patternLettersInGroups[i];
            int targetFrequency = targetWord.frequencyLettersInGroups[i];

            char letter = word.patternLettersInGroups[i];
            int frequency = word.frequencyLettersInGroups[i];

            if ((letter != targetLetter)
                || (frequency > targetFrequency)
                || (frequency != targetFrequency && targetFrequency < MIN_SIZE_STRETCHY_GROUP)) {
                return false;
            }
        }
        return true;
    }

    static Word createWord(string_view input) {
        Word word;
        word.patternLettersInGroups.push_back(input[0]);
        word.frequencyLettersInGroups.push_back(1);

        for (int i = 1; i < input.length(); ++i) {
            if (input[i] != word.patternLettersInGroups[word.patternLettersInGroups.size() - 1]) {
                word.patternLettersInGroups.push_back(input[i]);
                word.frequencyLettersInGroups.push_back(1);
            }
            else {
                ++word.frequencyLettersInGroups[word.frequencyLettersInGroups.size() - 1];
            }
        }
        return word;
    }
};
