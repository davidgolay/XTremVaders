package Objets.BonusJoueur;

import Jeu.XtremVaders2021;
import iut.Game;

/**
 * Bonus donnant un point de vie au joueur
 * @author Mathis Poncet
 */
public class BonusMedecin extends Bonus {
    /**
     * Point de vie que le joueur va avoir en plus en récoltant le bonus
     * Ne peut pas dépasser la barre de vie max du joueur
     */
    private int pointVieARajoute = 20;
    
    /**
     * Constructeur par initialisation
     * @param g le jeu auquel appartient le bonus
     * @param x abcisse à lequel apparaît le bonus 
     * @param y ordonnée à lequel apparaît le bonus 
     */
    public BonusMedecin(Game g, int x, int y) {
        super(g, "bonus/itemsBonus/bonusMedecin", x, y);
    }

    @Override
    public String getItemType() {
        return "BonusMedecin";
    }
    
    @Override
    public TypeBonus getTypeBonus(){
        return TypeBonus.MEDECIN;
    }
    
    @Override
    public void bouger(long dt){
        moveDA(dt * getVitesse(), -90);
    }
    
    /**
     * Permet de rajouter une vie au joueur lorsqu'il a attrapé le bonus
     * Normalisation si le rajout de vie déppasse la vie maximum que peut 
     * posséder le joueur
     */
    @Override
    public void lancerEffet() {
        System.out.println("effet medecin");
        if(XtremVaders2021.getJoueur().getPtVie() + pointVieARajoute > XtremVaders2021.getJoueur().getMaxPtVie()){
            pointVieARajoute = XtremVaders2021.getJoueur().getMaxPtVie() - XtremVaders2021.getJoueur().getPtVie(); 
        }
        XtremVaders2021.getJoueur().setPtVie(XtremVaders2021.getJoueur().getPtVie() + pointVieARajoute);
    }
    
    /**
     * Le bonus est permanent et n'a pas de fin
     * @param dt l'évolution du bonus au court du temps
     */
    @Override
    public void finEffet(long dt) {
        
    }
}