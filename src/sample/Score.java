package sample;

import java.io.*;

public class Score {
    public  String getScore()  {
        int lineno=1;
        String score=null;
        File file = new File("output.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer;

        try{
            while ((buffer=br.readLine())!=null) {

                if(lineno==2)
                    score=buffer;
                lineno++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }
    public String getStar()  {
        int  lineno=1;
        String total_stars = null;
        File file = new File("output.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String buffer;

        try{
            while ((buffer=br.readLine())!=null) {
//                System.out.println(buffer);
                if(lineno==1)
                    total_stars=buffer;

                lineno++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total_stars;
    }

}




