package Objets.Missiles.Joueur;


import Entites.Vaisseau;
import Graphics.SpritesAnimes.ImpactMissile;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Missile par défaut que peut tirer un vaisseau joueur
 * @author Mathis Poncet
 */
public class MissileNormal extends MissileJoueur{

    public MissileNormal(Game aG, int aX, int aY, Vaisseau vaisseau) {
        super(aG, "missile/missile_ball/missile_ball_00001", aX, aY, vaisseau);
        //decallage de l'item
        moveXY(-4, 0);
    }

    @Override
    public void collideEffect(GameItem gameItem) {
        super.collideEffect(gameItem); //To change body of generated methods, choose Tools | Templates.
    }
  
    @Override
    public TypeMissile getMissileType() {
        return TypeMissile.NORMAL;
    }

    @Override
    public void effetExplosion() {
        ImpactMissile impact = new ImpactMissile(getGame(), "missile/missile_impact/missile_impact_", getMiddleX() , getMiddleY() );
        getGame().addItem(impact);
    }

    @Override
    public void deplacement(long dt) {
        if(getVaisseau().getItemType().equals("Joueur")) this.moveDA(dt * getVitesse(), 90);
        else if(getVaisseau().getItemType().equals("Invader")) this.moveDA(dt * getVitesse(), -90);
    }
    
    /**
     * Les missiles normaux ne font pas beaucoup de dégât sur le boss
     * @param itemType
     * @return 
     */
    @Override
    public int getDegat(iut.GameItem itemType) {
        int degat = 20;
        if(itemType.getItemType().equals("Boss")) degat = 3;
        return degat;
    }
}
