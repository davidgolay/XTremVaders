package Objets.BonusJoueur;

import iut.Game;

/**
 * Fabrique des bonus
 * @author Mathis Poncet
 */
public class FabriqueBonus{
    
    
    /**
     * Permet de construire un bonus suivant le type du bonus passé en paramètre
     * @param g le jeu auquel appartient le bonus
     * @param bonus Le type du bonus que l'on veut créer
     * @param y où le bonus va apparaître
     * @param x où le bonus va appparaître
     * @return le bonus qui a été créé suivant le type 
     */
    public static Bonus fabriquerUnBonus(Game g, int x, int y, TypeBonus bonus){
        Bonus b = null;
        switch(bonus){
            case AUCUN:
                b = null;
                break;
            case MEDECIN:
                b = new BonusMedecin(g, x, y);
                break;
            case SLOW:
                b = new BonusSlow(g, x, y);
                break;
            case MISSILERAPIDE:
                b = new BonusMissileRapide(g, x, y);
                break;
            case NUKE:
                b = new BonusNuke(g, x, y);
                break;
        }
        return b;
    }
}
