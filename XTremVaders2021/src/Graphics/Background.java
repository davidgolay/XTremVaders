package Graphics;


import Jeu.XtremVaders2021;
import Objets.Debris.Asteroid;
import Objets.Debris.Debris;
import Objets.Debris.FabriqueDebris;
import Objets.Debris.TypeDebris;
import iut.Game;
import iut.GameItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lr468444
 */
public class Background extends GameItem{
    private long lifeSpend;
    private long vitesseCiel;
    private int frequenceAsteroid;
    
    public Background(Game g) {
        super(g, "background/background_1", 0, 0);
        this.lifeSpend = 0;
        this.vitesseCiel = 15;
        this.frequenceAsteroid = 300;
        moveXY(0, -getGame().getHeight());
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
        return "background";
    }

    @Override
    public void evolve(long l) {
        if(XtremVaders2021.getJoueur().getPtVie() <= 20){
            frequenceAsteroid = 500;
        }
        
        lifeSpend += 1;
        if(lifeSpend%frequenceAsteroid == 0){
            Debris debris = FabriqueDebris.fabriquerUnDebris(getGame(), 0, 0, TypeDebris.ASTEROID);
            getGame().addItem(debris);
        }
        this.moveXY(0, l*vitesseCiel/100);
        if(this.getTop()>0){
            moveXY(0, -this.getHeight()/2);
        }
    }   
}
