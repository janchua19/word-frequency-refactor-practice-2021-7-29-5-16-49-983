import java.util.*;

public class WordFrequencyGame {

    public static final String BLANK_SPACES = "\\s+";

    public String getResult(String inputStr){

        if (inputStr.split(BLANK_SPACES).length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(BLANK_SPACES);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo input = new WordInfo(word, 1);
                    wordInfos.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(wordInfos);

                List<WordInfo> uniqueWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    uniqueWordInfos.add(wordInfo);
                }
                wordInfos = uniqueWordInfos;

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : wordInfos) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
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
