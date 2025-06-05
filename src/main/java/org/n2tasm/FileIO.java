package org.n2tasm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class FileIO{
    
    // read file with given path and return an array containing the lines of file, the method will return 
    // an empty array if the file is empty and if path is incorrect, however in case path is incorrect it will display error that path is incorrect 

    public static List<String> readfile(String path){
        var filepath = getpathof(path);
        if(filepath != null){
            List<String> readfileLines;
            try{
                readfileLines = Files.readAllLines(filepath);
                return readfileLines;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                return new ArrayList<>();           
            }
        }else{
            return new ArrayList<>();
        }
    }
    
    public static boolean writefile(String path, List<String> writeFileLines){
        var filePath = getpathof(path);
        if(filePath == null){
            return false;
        }
        try{
            Files.write(filePath, writeFileLines);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Path getpathof(String path){
        try{
            var filePath = Path.of(path);
            return filePath;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}