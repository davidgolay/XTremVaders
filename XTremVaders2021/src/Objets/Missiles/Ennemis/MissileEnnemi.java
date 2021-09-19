package Objets.Missiles.Ennemis;

import Entites.Vaisseau;
import Objets.Missiles.Missile;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/**
 * Classe qui décrit le comportement des missiles enemis
 * @author Mathis Poncet
 */
public abstract class MissileEnnemi extends Missile {

    public MissileEnnemi(Game g, String sprite, int x, int y, Vaisseau v) {
        super(g, sprite, x, y, v);
    }

    @Override
    public void collideEffect(GameItem gameItem) {
        super.collideEffect(gameItem); //To change body of generated methods, choose Tools | Templates.
        if(gameItem.getItemType().equals("Joueur")){
            effetExplosion();
            getGame().remove(this);
        }
        //collisions avec le missile du joueur
        else if(gameItem.getItemType().equals("MissileJoueur")){
            effetExplosion();
            getGame().remove(this);
        }
    }

    @Override
    public String getItemType() {
        return "MissileEnnemi";
    }
    
    /**
     * Renvoie le type de missile
     */
    public abstract TypeMissile getMissileType();

    /**
     * Effet lorsque le missile touche une entité
     */
    public abstract void effetExplosion();

    /**
     * Déplacement du missile au court du temps
     * @param aDt Evolution du temps
     * Permet de calculer la vitesse de déplacement
     */
    public abstract void deplacement(long aDt);
    
}
