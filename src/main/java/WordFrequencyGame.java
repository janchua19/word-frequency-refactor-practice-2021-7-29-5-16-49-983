import java.util.*;

public class WordFrequencyGame {

    public static final String BLANK_SPACES = "\\s+";

    public String getResult(String sentence){

        if (isSingleWord(sentence)) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfos = getWordInfos(sentence);

                wordInfos.sort((currentWord, wordTemp) -> wordTemp.getWordCount() - currentWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : wordInfos) {
                    String s = wordInfo.getValue() + " " +wordInfo.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> getWordInfos(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACES));
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int wordCount = Collections.frequency(words,word);
            wordInfos.add(new WordInfo(word,wordCount));
        }
        return wordInfos;
    }

    private boolean isSingleWord(String sentence) {
        return sentence.split(BLANK_SPACES).length==1;
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }

            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }


}
