package Objets.Debris;

import Graphics.SpritesAnimes.ExplosionAsteroid;
import Graphics.VFX.TypeAnimation;
import iut.Game;

/**
 * @author David Golay
 */
public class Asteroid extends Debris {
    
    public Asteroid(Game g, double _x, double _y) {
        super(g, "", _x, _y);
    }    

    @Override
    public void effetExplosion() {
        /*
        * on crée une instance de asteroid Explosion (qui contient un itemAnime) qui permet
        * de générer une animation d'explosion d'asteroid
        */
        ExplosionAsteroid explosion = new ExplosionAsteroid(getGame(), "asteroid/explode/asteroid_1_explode_00000_optimized", getMiddleX() , getMiddleY() );      
        getGame().addItem(explosion);
        //explosion.moveXY(-getWidth()/2, -getHeight()/2);
    }

    @Override
    public void effetDegradation() {
        getItemAnime().setLifeSpend(0);
        getItemAnime().setAnimationType(TypeAnimation.ASTEROID_BROKEN_1); 
    }

}
