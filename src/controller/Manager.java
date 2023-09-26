/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.NormalizeText;
import view.Menu;

/**
 *
 * @author This PC
 */
public class Manager extends Menu<String>{

    private NormalizeText nt= new NormalizeText();
    private List<String> list_line= new ArrayList<>();
    
    public Manager(String title, String[] s){
        super(title, s);
    }
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                nt.readFile("input.txt");
                break;
            case 2:
                list_line= nt.normalizeText();
                break;
            case 3:
                nt.writeFile("output.txt", list_line);
                break;
            case 4:
                System.exit(0);
        }
    }
    
}
