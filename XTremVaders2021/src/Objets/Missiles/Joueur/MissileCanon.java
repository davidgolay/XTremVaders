package Objets.Missiles.Joueur;

import Entites.Vaisseau;
import Graphics.SpritesAnimes.ImpactMissile;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author David
 */
public class MissileCanon extends MissileJoueur {

    public MissileCanon(Game aG, int aX, int aY, Vaisseau vaisseau) {
        super(aG, "missile/missile_canon_energy/missile_canon_energy_00001", aX, aY, vaisseau);
    }

    @Override
    public void collideEffect(GameItem gameItem) {
       super.collideEffect(gameItem);
    }
    
    @Override
    public TypeMissile getMissileType() {
        return TypeMissile.MISSILE_CANON;
    }

    @Override
    public void effetExplosion() {
        ImpactMissile impact = new ImpactMissile(getGame(), "missile/missile_impact/missile_impact_", getMiddleX(), getTop() );
        getGame().addItem(impact);
    }

    @Override
    public void deplacement(long dt) {
        this.moveDA(dt * getVitesse(), getRandomDirection() );
    }
    
    /**
     * Les missiles du canon possède une efficité fixe indifférente de l'item
     * qu'il a touché
     * @param itemType
     * @return 
     */
    @Override
    public int getDegat(iut.GameItem itemType) {
        return 2;
    }
    
}
