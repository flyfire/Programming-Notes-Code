package ztiany.chapter2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <br/>    功能描述：
 * <br/>    Email     : ztiany3@gmail.com
 *
 * @author Ztiany
 * @see
 * @since 1.0
 */
public class JavaCodeWithProcess {

    public static void main(String... args) {
        printGroovy();
        getProcessText();
        System.out.println(new File("").getAbsolutePath());
    }

    private static void printGroovy() {
        for (int i = 0; i < 3; i++) {
            System.out.print("ho ");
        }
        System.out.println("merry groovy");
    }

    //process 非常有用，可以与系统级进程进行交互，但是使用起来却大费周章
    private static void getProcessText() {
        BufferedReader br = null;
        try {
            Process proc = Runtime.getRuntime().exec("git help");
             br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = br.readLine() )!= null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
