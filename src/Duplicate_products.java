package src;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Duplicate_products {
    Vector products=new Vector();
    Vector IDs=new Vector();

    public static void main(String args[]) throws IOException {
        Duplicate_products obj=new Duplicate_products();
        obj.readFile("produse_de_adaugat.csv");
    }

    public void readFile(String file) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(file));
        StringTokenizer stringToken;
        String line;
        line=br.readLine();
        while(line != null) {
            stringToken=new StringTokenizer(line,";");
            line=stringToken.nextToken();
            IDs.add(line);
            line=stringToken.nextToken();
            products.add(line);
            line=br.readLine();
        }
        System.out.println(products);
    }
}
