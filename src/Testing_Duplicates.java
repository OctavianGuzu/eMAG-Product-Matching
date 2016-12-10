package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Testing_Duplicates {
    Vector products1 = new Vector();
    Vector products2 = new Vector();
    Vector similitudini = new Vector();

    public Testing_Duplicates(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringTokenizer str;
        String line = in.readLine();
        String first = new String();
        String second = new String();
        while (line != null) {
            str = new StringTokenizer(line, ";");
            line = str.nextToken();
            first = str.nextToken();
            line = str.nextToken();
            second = str.nextToken();
            similitudini.add(Check_Similar.compareTHEM(first, second));
            line = in.readLine();

        }
        //System.out.println(similitudini);
        double sum = 0;
        for (int i = 0; i < similitudini.size(); i++)
            sum += (double) similitudini.get(i);
        System.out.println(sum / similitudini.size());
    }

    public static void main(String args[]) throws IOException {
        Testing_Duplicates obj = new Testing_Duplicates("produse_duplicate.csv");

    }
}
