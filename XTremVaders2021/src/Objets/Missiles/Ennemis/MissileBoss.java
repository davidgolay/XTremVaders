package Objets.Missiles.Ennemis;

import Entites.Vaisseau;
import Graphics.SpritesAnimes.EnnemiExplosion;
import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author David
 */
public class MissileBoss extends MissileEnnemi {
    //missile du boss animé
    private ItemAnime itemAnime;

    public MissileBoss(Game aG, int aX, int aY, Vaisseau vaisseau) {
        super(aG, "cold1", aX, aY, vaisseau);
        this.itemAnime = new ItemAnime(aG, "missile/missile_boss/missile_boss_00001", (int)aX, (int)aY, TypeAnimation.MISSILE_BOSS, this); 
    }

    @Override
    public void collideEffect(GameItem gameItem) {
        super.collideEffect(gameItem);      
    }

    @Override
    public TypeMissile getMissileType() {
        return TypeMissile.MISSILE_BOSS;
    }

    @Override
    public void effetExplosion() {
        // creation d'une explosion
        EnnemiExplosion explosionItem = new EnnemiExplosion(getGame(), "explosion1/explosion1_00000", getMiddleX(), getMiddleY());
        getGame().addItem(explosionItem);
    }

    @Override
    public void deplacement(long dt) {
        itemAnime.loopAnimation(dt, 30);
        this.moveDA(dt * getVitesse(), -getRandomDirection() );
        this.trackPlayerPosition();
    }

    @Override
    public int getDegat(iut.GameItem itemType) {
        return 50;
    }
    
}
