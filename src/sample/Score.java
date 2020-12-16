package sample;

import java.io.*;

public class Score {
    public String getScore()  {
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
    public void writeStats(int score){
        int highscore=Integer.parseInt(getScore());
        int totalStar=Integer.parseInt(getStar());
        if(score>highscore)
            highscore=score;
        totalStar+=score;

        try{
            String test = Integer.toString(totalStar)+"\n"+Integer.toString(highscore);
            File file = new File("output.txt");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(test);
            bw.close();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void reset(){
        try{
            String test = "0\n0";
            File file = new File("output.txt");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(test);
            bw.close();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}




