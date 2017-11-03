package morozkr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

public class MorozKR {

    public static void main(String[] args) {
        String res = "";
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");                
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try(FileReader reader = new FileReader(file))
            {
                Scanner scan = new Scanner(reader);
                Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
                while(scan.hasNext()!=false)
                {
                    Matcher matcher = pattern.matcher(scan.nextLine());
                    if(matcher.find()) {
                        res += matcher.group() + " ";
                    }
                }
                System.out.println(res);
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        JFileChooser fileclose = new JFileChooser();  
        if (fileclose.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {  
            try {
                PrintWriter out = new PrintWriter(fileclose.getSelectedFile());
                String[] arr = res.split(" ");
                for(int i = 0; i < arr.length; i++)
                    out.print(arr[i] + "\r\n");
                out.close();
            }
            catch (Exception e) {
                System.out.println("Что-то пошло не так...");
            }  
        }    
    }
}
