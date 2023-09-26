/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.Manager;
import view.Menu;

/**
 *
 * @author This PC
 */
public class Main {
    public static void main(String[] args) {
        String title= "NORMALIZE TEXT";
        String[] s= new String[] {"Read file","Normalize Text", "Write File", "Exit"};
        Menu<String> menu= new Manager(title, s);
        menu.run();
    }
}
