package org.n2tasm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Code{
        
    static List<String> getHackFileLines(List<String> asmfileLines){
        List<String> hackFileLines = new ArrayList<>();
        
        Map<SymbolTable, List<String>> inputs = Parser.getCodeAndSymbolTable(asmfileLines);

        SymbolTable symbolTable = (SymbolTable) inputs.keySet().iterator().next();

        List<String> fileLines =  inputs.values().iterator().next();

        int[] ramCounter = new int[1];
        ramCounter[0] = 16;


        for (int j = 0; j < fileLines.size(); j++) {
            

            HashMap<String, String> tokens = new HashMap<>();

            tokens = Parser.parse(fileLines.get(j), symbolTable, j, ramCounter);
            if(tokens.isEmpty()){System.out.println("How can it be empty?");}
            else if(tokens.containsKey("aValue")){
                String binaryString = Integer.toBinaryString(Integer.parseInt(tokens.get("aValue")));
                if(binaryString.length()<=15){
                    String preBinaryValues = "";
                    for(int i = 0; i < (16-binaryString.length()); i++){
                        preBinaryValues += "0";
                    }
                    String code = preBinaryValues + binaryString;
                    hackFileLines.add(code);
                }else{
                    System.out.println("Something wrong with @ parser.");
                }
            }
            else{
                String code = "111";
                String dest = "";
                String comp = "";
                String jump = "";

                switch (tokens.get("dest")) {
                    case null:
                        dest= "000";
                        break;
                    case "M":
                        dest= "001";
                        break;
                    case "D":
                        dest= "010";
                        break;
                    case "DM":
                        dest= "011";
                        break;
                    case "MD":
                        dest= "011";
                        break;
                    case "A":
                        dest= "100";
                        break;
                    case "AM":
                        dest= "101";
                        break;
                    case "MA":
                        dest= "101";
                        break;
                    case "AD":
                        dest= "110";
                        break;
                    case "DA":
                        dest= "110";
                        break;
                    case "ADM":
                        dest= "111";
                        break;
                    case "AMD":
                        dest= "111";
                        break; 
                    case "DAM":
                        dest= "111";
                        break;
                    case "DMA":
                        dest= "111";
                        break;
                    case "MDA":
                        dest= "111";
                        break;
                    case "MAD":
                        dest= "111";
                        break;    
                    default:
                    // Enter exception here
                        break;
                }

                switch (tokens.get("jump")) {
                    case null:
                        jump= "000";
                        break;
                    case "JGT":
                        jump= "001";
                        break;
                    case "JEQ":
                        jump= "010";
                        break;
                    case "JGE":
                        jump= "011";
                        break;
                    case "JLT":
                        jump= "100";
                        break;
                    case "JNE":
                        jump= "101";
                        break;
                    case "JLE":
                        jump= "110";
                        break;
                    case "JMP":
                        jump= "111";
                        break;               
                    default:
                    // Enter exception here
                        break;
                }

                switch (tokens.get("comp")) {
                    case null:
                        System.out.println("comp is null");
                        break;
                    case "0":
                        comp = "0101010";
                        break;
                    case "1":
                        comp = "0111111";
                        break;
                    case "-1":
                        comp = "0111010";
                        break;
                    case "D":
                        comp = "0001100";
                        break;
                    case "A":
                        comp = "0110000";
                        break;
                    case "M":
                        comp = "1110000";
                        break;                    
                    case "!D":
                        comp = "0001101";
                        break;
                    case "!A":
                        comp = "0110001";
                        break;
                    case "!M":
                        comp = "1110001";
                        break;
                    case "-D":
                        comp = "0001111";
                        break;
                    case "-A":
                        comp = "0110011";
                        break;
                    case "-M":
                        comp = "1110011";
                        break;
                    case "D+1":
                        comp = "0011111";
                        break;
                    case "1+D":
                        comp = "0011111";
                        break;
                    case "A+1":
                        comp = "0110111";
                        break;
                    case "1+A":
                        comp = "0110111";
                        break;
                    case "M+1":
                        comp = "1110111";
                        break;
                    case "1+M":
                        comp = "1110111";
                        break;
                    case "D-1":
                        comp = "0001110";
                        break;
                    case "1-D":
                        comp = "0001110";
                        break;
                    case "A-1":
                        comp = "0110010";
                        break;
                    case "1-A":
                        comp = "0110010";
                        break;
                    case "M-1":
                        comp = "1110010";
                        break;
                    case "1-M":
                        comp = "1110010";
                        break;
                    case "D+A":
                        comp = "0000010";
                        break;
                    case "A+D":
                        comp = "0000010";
                        break;
                    case "D+M":
                        comp = "1000010";
                        break;
                    case "M+D":
                        comp = "1000010";
                        break;
                    case "D-A":
                        comp = "0010011";
                        break;
                    case "D-M":
                        comp = "1010011";
                        break;
                    case "A-D":
                        comp = "0000111";
                        break;
                    case "M-D":
                        comp = "1000111";
                        break;
                    case "D&A":
                        comp = "0000000";
                        break;
                    case "A&D":
                        comp = "0000000";
                        break;
                    case "D&M":
                        comp = "1000000";
                        break;
                    case "M&D":
                        comp = "1000000";
                        break;
                    case "D|A":
                        comp = "0010101";
                    case "A|D":
                        comp = "0010101";
                    case "D|M":
                        comp = "1010101";
                    case "M|D":
                        comp = "1010101";
                    default:
                        //Write exceptions here
                        break;
                }
            
                hackFileLines.add(code + comp + dest + jump );

            

            }
        
        }

        return hackFileLines;
    }

}