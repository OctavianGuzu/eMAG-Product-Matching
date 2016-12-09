package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Refined_Duplicate_products {
    Vector IDs=new Vector();
    Vector products=new Vector();
    Vector afterComma=new Vector();

    public Refined_Duplicate_products(String file) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(file));
        StringTokenizer stringToken, commas;
        String line;
        line=br.readLine();
        while(line!=null) {
            stringToken=new StringTokenizer(line,";");
            line=stringToken.nextToken();
            IDs.add(line);
            line=stringToken.nextToken();
            commas=new StringTokenizer(line, "'");
            line=commas.nextToken();
            while(commas.hasMoreTokens()) {
                afterComma.add(line);
                line=commas.nextToken();
            }
            line=br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        Refined_Duplicate_products obj=new Refined_Duplicate_products("produse_de_adaugat.csv");
        System.out.println(obj.afterComma);
    }
}
