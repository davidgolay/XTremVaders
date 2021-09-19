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
public class ImpactMissile extends GameItem {
    private ItemAnime itemAnime;

    public ImpactMissile(Game g, String name, double _x, double _y) {
        super(g, name, (int)_x, (int)_y);
        this.itemAnime = new ItemAnime(g, name, (int)_x, (int)_y, TypeAnimation.MISSILE_IMPACT, this);
        moveXY(-getWidth()/2, -getHeight()/2 -10);
        
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
        return "InpactMissile";
    }

    @Override
    public void evolve(long l) {
        this.moveXY(itemAnime.relativeX(), itemAnime.relativeY());
        itemAnime.playAnimation(l);       
    }
    
}
