package src;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Find_Duplicates { // DEPRECATED
    Vector duplicates = new Vector();
    Vector reviewed_duplicates = new Vector();

    public static void main(String args[]) throws IOException {
        //System.out.println(Similarity.similarity("Ana are mere","Anna arrre pere")*100);
        Find_Duplicates obj = new Find_Duplicates();
        obj.find_Duplicates();
    }

    public void find_Duplicates() throws IOException {
        Duplicate_products obj = new Duplicate_products("produse_de_adaugat.csv");
        String check = new String();
        String buildEntry = new String();
        int ok;
        for (int i = 0; i < obj.products.size() / 8; i++) {
            check = (String) obj.products.get(i);
            buildEntry = obj.IDs.get(i) + ";" + check + " -- ";
            ok = 0;
            for (int j = i; j < obj.products.size() / 8; j++) {
                if (i != j && LetterPairSimilarity.compareStrings(check, (String) obj.products.get(j)) > 0.6) {
                    buildEntry = buildEntry + obj.IDs.get(j) + ";" + obj.products.get(j) + " -- ";
                    ok = 1;
                }
            }
            System.out.println(i + "\n");
            if (ok == 1)
                duplicates.add(buildEntry + "\n");
        }
        for (int i = 0; i < duplicates.size(); i++) {
            if (((String) duplicates.get(i)).contains("XL")) {
                duplicates.remove(i);
            }
        }

        System.out.println(duplicates);
    }
}
