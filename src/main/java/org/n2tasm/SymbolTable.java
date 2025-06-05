package org.n2tasm;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    public Map<String, String> symbolTable;

    public SymbolTable(){
            
        this.symbolTable = new HashMap<>();

        //Predefined Symbols
        this.symbolTable.put("R0", "0");
        this.symbolTable.put("R1", "1");
        this.symbolTable.put("R2", "2");
        this.symbolTable.put("R3", "3");
        this.symbolTable.put("R4", "4");
        this.symbolTable.put("R5", "5");
        this.symbolTable.put("R6", "6");
        this.symbolTable.put("R7", "7");
        this.symbolTable.put("R8", "8");
        this.symbolTable.put("R9", "9");
        this.symbolTable.put("R10", "10");
        this.symbolTable.put("R11", "11");
        this.symbolTable.put("R12", "12");
        this.symbolTable.put("R13", "13");
        this.symbolTable.put("R14", "14");
        this.symbolTable.put("R15", "15");
        this.symbolTable.put("SP", "0");
        this.symbolTable.put("LCL", "1");
        this.symbolTable.put("ARG", "2");
        this.symbolTable.put("THIS", "3");
        this.symbolTable.put("THAT", "4");
        this.symbolTable.put("SCREEN", "16384");
        this.symbolTable.put("KBD", "24576");
    }


    public void add(String symbol, int instructionNo){
        symbolTable.put(symbol, String.valueOf(instructionNo));
    }

    public boolean contains(String line) {
        return symbolTable.containsKey(line);
    }

    public String get(String line) {
        return symbolTable.get(line);
    }
    
}
