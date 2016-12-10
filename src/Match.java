package src;

import src.LetterPairSimilarity;

import java.util.*;
import java.io.*;

class Match {
  public static void main(String args[]) {
    Vector<String> v = new Vector<String>();
    Vector<String> ID = new Vector<String>();
    BufferedReader br;
    StringTokenizer st1, st2;
    try {
      br = new BufferedReader(new FileReader("produse_de_adaugat.csv"));
      String line = br.readLine();
      while (line != null) {
        st1 = new StringTokenizer(line, ";");
        if (st1.hasMoreElements())
          ID.add(st1.nextToken());
        if (st1.hasMoreElements())
          v.add(st1.nextToken());
        line = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    String first = "";
    String second = "";
    
    for (int i = 0; i < v.size(); i++) {
//       System.out.println(i);
      String product = v.elementAt(i);
      if (product.contains("__"))
        continue;
      if (product.matches(".*\\d+.*"))
        continue;
      if (product.contains(" - ")) {
        st2 = new StringTokenizer(product, "-");
          if (st2.hasMoreElements())
            first = st2.nextToken();
          if (st2.hasMoreElements()) 
            second = st2.nextToken();
//             if (i % 100 == 0)
//               System.out.println(i);
        for (int j = 0; j < v.size(); j++) {
          if (i != j) {
//             if (first.equals(v.elementAt(j))) {
            if (LetterPairSimilarity.compareStrings(first, v.elementAt(j)) > 0.9) {
              System.out.println(ID.elementAt(i) + ";" + ID.elementAt(j));
            }
//             if (LetterPairSimilarity.compareStrings(second, v.elementAt(j)) > 0.8) {
//               System.out.println(ID.elementAt(i) + ";" + v.elementAt(i) + ";" + ID.elementAt(j) + ";" + v.elementAt(j));
//             }
          }
        }
      }
    }
  }
}
