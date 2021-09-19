package Objets.BonusJoueur;

import Jeu.XtremVaders2021;
import Objets.Missiles.TypeMissile;
import iut.Game;
import java.util.Random;

/**
 * Bonus permettant au joueur d'avoir un missile rapide lorsqu'il attrape 
 * ce bonus
 */
public class BonusMissileRapide extends BonusTir {

    public BonusMissileRapide(Game aG, int aX, int aY) {
        super(aG, "bonus/itemsBonus/bonusSpeed", aX, aY);
        Random r = new Random();
        setDureeEffet(r.nextInt(10000)+ 5000); //entre 5s et 15000s d'effet
    }
    
    /**
     * On change le type de missile que possède le joueur
     */
    @Override
    public void lancerEffet() {
        if(!isEnCours()){
            XtremVaders2021.getJoueur().setTypeMissile(TypeMissile.RAPIDE);
            this.changeSprite("transparent");
        }
        setEnCours(true);
    }
    
    /**
     * On réintialise le type de missile du joueur
     * @param dt 
     */
    @Override
    public void finEffet(long dt) {
        setDureeEffet(getDureeEffet() - dt);
        if(getDureeEffet() <= 0){
            setEnCours(false);
            XtremVaders2021.getJoueur().setTypeMissile(TypeMissile.NORMAL);
            getGame().remove(this);
        }
    }

    @Override
    public TypeMissile getTypeBonusTir() {
        return TypeMissile.RAPIDE;
    }

    @Override
    public TypeBonus getTypeBonus() {
        return TypeBonus.MISSILERAPIDE;
    }    
}