import java.util.*;
import java.util.regex.*;
import java.io.*;

class Local {
    Vector<String> s;
    Vector<Integer> ap;
    int numar_cuvinte;
    String categorie;


    public Local() {
        s = new Vector<String>();
        ap = new Vector<Integer>();
        numar_cuvinte = 0;
    }


    public String toString() {
        String result = categorie + " " + numar_cuvinte + " ";
        for (int i = 0; i < s.size(); i++) {
            result += s.elementAt(i) + " " + ap.elementAt(i) + " ";
        }
        return result;
    }
}

class ProductName {
    Vector<Local> global;

    public ProductName() {
        global = new Vector<Local>();
    }

    public static void main(String args[]) {
        ProductName pn = new ProductName();
        pn.read();
        System.out.println(pn.getProductName(args[0]));
        try {
            pn.readInputFile(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
//     pn.readFromDir("product_names_code");
//     System.out.println(pn);
    }

    public Local readFromFile(String file) throws IOException {
//     BufferedReader br = new BufferedReader(new FileReader(file));
        Local l = new Local();
        l.categorie = file.replaceAll("product_names_code/", "");
        Scanner s = new Scanner(new File(file));
        l.numar_cuvinte = s.nextInt();
//     System.out.println(l.numar_cuvinte);
        while (s.hasNext()) {
            l.s.add(s.next());
            if (s.hasNextInt()) ;
            l.ap.add(s.nextInt());
        }
        global.add(l);

//     System.out.println(file + " " + l);
//     System.out.println(global);
        return l;
    }

    public void readFromDir(String dir) {
//     ProductName pn = new ProductName();
        File folder = new File("product_names_code");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
//       System.out.println(listOfFiles[i].getName());
            if (listOfFiles[i].isFile()) {
                try {
                    readFromFile("product_names_code/" + listOfFiles[i].getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
//     System.out.println(global);
//         pn.readFromFile(listOfFiles[i]);
            }
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < global.size(); i++) {
            result += global.elementAt(i).toString() + "\n";
        }
        return result;
    }

    void read() {
        readFromDir("product_names_code");
//     System.out.println(global);
    }

    public String getProductName(String produs) {
        StringTokenizer st = new StringTokenizer(produs, " :;.,'\"+|/\\-_=()[]{}~`");
        String categorie = "Carti";
        double procent = 0;
        double temp = 0;
        int i = 0;
        if (produs.contains(" - "))
            return categorie;


//     System.out.println(global.size());
        while (st.hasMoreElements()) {
            String token = st.nextToken().toLowerCase();
            for (i = 0; i < global.size(); i++) {
//         i = 301;
//               System.out.println(i + " - " + global.elementAt(i).categorie);
                temp = 0;
                int nr_match = 0;
                boolean consecutive = false;
                boolean matched = false;
                for (int j = 0; j < global.elementAt(i).s.size(); j++) {
                    if (token.equals(global.elementAt(i).s.elementAt(j))) {
//         System.out.println(token);
                        temp += ((double) global.elementAt(i).ap.elementAt(j) / global.elementAt(i).numar_cuvinte) * global.elementAt(i).ap.elementAt(j);
//     System.out.println(temp);
                        nr_match++;
                        if (matched) {
                            consecutive = true;
                        }
                        matched = true;
                    }
                    if (consecutive)
                        System.out.println(consecutive);
                    if (consecutive)
                        temp *= 10;
                    if (temp * nr_match * nr_match > procent) {
                        procent = temp;
                        categorie = global.elementAt(i).categorie;
                    }

                }
            }
        }
//     System.out.println(global.elementAt(i).categorie);
        return categorie;
    }

    public void readInputFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));


        StringTokenizer st;
        String line = br.readLine();
        String ID = "";
        String name = "";
        while (line != null) {
            if (line.contains("__")) {
                line = br.readLine();
                continue;
            }
            st = new StringTokenizer(line, ";");
            if (st.hasMoreElements()) {
                ID = st.nextToken();
            }
            if (st.hasMoreElements()) {
                name = st.nextToken();
            }
            System.out.println(ID + ";" + getProductName(name));
            line = br.readLine();
        }
    }
}
//     StringTokenizer st1;
//     String line = br.readLine();
//     while (line != null)
//       st1 = new StringTokenizer(line, "=");
//       Pair p = new Pair();
//       if (st1.hasMoreElements()) {
//         p.s = st1.nextToken();
//       }
//       if (st1.hasMoreElements()) {
//         p.ap = Integer.parceInt(st1.nextToken());
//       }
//       
    

