
using System;
using System.Collections.Generic;

public class Solution
{
    private static readonly int MIN_SIZE_STRETCHY_GROUP = 3;
    private Word? targetWord;

    public int ExpressiveWords(string target, string[] words)
    {
        targetWord = CreateWord(target);
        int countExpressiveWords = 0;

        foreach (string word in words)
        {
            Word wordToStretch = CreateWord(word);
            if (WordIsStretchy(wordToStretch))
            {
                ++countExpressiveWords;
            }
        }
        return countExpressiveWords;
    }

    private bool WordIsStretchy(Word word)
    {
        if (word.PatternLettersInGroups.Count != targetWord!.PatternLettersInGroups.Count)
        {
            return false;
        }

        for (int i = 0; i < word.PatternLettersInGroups.Count; ++i)
        {
            char targetLetter = targetWord.PatternLettersInGroups[i];
            int targetFrequency = targetWord.FrequencyLettersInGroups[i];

            char letter = word.PatternLettersInGroups[i];
            int frequency = word.FrequencyLettersInGroups[i];

            if ((letter != targetLetter)
                    || (frequency > targetFrequency)
                    || (frequency != targetFrequency && targetFrequency < MIN_SIZE_STRETCHY_GROUP))
            {
                return false;
            }
        }
        return true;
    }

    private static Word CreateWord(string input)
    {
        Word word = new();
        word.PatternLettersInGroups.Add(input[0]);
        word.FrequencyLettersInGroups.Add(1);

        for (int i = 1; i < input.Length; ++i)
        {
            if (input[i] != word.PatternLettersInGroups[^1])
            {
                word.PatternLettersInGroups.Add(input[i]);
                word.FrequencyLettersInGroups.Add(1);
            }
            else
            {
                ++word.FrequencyLettersInGroups[^1];
            }
        }
        return word;
    }
}

class Word
{

    /*
    patternLettersInGroups contains single letters representing groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    patternLettersInGroups: a, e, o, e, w, t, w, p
     */
    public readonly List<char> PatternLettersInGroups = [];

    /*
    frequencyLettersInGroups contains the letter frequency for each groups of same letters.
    Example:
    word:   aaaaeeoooooeeeeeewttttwwwwpp
    groups: aaaa | ee | ooooo | eeeeee | w | tttt | wwww | pp
    frequencyLettersInGroups: 4, 2, 5, 6, 1, 4, 4, 2
     */
    public readonly List<int> FrequencyLettersInGroups = [];

}
