import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACES = "\\s+";

    public String getResult(String sentence){

        if (isSingleWord(sentence)) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfos = getWordInfos(sentence);

                wordInfos.sort((currentWord, wordTemp) -> wordTemp.getWordCount() - currentWord.getWordCount());

                return getExpectedResult(wordInfos);
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private String getExpectedResult(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfos
                .stream()
                .map(wordInfo -> wordInfo.getValue() + " " + wordInfo.getWordCount())
                .forEach(joiner::add);
        return joiner.toString();
    }

    private List<WordInfo> getWordInfos(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACES));
        List<WordInfo> wordInfos = new ArrayList<>();
        words
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((word,wordCount) -> wordInfos.add(new WordInfo(word, Math.toIntExact(wordCount))));
        return wordInfos;
    }

    private boolean isSingleWord(String sentence) {
        return sentence.split(BLANK_SPACES).length==1;
    }
}
