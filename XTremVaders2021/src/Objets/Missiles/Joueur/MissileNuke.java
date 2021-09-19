/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets.Missiles.Joueur;

import Graphics.SpritesAnimes.ExplosionNuke;
import Entites.Vaisseau;
import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author Mathis Poncet
 */
public class MissileNuke extends MissileJoueur{
    private ItemAnime itemAnime;

    public MissileNuke(Game aG, int x, int y, Vaisseau vaisseau) {
        super(aG, "missile/nuke/nuke_launch_00001", x, y, vaisseau);
        this.itemAnime = new ItemAnime(aG, "missile/nuke/nuke_launch_00001", (int)x, (int)y, TypeAnimation.NUKE_LAUNCH, this); 
    }

    @Override
    public void collideEffect(GameItem gameItem) {
        super.collideEffect(gameItem); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public TypeMissile getMissileType() {
        return TypeMissile.NUKE;
    }

    /**
     * Le missile crée une explosion qui se propage
     */
    @Override
    public void effetExplosion() {
        ExplosionNuke nukeExplosion = new ExplosionNuke(getGame(), "missile/nuke/nuke_launch_00000", getMiddleX() , getMiddleY() );
        getGame().addItem(nukeExplosion);
    }

    @Override
    public void deplacement(long dt) {       
        itemAnime.loopAnimation(dt, 10);
        this.moveDA(dt * getVitesse(), 90);
    }
    
    /**
     * Les missiles nuke ont moins d'efficacité sur le boss mais peuvent détruire
     * en un coup les débrits
     * @param itemType
     * @return 
     */
    @Override
    public int getDegat(iut.GameItem itemType) {
        int degat = 20; 
        if(itemType.getItemType().equals("Invader")) degat = 200;
        else if(itemType.getItemType().equals("Boss")) degat = 25;
        else if(itemType.getItemType().equals("Debrits")) degat = 45;
        return degat;
    } 
}
