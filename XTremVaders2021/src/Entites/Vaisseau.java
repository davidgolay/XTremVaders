package Entites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Jeu.XtremVaders2021;
import Objets.Missiles.Missile;
import iut.Game;
import iut.GameItem;
import iut.BoxGameItem;

/**
 * Vaisseau du jeu
 */
public abstract class Vaisseau extends BoxGameItem {
    
    //La vitesse à laquelle le vaisseau va se déplacer
    private double vitesse = 0.15;

    // Le nombre de vie que possède un vaisseau
    private int ptVie;
    
    private Missile missile;
    
    /**
     * Initialise la balle
     * @param aG le jeu
     * @param sprite
     * @param aX la position de dÃ©part
     */
    public Vaisseau(Game aG, String sprite,int aX, int aY, double vitesse) {
        super(aG, sprite, aX, aY);
        this.vitesse = vitesse;
    }

    public void collideEffect(GameItem aO) {
        
    }

    public void evolve(long aDt) {
        if(!XtremVaders2021.getJoueur().estVivant() && !this.getItemType().equals("Joueur")) getGame().remove(this);
    }

    public String getItemType() {
        return "Vaisseau";
    }

    /**
     * Le vaisseau tire un missile qu'il possède
     */
    public abstract void tirer();

    /**
     * Methode qui défini la vitesse du vaisseau
     * @param aVitesse 
     */
    public void setVitesse(double aVitesse) {
        vitesse = aVitesse;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setPtVie(int aPtVie) {
        this.ptVie = aPtVie;
    }

    public int getPtVie() {
        return this.ptVie;
    }
    
    /**
     * Méthode qui determine si le vaisseau a encore des points de vie
     * @return true si le vaisseau est vivant
     */
    public boolean estVivant() {
        boolean estVivant = true;
        if(ptVie <= 0) {
            estVivant = false;
        }
        return estVivant;
    }  
    
    /**
     * Methode utile pour reset la game en supprimant tout les vaisseaux
     * sauf le vaisseau du joueur
     */
    @Deprecated
    public void deleteVaisseau(){
        //si le vaisseau n'ets pas le vaisseau du Joueur on le supprime
        if(this.getItemType() != "Joueur"){
            getGame().remove(this);
        }        
    } 
}