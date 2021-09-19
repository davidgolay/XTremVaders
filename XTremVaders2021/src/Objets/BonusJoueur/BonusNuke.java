package Objets.BonusJoueur;

import Jeu.XtremVaders2021;
import Objets.Missiles.TypeMissile;
import iut.Game;
import java.util.Random;

/**
 * Bonus permettant au joueur d'avoir un bonus nuke quand il l'attrappe
 * @author Mathis Poncet
 */
public class BonusNuke extends BonusTir{

    public BonusNuke(Game aG, int x, int y) {
        super(aG, "bonus/itemsBonus/bonusNuke", x, y);
        Random r = new Random();
        setDureeEffet(r.nextInt((10000)+ 5000)); //entre 5s et 15000s d'effet
    }
    
    @Override
    public void lancerEffet() {
        if(!isEnCours()){
            XtremVaders2021.getJoueur().setTypeMissile(TypeMissile.NUKE);
            this.changeSprite("transparent");
        }
        setEnCours(true);
    }

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
    public TypeBonus getTypeBonus() {
        return TypeBonus.NUKE;
    }

    @Override
    public TypeMissile getTypeBonusTir() {
        return TypeMissile.NUKE;
    }
    
}
