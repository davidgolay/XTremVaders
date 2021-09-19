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
 *
 * @author David
 */
public class ExplosionNuke extends GameItem {
    //attribut utile pour l'animation d'explosion de la nuke
    private ItemAnime itemAnime;

    public ExplosionNuke(Game g, String name, double _x, double _y) {
        super(g, name, (int)_x, (int)_y);
        this.itemAnime = new ItemAnime(g, name, (int)_x, (int)_y, TypeAnimation.NUKE_EXPLODE, this);
        //ajustement de la position de l'explosion
        moveXY(-200,-220);
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
        return "explosioNuke";
    }

    @Override
    public void evolve(long l) {
        this.moveXY(itemAnime.relativeX(), itemAnime.relativeY());
        itemAnime.playAnimation(l*6);       
    }

    
}
