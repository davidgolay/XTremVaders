package Objets.Missiles.Joueur;


import Entites.Vaisseau;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Missile avec une vitesse plus rapide que les missiles normal
 * @author Mathis Poncet
 */
public class MissileRapide extends MissileJoueur{

    public MissileRapide(Game aG, int aX, int aY, Vaisseau vaisseau) {
        super(aG, "missile/missileLargeBlue", aX, aY, vaisseau);    
        //decallage de l'item
        moveXY(-10, 0);      
    }

    @Override
    public void collideEffect(GameItem gameItem) {
        super.collideEffect(gameItem); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TypeMissile getMissileType() {
        return TypeMissile.RAPIDE;
    }

    @Override
    public void effetExplosion() {

    }

    @Override
    public void deplacement(long dt) {
        this.moveDA(dt * getVitesse(), +90);
    }

    /**
     * Les missiles rapides occasionnent plus de dégât aux débrits
     * @param itemType
     * @return 
     */
    @Override
    public int getDegat(iut.GameItem itemType) {
        int degat = 20;
        if(itemType.getItemType().equals("Debrits")){
            degat = 45;
        }
        return degat;
    }  
}
