/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author This PC
 */
public class NormalizeText {
     private List<String> list;

    public NormalizeText() {
        list= new ArrayList<>();
    }
         
    public String onlyOneSpace(String line){
        line= line.trim();        
        while(line.contains("  ")){
            line= line.replaceAll("  ", " ");
        }
        return line;
    }
    
    public  String oneSpaceAfterCommaDotColon(String line){
        if(line.contains(".")){
            line= line.replace(".", ". ");                    
            line= onlyOneSpace(line);           
        }     
        StringBuffer st= new StringBuffer(line);               
        for (int i = 0; i < line.length(); i++) {
            if((i==line.length()-1) && st.charAt(i)=='.'){
                break;
            }
            if (st.charAt(i) == '.') {                         
                st.setCharAt(i + 2, Character.toUpperCase(st.charAt(i + 2)));
            }
        }                             
        line= st.toString().trim();
        if(line.contains(",")){
            line= line.replaceAll(",", ", ");
        }
        if (line.contains(":")){
            line= line.replaceAll(":", ": ");
        }
        return onlyOneSpace(line);
    }
    
    public String noSpaceInQuotes(String line){                     
        int countQuetes= 0;
        StringBuffer stringBuffer = new StringBuffer(line);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == '"' && countQuetes % 2 == 0) {
                stringBuffer.deleteCharAt(i + 1);
                countQuetes++;
            } else if (stringBuffer.charAt(i) == '"' && countQuetes % 2 == 1
                    && i != 0) {
                stringBuffer.deleteCharAt(i - 1);
                countQuetes++;
            }
        }
        return stringBuffer.toString().trim();
    }
    
    public String firstCharacterUppercase(String line){
        StringBuffer st= new StringBuffer(line);
        st.setCharAt(0, Character.toUpperCase(st.charAt(0)));
        return st.toString().trim();
    }
    
    public String noSpaceWordFrontOf(String line){
        if(line.contains(",")){
            line=line.replaceAll(",", " ,");
        }
        if(line.contains(".")){
            line=line.replace(".", " .");
        }
        line= onlyOneSpace(line);       
        StringBuffer st= new StringBuffer(line);
        for (int i = 0; i < st.length(); i++) {
            if(st.charAt(i)==','){
                st.deleteCharAt(i-1);
            }
            if(st.charAt(i)=='.'){
                st.deleteCharAt(i-1);
            }
            
        }
        return st.toString().trim();
    }
    
    public void readFile(String name){       
        File f= new File(name);
        try {
            BufferedReader br= Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8);
            String line= null;
            while(true){
                line= br.readLine();
                if(line==null){
                    break;
                }
                if(!line.isEmpty()){ 
                    line= line.toLowerCase();                  
                    list.add(line);                   
                }
            }
        } catch (Exception e) {
            System.err.println("Error read file!");
        }
        System.out.println("Read file sucessful!");        
    }
    
    public List<String> normalizeText(){
        List<String> list_text= new ArrayList<>();
        String line;
        for (int i = 0; i < list.size(); i++) {
            line= onlyOneSpace(list.get(i));             
            line= noSpaceWordFrontOf(line);           
            line= oneSpaceAfterCommaDotColon(line);            
            line= noSpaceInQuotes(line);
            line= firstCharacterUppercase(line);           
            if(i==list.size()-1){               
                if(line.charAt(list.size()-1)!='.'){
                    line= line+".";
                }
            }           
            list_text.add(line);
        }
        System.out.println("Normalize Text Done!");
        return list_text;
    }
    
    public void writeFile(String name, List<String> list_line) {      
        File f= new File(name);        
        try {            
            PrintWriter pw= new PrintWriter(f, "UTF-8");
            for (String line : list_line){
                pw.print(line+" ");
            }
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.err.println("Error write file!");
        }
        System.out.println("Write File Sucessful!");
    }      
}
