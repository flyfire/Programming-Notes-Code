package cn.kotliner.java.io;

import java.io.*;

/**
 * Created by benny on 5/28/17.
 */
public class IO {
    public static void main(String... args) {
        BufferedReader bufferedReader = null;
        try {
             bufferedReader = new BufferedReader(new FileReader(new File("build.gradle")));
            String line;
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
