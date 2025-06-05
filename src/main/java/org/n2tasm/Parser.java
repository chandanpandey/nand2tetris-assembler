package org.n2tasm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * This class handles the parsing of assembly language instructions.
 * It takes a single line (string) from an assembly file as input
 * and returns a HashMap containing the parsed components of the instruction.
 * For A-instructions, the map will contain the key "aValue" with the numeric value or symbol.
 * For C-instructions, the map will contain keys "dest", "comp", and "jump" with their respective parts.
*/
class Parser{
    
    static Map<SymbolTable, List<String>> getCodeAndSymbolTable(List<String> fileLines){
        SymbolTable symbolTable = new SymbolTable();
        List<String> code = new ArrayList<>(); 
        for (String line : fileLines) {
            line = line.strip();
            if(line==""){}
            else if(line.startsWith("//")){}
            else if(line.startsWith("(") && line.endsWith(")")){
                line = line.substring(1, line.length()-1);
                symbolTable.add(line, code.size());
            }else{
                code.add(line);
            }
        }
        return new HashMap<SymbolTable, List<String>>(){{put(symbolTable, code);}};
    }
       
    static HashMap<String, String> parse(String line, SymbolTable table, int instructionNo, int[] ramCounter){
            line = line.strip();
            HashMap<String, String> returnMap = new HashMap<>(); 

            if(line.startsWith("@")){
                line = line.substring(1);
                
                try{
                    Integer.parseInt(line);
                    returnMap.put("aValue", line);
                }
                catch(Exception e){
                    if(table.contains(line)){
                        returnMap.put("aValue", table.get(line));
                    }else{
                        table.add(line, ramCounter[0]);
                        returnMap.put("aValue", String.valueOf(ramCounter[0]));
                        ramCounter[0]++; 
                    }
                }
            }
            else{

                if (line.contains("=") && line.contains(";")){
                    
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
                    System.out.println(line);
                }
            }
        
            return returnMap;
            
        
        }
}