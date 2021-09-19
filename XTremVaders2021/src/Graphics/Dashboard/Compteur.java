/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Dashboard;

import iut.Game;
import iut.GameItem;

/**
 *
 * @author David
 */
public class Compteur extends GameItem {
    
    private int unite;

    public Compteur(Game g, double _x, double _y, int unite) {
        super(g, "score/0", _x, _y);
        this.unite = unite;
        System.out.println("2859 << 4" + Integer.toBinaryString(2859<<4));
        
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
    }

    @Override
    public String getItemType() {
        return "Compteur";
    }

    @Override
    public void evolve(long l) {
    }
    
    public void affichageCompteur() {
        //this.changeSprite();
    }
    
}
