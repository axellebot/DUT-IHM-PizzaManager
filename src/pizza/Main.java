package pizza;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.UIManager;

/**
 *
 * @author axel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex){
            
        }
        
        Fenetre fen = new Fenetre();

        fen.setVisible(true);
        // fen.setSize(600, 320);
    }

}
