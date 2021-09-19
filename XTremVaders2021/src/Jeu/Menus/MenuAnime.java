/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Menus;

import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author David
 */
public class MenuAnime extends GameItem {
    
    private ItemAnime fondAnime;

    public MenuAnime(Game g, double _x, double _y) {
        super(g, "menus/menu_principal_animation/menu_principal_animation_00000", _x, _y);
        this.fondAnime = new ItemAnime(getGame(), "menus/menu_principal_animation/menu_principal_animation_00000", 0, 0, TypeAnimation.MENU_PRINCIPAL, this);
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
        return "menuPrincipal";
    }

    @Override
    public void evolve(long l) {
        fondAnime.loopAnimation(l, 30);
    }
    
}
