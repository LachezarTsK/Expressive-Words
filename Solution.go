
package main

const MIN_SIZE_STRETCHY_GROUP = 3
var targetWord Word

func expressiveWords(target string, words []string) int {
    targetWord = createWord(target)
    countExpressiveWords := 0

    for _, word := range words {
        wordToStretch := createWord(word)
        if wordIsStretchy(wordToStretch) {
            countExpressiveWords++
        }
    }
    return countExpressiveWords
}

func wordIsStretchy(word Word) bool {
    if len(word.patternLettersInGroups) != len(targetWord.patternLettersInGroups) {
        return false
    }

    for i := range word.patternLettersInGroups {
        targetLetter := targetWord.patternLettersInGroups[i]
        targetFrequency := targetWord.frequencyLettersInGroups[i]

        letter := word.patternLettersInGroups[i]
        frequency := word.frequencyLettersInGroups[i]

        if (letter != targetLetter) ||
            (frequency > targetFrequency) ||
            (frequency != targetFrequency && targetFrequency < MIN_SIZE_STRETCHY_GROUP) {
            return false
        }
    }
    return true
}

func createWord(input string) Word {
    word := Word{}
    word.patternLettersInGroups = append(word.patternLettersInGroups, input[0])
    word.frequencyLettersInGroups = append(word.frequencyLettersInGroups, 1)

    for i := 1; i < len(input); i++ {
        if input[i] != word.patternLettersInGroups[len(word.patternLettersInGroups) - 1] {
            word.patternLettersInGroups = append(word.patternLettersInGroups, input[i])
            word.frequencyLettersInGroups = append(word.frequencyLettersInGroups, 1)
        } else {
            word.frequencyLettersInGroups[len(word.frequencyLettersInGroups) - 1]++
        }
    }
    return word
}

type Word struct {

    /*
    patternLettersInGroups contains single letters representing groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    patternLettersInGroups: a, e, o, e, w, t, w, p
    */
    patternLettersInGroups []byte

    /*
    frequencyLettersInGroups contains the letter frequency for each groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    frequencyLettersInGroups: 4, 2, 5, 6, 1, 4, 4, 2
    */
    frequencyLettersInGroups []int
}
