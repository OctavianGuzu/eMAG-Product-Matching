package src;

import java.util.ArrayList;

/**
 * Created by vladimir on 09.12.2016.
 */
public class LetterPairSimilarity {
    @SuppressWarnings("rawtypes")
    public static double compareStrings(String str1, String str2) {
        ArrayList pairs1 = wordLetterPairs(str1.toUpperCase());
        ArrayList pairs2 = wordLetterPairs(str2.toUpperCase());
        int intersection = 0;
        int union = pairs1.size() + pairs2.size();
        for (int i=0; i<pairs1.size(); i++) {
            Object pair1=pairs1.get(i);
            for(int j=0; j<pairs2.size(); j++) {
                Object pair2=pairs2.get(j);
                if (pair1.equals(pair2)) {
                    intersection++;
                    pairs2.remove(j);
                    break;
                }
            }
        }
        return (2.0*intersection)/union;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static ArrayList wordLetterPairs(String str) {
        ArrayList allPairs = new ArrayList();
        // Tokenize the string and put the tokens/words into an array
        String[] words = str.split("\\s");
        // For each word
        for (int w=0; w < words.length; w++) {
            // Find the pairs of characters
            String[] pairsInWord = letterPairs(words[w]);
            for (int p=0; p < pairsInWord.length; p++) {
                allPairs.add(pairsInWord[p]);
            }
        }
        return allPairs;
    }

    private static String[] letterPairs(String str) {
        int numPairs=0;
        if(str.length() > 0)
            numPairs=str.length()-1;
        String[] pairs = new String[numPairs];
        for (int i=0; i<numPairs; i++) {
            pairs[i] = str.substring(i,i+2);
        }
        return pairs;
    }

    public static void main(String[] args) {

        System.out.println(compareStrings("Storcator de fructe si legume Philips Viva Collection HR1855/80, 700 W, Recipient suc 0.8 l, "
                        + "Recipient pulpa 1.2 l, 1 Viteza, Tub de alimentare 75 mm, Alb",
                "Storcator de fructe Philips Viva Collection HR1855/80"));

        System.out.println(compareStrings("France",	"Republic of Cuba"));
        System.out.println("");

    }
}
