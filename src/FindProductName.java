import java.util.*;
import java.io.*;
import java.util.regex.*;

// class Pair {
//   String s;
//   int i;
//   
//   public Pair() {
//     s = new String();
//   }
// 
//   public String toString() {
//     return s + "=" + i;
//   }
// }

class FindProductName {
  Vector<String> products;
  int product_number;
//   HashMap<String, Integer> cuvinte;
//   Vector<Pair> cuvinte;
  Vector<String> s;
  Vector<Integer> i;
//   int indice; //cate cuvinte ne intereseata din fiecare categorie


  public FindProductName() {
    products = new Vector<String>();
//     cuvinte = new Vector<Pair>();
    s = new Vector<String>();
    i = new Vector<Integer>();
    product_number = 0;
//     indice = 0;
  }

  public void readFromFile(String file) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader(file));
    StringTokenizer st1, st2;
    String line = br.readLine();
    product_number = 0;
    products = new Vector<String>();
    while (line != null) {
      st1 = new StringTokenizer(line, "#");
      if (st1.hasMoreTokens()) {
        products.add(st1.nextToken());
        product_number++;
      }
      if (st1.hasMoreTokens()) {
        product_number++;
        products.add(st1.nextToken());
      }
      line = br.readLine();
//       System.out.println(product_number);
    }
//     System.out.println(products);
  }

  public void readCuvinte() {
    StringTokenizer st;
    for (int i = 0; i < products.size(); i++) {
      st = new StringTokenizer(products.elementAt(i));
      while (st.hasMoreElements()) {
        String token = st.nextToken().toLowerCase();
        if (token.length() > 2 && !token.matches("[0-9-.]+[a-zA-Z]{0,2}") && !token.equals("pentru") && !token.equals("and") && !token.equals("the")) {
//           product_number++;
//           System.out.println(product_number);
          if (!s.contains(token)) {
            s.add(token);
            this.i.add(new Integer(1));
  //           cuvinte.put(token, 1);
          } else {
            int old = this.i.elementAt(s.indexOf(token));
            this.i.set(s.indexOf(token), old + 1);
          }
        }
//           cuvinte.put(token, (Integer) (cuvinte.get(token) + 1));
        
        }
      }
    }

    public void sortCuvinte() { //sortam cuvintele dupa numarul de aparitii
      String aux_s;
      Integer aux_i;
//       indice = 0;
//       System.out.println(indice);

      int j = 0;
//       System.out.println(this.i);
      boolean gata = false;
      while (!gata) {
        gata = true;
        for (int i = 0; i < s.size() - 1; i++) {
          if (this.i.elementAt(i) < this.i.elementAt(i+1)) {
            aux_s = s.elementAt(i);
            aux_i = this.i.elementAt(i);
            s.set(i, this.s.elementAt(i + 1));
            this.i.set(i, this.i.elementAt(i + 1));
            s.set(i+1, aux_s);
            this.i.set(i+1, aux_i);
            gata = false;
          }
        }
        j++;
      }
//       System.out.println(this.i);
//       System.out.println(s.elementAt(1));
    }
          
      

//     System.out.println(cuvinte);
//     System.out.println(product_number);
        
  public String toString() {
    String result = "" + product_number + "\n";
    int indice = 0;
    if (product_number < 10) {
      indice = product_number;
    } else if (product_number < 50) {
      indice = 10;
    } else if (product_number < 200) {
      indice = 4;
    } else if (product_number < 1000) {
      indice = 5;
    } else {
      indice = 6;
    }
    indice = 0;
    for (int i = 0; i < s.size(); i++) {
      if (product_number / this.i.elementAt(i) < 10) {
        indice++;
      }
      else break;
    }
    for (int i = 0; i < indice; i++) {
      result += s.elementAt(i) + " " + this.i.elementAt(i) + "\n";
    }
    return result;
  }

  public void readAndWrite(String source, String destination) {
    FindProductName fpn = new FindProductName();
    try {
      fpn.readFromFile(source);
    } catch (IOException e) {
      e.printStackTrace();
    }
    readCuvinte();
//     System.out.println(fpn);
    try {
      PrintWriter writer = new PrintWriter(destination);
      fpn.readCuvinte();
      fpn.sortCuvinte();
//       System.out.println(indice);
//       writer.println(product_number);
      writer.println(fpn);
//       System.out.println(fpn);
      writer.close();
//       Process p = Runtime.getRuntime().exec("sort -k 2 -n -t '=' -r " + destination);
//       final Runtime rt = Runtime.getRuntime();
//       System.out.println("sort -k 2 -n -t '=' -r " + destination);
//       rt.exec("sort -k 2 -n -t '=' -r" + destination);
//     destination = destination.replaceAll(" ", "\\\\ ");
//     System.out.println(destination);
//     Process p = Runtime.getRuntime().exec("wc -l " + destination);
//     BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//     String s = stdInput.readLine();
//     System.out.println(s);
//     Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "wc -l " + destination });
//     BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//     String s = stdInput.readLine();
//     System.out.println(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void readFromDirectory() { //throws IOException {
    FindProductName fpn = new FindProductName();
    File folder = new File("product_names");
    File[] listOfFiles = folder.listFiles();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
//         try {
//         System.out.println("File " + listOfFiles[i].getName());
//           readFromFile(listOfFiles[i].getName());
          readAndWrite("product_names/" + listOfFiles[i].getName(), "product_names_code/" + listOfFiles[i].getName());
//           System.out.println("product_names/" + listOfFiles[i].getName());
//         } catch (IOException e) {
//           e.printStackTrace();
//         }
//         System.out.println(fpn);
      }
    }
  }

  public static void main(String args[]) {
    FindProductName fpn = new FindProductName();
//     fpn.readAndWrite(args[0], args[1]);
    fpn.readFromDirectory();
//     try {
//       fpn.readFromFile(args[0]);
//     } catch (IOException e) {
//       e.printStackTrace();
//     }
//     fpn.readCuvinte();
//     System.out.println(fpn);
//     System.out.println(fpn.products);
//     fpn.readFromFile(args[0]);
    //salut
  }
}
