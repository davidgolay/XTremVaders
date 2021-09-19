package Graphics.SpritesAnimes;

import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author David Golay
 */
public class ExplosionAsteroid extends GameItem{
    
    private ItemAnime itemAnime;

    public ExplosionAsteroid(Game g, String name, double _x, double _y) {
        super(g, name, _x, _y);
        this.itemAnime = new ItemAnime(g, name, (int)_x, (int)_y, TypeAnimation.ASTEROID_EXPLODE, this);
        moveXY(-itemAnime.getWidth()/2, -itemAnime.getHeight()/2);
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
        return "AsteroidExplosion";
    }

    @Override
    public void evolve(long l) {
        itemAnime.playAnimation(l);
    }
    
}
