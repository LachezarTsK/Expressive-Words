
class Solution {

    private companion object {
        const val MIN_SIZE_STRETCHY_GROUP = 3
    }

    private lateinit var targetWord: Word

    fun expressiveWords(target: String, words: Array<String>): Int {
        targetWord = createWord(target)
        var countExpressiveWords = 0

        for (word in words) {
            val wordToStretch = createWord(word)
            if (wordIsStretchy(wordToStretch)) {
                ++countExpressiveWords
            }
        }
        return countExpressiveWords
    }

    private fun wordIsStretchy(word: Word): Boolean {
        if (word.patternLettersInGroups.size != targetWord.patternLettersInGroups.size) {
            return false
        }

        for (i in word.patternLettersInGroups.indices) {
            val targetLetter = targetWord.patternLettersInGroups[i]
            val targetFrequency = targetWord.frequencyLettersInGroups[i]

            val letter = word.patternLettersInGroups[i]
            val frequency = word.frequencyLettersInGroups[i]

            if ((letter != targetLetter)
                || (frequency > targetFrequency)
                || (frequency != targetFrequency && targetFrequency < MIN_SIZE_STRETCHY_GROUP)) {
                return false
            }
        }
        return true
    }

    private fun createWord(input: String): Word {
        val word = Word()
        word.patternLettersInGroups.add(input[0])
        word.frequencyLettersInGroups.add(1)

        for (i in 1..<input.length) {
            if (input[i] != word.patternLettersInGroups[word.patternLettersInGroups.size - 1]) {
                word.patternLettersInGroups.add(input[i])
                word.frequencyLettersInGroups.add(1)
            } else {
                ++word.frequencyLettersInGroups[word.frequencyLettersInGroups.size - 1]
            }
        }
        return word
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
    val patternLettersInGroups = mutableListOf<Char>()

    /*
    frequencyLettersInGroups contains the letter frequency for each groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    frequencyLettersInGroups: 4, 2, 5, 6, 1, 4, 4, 2
     */
    val frequencyLettersInGroups = mutableListOf<Int>()
}
