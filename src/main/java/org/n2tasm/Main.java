package org.n2tasm;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
         
        if(args.length<1 || args.length > 1){
            System.out.print("Please provide the filename with .asm extension as argument.");
        }else{
        
        // Read file 
        String userFile = System.getProperty("user.dir") + "/" + args[0];
        List<String> fileLines = FileIO.readfile(userFile);
        
        //Get Hack Code
        List<String> writeLines = Code.getHackFileLines(fileLines);
        
        //Get Hack Filename and Path
        String filename = args[0];
        int dotIndex = filename.indexOf(".");
        if (dotIndex != -1){
            filename = filename.substring(0, dotIndex) + ".hack";
        }else{
            filename += ".hack";
        }



        filename = System.getProperty("user.dir") + "/" + filename;

        //Write Hack File
        FileIO.writefile(filename, writeLines);        

        }

    }
}