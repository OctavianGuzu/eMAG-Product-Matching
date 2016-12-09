package src;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Refined_Find_Duplicates {
    Vector duplicates=new Vector();

    public static void main(String[] args) throws IOException {
        Refined_Find_Duplicates obj=new Refined_Find_Duplicates();
        obj.find_Duplicates();
    }

    public void find_Duplicates() throws IOException {
        Duplicate_products obj=new Duplicate_products("produse_de_adaugat.csv");
        PrintWriter finalOutput = new PrintWriter(new FileWriter("produse_duplicate_de_adaugat.csv"));
        String check=new String();
        String buildEntry=new String();
        String fileLine=new String();
        StringTokenizer firstProduct, secondProduct;
        Vector commas1=new Vector();
        String productName1=new String();
        Vector commas2=new Vector();
        String productName2=new String();
        int ok=0;
        for(int i=0;i<obj.products.size()/8;i++) {
            ok=0;
            commas1=new Vector();
            check=(String) obj.products.get(i);
            buildEntry=obj.IDs.get(i) + ";" + check + " -- ";
            fileLine=(String) obj.IDs.get(i);
            firstProduct=new StringTokenizer(check,",");
            productName1=firstProduct.nextToken();
            while(firstProduct.hasMoreTokens()) {
                commas1.add(firstProduct.nextToken());
            }
            //System.out.println(commas);

            for(int j=i;j<obj.products.size()/8;j++) {
                commas2=new Vector();
                check=(String) obj.products.get(j);
                secondProduct=new StringTokenizer(check, ",");
                productName2=secondProduct.nextToken();
                while(secondProduct.hasMoreTokens()) {
                    commas2.add(secondProduct.nextToken());
                }
                if(i!=j) {
                    if(LetterPairSimilarity.compareStrings(productName1, productName2) > 0.64) {
                        if(productName1.contains("__") || productName2.contains("__"))
                            continue;
                        if(commas1.size()==commas2.size()) {
                            if(commas1.size()==2) {
                                if (!commas1.get(0).equals(commas2.get(0)))
                                    continue;
                                if(!commas1.get(1).equals(commas2.get(1)))
                                    continue;
                                if(productName1.toLowerCase().contains("barbati") || productName2.toLowerCase().contains("barbati"))
                                    continue;
                                buildEntry=buildEntry+obj.IDs.get(j) + ";" + check + " -- ";
                                fileLine= fileLine +", " + obj.IDs.get(j);
                                ok=1;
                                continue;
                            }
                        }
                        if(commas1.toString().toLowerCase().contains("gb") || commas2.toString().toLowerCase().contains("gb"))
                            continue;
                        buildEntry=buildEntry+obj.IDs.get(j) + ";" + check + " -- ";
                        fileLine= fileLine +", " + obj.IDs.get(j);
                        ok=1;
                    }
                }
            }
            System.out.println(i+ "\n");
            if(ok==1) {
                duplicates.add(buildEntry+"/////"+"\n");
                finalOutput.println(fileLine);
            }
        }

       // System.out.println(duplicates);

        PrintWriter out = new PrintWriter(new FileWriter("Duplicates_output_Task1.txt"));
        out.println(duplicates);
        out.flush();
        out.close();
        finalOutput.close();
    }
}
