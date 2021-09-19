package Objets.Missiles;

import Objets.Missiles.Joueur.MissileNuke;
import Objets.Missiles.Joueur.MissileCanon;
import Objets.Missiles.Joueur.MissileRapide;
import Objets.Missiles.Joueur.MissileNormal;
import Objets.Missiles.Ennemis.MissileInvader;
import Objets.Missiles.Ennemis.MissileBoss;
import Entites.Vaisseau;
import iut.Game;

/**
 * Fabrique des différents types de missiles
 * @author Mathis Poncet
 */
public class FabriqueMissile {
    public static Missile fabriquerUnMissile(Game g, int x, int y, TypeMissile typeMissile, Vaisseau v){
        Missile missile = null;
        switch(typeMissile){
            case NORMAL:
                missile = new MissileNormal(g, x, y, v);
                break;
            case INVADERDEFAUT:
                missile = new MissileInvader(g, x, y, v);
                missile.setCoefficientMagnetisme(Utilities.Utilite.randomBetweenRange(1, 5));
                missile.setRandomDirection(87, 93);
                break;
            case RAPIDE:
                missile = new MissileRapide(g, x, y, v);
                missile.setVitesse(0.5);
                break;
            case NUKE:
                missile = new MissileNuke(g, x, y, v);
                break;
            case MISSILE_CANON:
                missile = new MissileCanon(g, x, y, v);
                missile.setCoefficientMagnetisme(Utilities.Utilite.randomBetweenRange(100, 200));
                missile.setRandomDirection(87, 93);
                missile.setVitesse(0.3);
                        
                break;
            case MISSILE_BOSS:
                missile = new MissileBoss(g, x, y, v);
                missile.setCoefficientMagnetisme(Utilities.Utilite.randomBetweenRange(20, 50));
                missile.setRandomDirection(87, 93);
                missile.setVitesseRotation(2);
                break;
        }
        return missile;
    }
}
