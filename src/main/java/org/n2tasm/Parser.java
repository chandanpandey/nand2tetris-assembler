package org.n2tasm;

import java.util.HashMap;
/*
 * This class handles the parsing of assembly language instructions.
 * It takes a single line (string) from an assembly file as input
 * and returns a HashMap containing the parsed components of the instruction.
 * For A-instructions, the map will contain the key "aValue" with the numeric value or symbol.
 * For C-instructions, the map will contain keys "dest", "comp", and "jump" with their respective parts.
*/
class Parser{
    
    static HashMap<String, String> parse(String line){
            line = line.strip();
            HashMap<String, String> returnMap = new HashMap<>();
            if(line.startsWith("@")){
                line = line.substring(1);
                returnMap.put("aValue", line);
            }
            else{
                if (line.contains("=") & line.contains(";")){
                    
                    String[] destAndRest = line.split("=");
                    String[] compAndJump = destAndRest[1].split(";");

                    returnMap.put("dest", destAndRest[0].strip());
                    returnMap.put("comp", compAndJump[0].strip());
                    returnMap.put("jump", compAndJump[1].strip());
                }
            else if(line.contains("=")){
                    String[] destAndRest = line.split("=");

                    returnMap.put("dest", destAndRest[0].strip());
                    returnMap.put("comp", destAndRest[1].strip());
                    returnMap.put("jump", null);
            }
            else if(line.contains(";")){
                    String[] destAndRest = line.split(";");

                    returnMap.put("comp", destAndRest[0].strip());
                    returnMap.put("jump", destAndRest[1].strip());
                    returnMap.put("dest", null);
            }
            else{
                // Write exception here
            }
            }
            return returnMap;
    }
}