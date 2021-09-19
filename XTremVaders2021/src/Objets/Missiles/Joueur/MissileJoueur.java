/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets.Missiles.Joueur;

import Entites.Vaisseau;
import Objets.Missiles.Missile;
import Objets.Missiles.TypeMissile;
import iut.Game;
import iut.GameItem;

/**
 * Classe représentant le missile des joueurs
 * @author Mathis Poncet
 */
public abstract class MissileJoueur extends Missile {

    public MissileJoueur(Game g, String sprite, int x, int y, Vaisseau v) {
        super(g, sprite, x, y, v);
    }
    
    /**
     * Le missile peut rentrer en collision avec des missiles ennemis mais aussi
     * avec des missiles joueur pour éviter de tirer en boucle!
     * @param gameItem l'item avec qui le missile est rentré en collison
     */
    @Override
    public void collideEffect(GameItem gameItem) {
        super.collideEffect(gameItem); //To change body of generated methods, choose Tools | Templates.
        if(gameItem.getItemType().contains("Invader") || gameItem.getItemType().contains("Boss")){
            effetExplosion();
            getGame().remove(this);
        }
        //collisions avec un missile ennemi ou un missile du joueur
        else if(gameItem.getItemType().equals("MissileEnnemi") || gameItem.getItemType().equals("MissileJoueur")){
            effetExplosion();
            getGame().remove(this);
        }
    }

    @Override
    public String getItemType() {
        return "MissileJoueur";
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
