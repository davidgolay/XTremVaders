/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.SpritesAnimes;

import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import iut.Game;
import iut.GameItem;

/**
 * Cette classe génère l'explosion du boss indépendement de l'existence du boss
 * @author David
 */
public class BossExplosion extends GameItem {
    //attribut utile pour l'animation d'explosion du boss
    private ItemAnime itemAnime;
    
    public BossExplosion(Game g, int _x, int _y) {
        super(g, "invaders/invader_father/explode/explode_00000", _x, _y);
        this.itemAnime = new ItemAnime(g, "invaders/invader_father/explode/explode_00000", _x, _y, TypeAnimation.ALIEN_FATHER_EXPLODE, this);
        moveXY(itemAnime.centerSpriteX(), itemAnime.centerSpriteY());
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
        return "explosionBoss";
    }

    @Override
    public void evolve(long dt) {
        itemAnime.playAnimation(dt);
    }    
}