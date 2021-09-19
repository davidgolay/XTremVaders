package Objets;

import Entites.Joueur;
import Jeu.XtremVaders2021;
import Objets.Missiles.FabriqueMissile;
import Objets.Missiles.Missile;
import Objets.Missiles.TypeMissile;
import Utilities.Sons;
import iut.Game;
import iut.GameItem;

/**
 * Cette classe représente le Canon du joeur
 * qui tire des doubles missiles avec la touche a
 * @author David Golay
 */
public class Canon extends GameItem {
    
    private TypeMissile typeMissile;
    /**
     * boolean qui décrit/défini si le joueur peut tirer ou non
     */
    private boolean canShoot;
    /**
     * utile pour attandre le temps avant de pouvoir tirer à nouveau
     */
    private double tempsAvantTirer;
    
    //joueur rattaché au canon
    private Joueur joueur;

    public Canon(Game g, Joueur joueur) {
        super(g, "canon/canon", joueur.getLeft(), joueur.getTop());
        this.joueur      = joueur;
        this.typeMissile = TypeMissile.MISSILE_CANON;
        this.canShoot    = true;
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
        return "canon";
    }

    @Override
    public void evolve(long l) {
        if(!XtremVaders2021.getJoueur().estVivant()) getGame().remove(this);
        this.tempsAvantTirer -= l;
        
        if(this.tempsAvantTirer <= 0){
            this.canShoot = true;
            this.tempsAvantTirer = 350;
        }
    }
    
    public Canon getCanon(){
        return this;
    }
    
    public void deplacerCanon(double value, double angle){
        moveDA(value, angle);
    }
    
    public void tirer(){      
        if(this.canShoot){
            Sons.play("newSounds/doubleShoot");
            this.canShoot = false;
            tirerCanon();
        }            
    }
    
    /**
     * Cette méthode retourne la position en X 
     * depuis laquelle le canon gauche tire son missile
     */
    private int getCanonGauchePos(){
        return this.getMiddleX()-30;
    }
    
    /**
     * Cette méthode retourne la position en X 
     * depuis laquelle le canon droit tire son missile
     */
    private int getCanonDroitPos(){
        return this.getMiddleX()+12;
    }
    
    /**
     * Cette méthode génère un double tir avec le canon
     */
    private void tirerCanon(){
        //génération d'un double tir avec les canons latéraux    
        Missile missileGauche = 
            FabriqueMissile.fabriquerUnMissile(getGame(), 
                                               getCanonGauchePos(),
                                               getTop(), typeMissile, joueur);
        Missile missileDroit  = 
                FabriqueMissile.fabriquerUnMissile(getGame(), 
                                               getCanonDroitPos(),
                                               getTop(), typeMissile, joueur);
        //AJOUT AU JEU
        getGame().addItem(missileDroit);
        getGame().addItem(missileGauche);
    }
    
}
