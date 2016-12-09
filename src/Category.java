package src;

import java.io.*;
import java.util.Vector;

/**
 * Created by octavian.guzu on 12/9/2016.
 */
public class Category {
    Vector categoriesRO=new Vector();
    Vector categoriesEN=new Vector();
    Vector productsCategories=new Vector();

    public Category(String ro_in, String en_in) throws IOException {
        BufferedReader in_ro=new BufferedReader(new FileReader(ro_in));
        String line=in_ro.readLine();
        while(line!=null) {
            categoriesRO.add(line);
            line=in_ro.readLine();
        }
        BufferedReader in_en=new BufferedReader(new FileReader(en_in));
        line=in_en.readLine();
        while(line!=null) {
            categoriesEN.add(line);
            line=in_en.readLine();
        }
    }

    public static void main(String args[]) throws IOException {
        Category obj=new Category("categorii_folosite_ro.txt", "categorii_folosite_en.txt");
        obj.find_Category();
    }

    public void find_Category() throws IOException {
        Duplicate_products obj=new Duplicate_products("produse_de_adaugat.csv");
        PrintWriter out = new PrintWriter(new FileWriter("Produse_categorii_mine.csv"));
        for(int i=0;i<obj.products.size();i++) {
            String currentProduct=(String) obj.products.get(i);
            if(currentProduct.contains("__"))
                continue;
            if(currentProduct.contains(" - ")) {
                //productsCategories.add(obj.IDs.get(i) + ";" + currentProduct + ";" + "Carti" + "\n");
                out.println(obj.IDs.get(i) + ";" + currentProduct + ";" + "Carti");
                //out.println(currentProduct + ";" + "Carti");
                continue;
            }
            double max=LetterPairSimilarity.compareStrings(currentProduct,(String) categoriesRO.get(0));
            String currentCategory=(String) categoriesRO.get(0);
            int index=0;
            for(int j=1;j<categoriesRO.size();j++) {
                String iterateCategory=(String) categoriesRO.get(j);
                double check=LetterPairSimilarity.compareStrings(currentProduct, iterateCategory);
                if(check > max) {
                    max=check;
                    currentCategory=iterateCategory;
                    index=j;
                }
            }
            //productsCategories.add(obj.IDs.get(i)+ ";" + currentProduct + ";" + categoriesEN.get(index)+"\n");
            out.println(obj.IDs.get(i)+";"+currentProduct+";"+categoriesEN.get(index));
            //out.println(currentProduct+";"+categoriesEN.get(index));

        }

        //System.out.println(productsCategories);
        out.flush();
        out.close();
    }

}
