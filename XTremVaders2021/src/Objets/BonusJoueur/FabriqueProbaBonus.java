package Objets.BonusJoueur;

import Utilities.RangProba;

/**
 * Sert à fabriquer des rangs de probabilité associé à des bonus
 * @author Mathis Poncet
 */
public class FabriqueProbaBonus {
    
    /**
     * Fabrication du rang de probabilité d'un bonus en fonction de celui qui 
     * est entré en paramètre
     * @param bonus à qui on veut attribuer le rang de probabilité
     * @return le rang de probabilité associé à un bonus
     */
    public static RangProba fabriqueRangBonus(TypeBonus bonus){
        RangProba rang = null;
        switch(bonus){
            case AUCUN:
                rang = new RangProba(0.35d, 1d);
                break;
            case MEDECIN:
                rang = new RangProba(0.3d, 0.35d);
                break;
            case SLOW:
                rang = new RangProba(0.15d, 0.30d);
                break;
            case MISSILERAPIDE:
                rang = new RangProba(0.02d, 0.15d);
                break;
            case NUKE:
                rang = new RangProba(0.0d, 0.02d);
                break;
        }
        return rang;
    }
}
