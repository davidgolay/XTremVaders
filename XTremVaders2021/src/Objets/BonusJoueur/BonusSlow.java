package Objets.BonusJoueur;

import Entites.Invader;
import Entites.VagueInvaders;
import iut.Game;
import java.util.Random;

/**
 * Bonus ralentissant toute la vague des invaders
 * @author Mathis Poncet
 */
public class BonusSlow extends Bonus {
    /**
     * Ancienne vitesse que les invaders possédaient
     */
    private double ancienneVitesse;
    
    /**
     * La durée de l'effet du bonus
     */
    private long dureeEffet = 1000;
    
    /**
     * Constructeur par initialisation
     * @param g le jeu auquel appartient le bonus
     * @param x abcisse à laquelle le bonus va apparaître
     * @param y ordonnée à laquelle le bonus va apparaîttre
     */
    public BonusSlow(Game g, int x, int y) {
        super(g, "bonus/itemsBonus/bonusSlow", x, y);
        Random r = new Random();
        dureeEffet = r.nextInt(2000)+ 3000; //entre 3s et 5s de ralentissement des ennemis 
    }

    @Override
    public String getItemType() {
        return "BonusSlow";
    }

    @Override
    public void bouger(long dt) {
        moveDA(dt * getVitesse(), -90);
    }

    @Override
    public TypeBonus getTypeBonus() {
        return TypeBonus.SLOW;
    }
    
    /**
     * L'effet est lancée et diminue la vitesse des invaders
     */
    @Override
    public void lancerEffet() {
        if(!isEnCours()){
            this.changeSprite("transparent");
            this.ancienneVitesse = VagueInvaders.vitesseVague();
            Invader.setVitesseInvaders(0.08);
        }
        setEnCours(true);
    }
    
    /**
     * Le bonus est temporaire et est arrêté suivant l'attribut qui détermine la
     * durée de l'effet
     * @param dt évolution du bonus au cours du temps
     */
    @Override
    public void finEffet(long dt) {
        dureeEffet -= dt;
        if(dureeEffet <= 0){
            setEnCours(false);
            Invader.setVitesseInvaders(ancienneVitesse);
            //quand le bonus est fini on l'enlève du jeu
            getGame().remove(this);
        }
    } 
}
