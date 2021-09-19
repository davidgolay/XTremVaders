package Objets.BonusJoueur;

import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author David
 */
public class BonusUnlocked extends GameItem {
    
    //Utile pour réaliser l'animation d'activation du bonus
    private ItemAnime itemAnime;
    
    public BonusUnlocked(Game g, int _x, int _y) {
        super(g, "bonus/bonus_unlock_00000", _x, _y);
        this.itemAnime = new ItemAnime(g, "bonus/bonus_unlock_00000", _x, _y, TypeAnimation.BONUS_UNLOCK, this);
        moveXY(itemAnime.centerSpriteX(), 0);
        //angle aléatoir de l'animation
        this.setAngle(Utilities.Utilite.randomBetweenRange(0, 360));
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
        return "bonusUnlock";
    }

    @Override
    public void evolve(long dt) {
        //on joue l'animation de debloquage
        itemAnime.playAnimation(dt);
        // l'animation de bonus reste accrocher au joueur
        int deltaPosX = XtremVaders2021.getJoueur().getMiddleX() - this.getMiddleX();
        moveXY(deltaPosX, 0);
    }    
    
}
