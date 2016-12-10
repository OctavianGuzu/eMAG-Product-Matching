package src;

import java.util.StringTokenizer;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Check_Similar {
    public static double compareTHEM(String s1, String s2) {
        StringTokenizer tok1 = new StringTokenizer(s1);
        StringTokenizer tok2 = new StringTokenizer(s2);
        String word1 = new String();
        String word2 = new String();
        int ok = 1;
        word1 = tok1.nextToken();
        word2 = tok2.nextToken();
        for (int i = 0; i < 3 && tok1.hasMoreTokens() && tok2.hasMoreTokens(); i++) {
            if (!word1.equals(word2)) {
                ok = 0;
                break;
            }
            word1 = tok1.nextToken();
            word2 = tok2.nextToken();
        }
        if (ok == 1)
            return 0.9;
        return 0.1;
    }

    public static boolean noWords(String s1) {
        StringTokenizer str = new StringTokenizer(s1);
        return str.countTokens() >= 3;
    }
}
