/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Menus;

import iut.Game;

/**
 *
 * @author David
 */
public class FabriqueMenu {
    
    public static Menu FabriquerUnMenu(Game g, TypeMenu type){
        Menu menu = null;
        switch(type){
            case DEMARRAGE: 
                menu = new MenuDemarrage(g, 0, 0);
                menu.setNbItemMenu(3);
            break;
            case FIN: 
                menu = new MenuFin(g, 0, 0);
                menu.setNbItemMenu(3);
            break;
        }
        return menu;
    }
}
