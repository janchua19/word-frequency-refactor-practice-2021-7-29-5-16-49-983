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

                List<Input> wordInfos = new ArrayList<>();
                for (String s : words) {
                    Input input = new Input(s, 1);
                    wordInfos.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map =getListMap(wordInfos);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()){
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfos = list;

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : wordInfos) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
