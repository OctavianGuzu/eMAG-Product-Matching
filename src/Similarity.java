package src;

/**
 * Created by vladimir on 09.12.2016.
 */
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;//

public class Similarity {

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

//    public static void printSimilarity(String s, String t) {
//        System.out.println(String.format(
//                "%.3f is the similarity between \"%s\" and \"%s\"", similarity(s, t), s, t));
//    }

    public static void main(String[] args) {
//        try (BufferedReader br = new BufferedReader(new FileReader("duplicate.csv"))) {
//
//            String sCurrentLine;
//
//            while ((sCurrentLine = br.readLine()) != null) {
//
//                String[] parts = sCurrentLine.split(";");
//                String[] words1 = parts[1].split(" ");
//                System.out.println(parts[1] + " ---> " + parts[3] + " seamana " + (int)(similarity(parts[1], parts[3]) * 100) + "%");
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("aaabb ---> ccaaa" + " seamana " + (int)(similarity("aaabb", "ccaaa") * 100) + "%");
    }

}

