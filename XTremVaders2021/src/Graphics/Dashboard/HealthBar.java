/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Dashboard;

import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author David
 */
public class HealthBar extends GameItem{
    
    private ItemAnime healthBar;
   
    private float ratioFramePV;

    public HealthBar(Game g, double _x, double _y) {
        super(g, "transparent", _x, _y);        
        this.ratioFramePV = 25.0f/XtremVaders2021.getJoueur().getMaxPtVie();
        this.healthBar = new ItemAnime(getGame(), "dashboard/barreVie/barreVie_00000", 0, 0, TypeAnimation.BARRE_VIE, this);
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
        return "interfaceJoueur";
    }

    @Override
    public void evolve(long l) {
        normaliseBarre();
    }
    
    public void normaliseBarre(){
        int nbPtVie = XtremVaders2021.getJoueur().getPtVie();
        int sprite = Math.round(ratioFramePV*nbPtVie);
//        System.out.println("RATIO: " + ratioFramePV);
//        System.out.println("POINT DE VIE: " + nbPtVie);
//        System.out.println("SPRITE VIE: " + sprite);
        this.changeSprite(healthBar.getAnimation().getSprite(sprite));
        if(sprite <= 0) this.changeSprite(healthBar.getAnimation().getSprite(0));
    }
}
