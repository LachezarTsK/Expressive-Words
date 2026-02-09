
function expressiveWords(target: string, words: string[]): number {
    const util = new Util();
    util.targetWord = createWord(target);
    let countExpressiveWords = 0;

    for (let word of words) {
        const wordToStretch = createWord(word);
        if (wordIsStretchy(wordToStretch, util)) {
            ++countExpressiveWords;
        }
    }
    return countExpressiveWords;
};

function wordIsStretchy(word: Word, util: Util): boolean {
    if (word.patternLettersInGroups.length !== util.targetWord.patternLettersInGroups.length) {
        return false;
    }

    for (let i = 0; i < word.patternLettersInGroups.length; ++i) {
        const targetLetter = util.targetWord.patternLettersInGroups[i];
        const targetFrequency = util.targetWord.frequencyLettersInGroups[i];

        const letter = word.patternLettersInGroups[i];
        const frequency = word.frequencyLettersInGroups[i];

        if ((letter !== targetLetter)
            || (frequency > targetFrequency)
            || (frequency !== targetFrequency && targetFrequency < Util.MIN_SIZE_STRETCHY_GROUP)) {
            return false;
        }
    }
    return true;
}

function createWord(input: string): Word {
    const word = new Word();
    word.patternLettersInGroups.push(input.charAt(0));
    word.frequencyLettersInGroups.push(1);

    for (let i = 1; i < input.length; ++i) {
        if (input.charAt(i) !== word.patternLettersInGroups[word.patternLettersInGroups.length - 1]) {
            word.patternLettersInGroups.push(input.charAt(i));
            word.frequencyLettersInGroups.push(1);
        } else {
            ++word.frequencyLettersInGroups[word.frequencyLettersInGroups.length - 1];
        }
    }
    return word;
}

class Util {
    static MIN_SIZE_STRETCHY_GROUP = 3;
    targetWord: Word;
}

class Word {

    /*
     patternLettersInGroups contains single letters representing groups of same letters.
     Example:
     word:   aaaaeeoooooeeeeeewttttwwwwpp
     groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
     patternLettersInGroups: a, e, o, e, w, t, w, p
     */
    patternLettersInGroups: string[] = new Array();

    /*
     frequencyLettersInGroups contains the letter frequency for each groups of same letters.
     Example:
     word:   aaaaeeoooooeeeeeewttttwwwwpp
     groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
     frequencyLettersInGroups: 4, 2, 5, 6, 1, 4, 4, 2
     */
    frequencyLettersInGroups: number[] = new Array();

}
