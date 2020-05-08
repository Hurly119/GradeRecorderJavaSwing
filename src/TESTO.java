import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TESTO {
    public static void main(String[] args) throws IOException {
        BufferedReader grades = new BufferedReader(new FileReader("OldGrades.txt"));
        String xD;
        while((xD = grades.readLine())!= null){
            String [] listxD = xD.split(" ");
            for(String x: listxD){
                System.out.println(x);

            }
        }
    }
}
