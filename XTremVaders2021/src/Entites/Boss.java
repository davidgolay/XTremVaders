package Entites;

import Graphics.VFX.Animation;
import Graphics.SpritesAnimes.BossExplosion;
import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Objets.Missiles.FabriqueMissile;
import Objets.Missiles.Missile;
import Objets.Missiles.TypeMissile;
import Utilities.Sons;
import Utilities.TypeMouvement;
import Utilities.Utilite;
import iut.Game;
import iut.GameItem;
import java.util.Random;

/**
 * Le boss du jeu qui apparaît à partir de la 7ème vague
 * @author Mathis Poncet
 */
public class Boss extends Vaisseau{
    /**
     * Le côté ou aller
     */
    private TypeMouvement coteOuAller;
    
    /**
     * Temps avant que le boss tire
     * A son apparition, il attend 3s avant de tirer son premier missile
     */
    private long tempsAvantTirer = 2000; 
    
    /**
     * Utile pour l'animation du boss
     */
    private ItemAnime itemAnime;
    
    /**
     * Utile pour générer le nombre de missile à la mort de boss
     */
    private static int agressivite = 10;
    
    
    /**
     * Par défaut à droite
     * @param g le jeu
     * @param x abssice de départ
     * @param y ordonnée de départ
     * @param vitesse vitesse de base
     * @param resistance la résistance du boss
     */
    public Boss(Game g, int x, int y, double vitesse, int resistance) {
        super(g, "alienGreen3", x, y, vitesse);
        this.coteOuAller = TypeMouvement.DROITE;
        super.setPtVie(resistance);
        this.itemAnime = new ItemAnime(g, "", x, y, TypeAnimation.ALIEN_FATHER, this);
        itemAnime.setAnimationType(TypeAnimation.ALIEN_FATHER);
        //agressivite = 
    }

    @Override
    public void tirer() {
        Missile m = FabriqueMissile.fabriquerUnMissile(getGame(), getMiddleX()-30, getMiddleY()-20, TypeMissile.MISSILE_BOSS, this);
        getGame().addItem(m);
    }

    @Override
    public String getItemType() {
        return "Boss"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void collideEffect(GameItem g) {
        if(g.getItemType().equals("MissileJoueur")){
            Missile m = (Missile) g;
            this.setPtVie(getPtVie() - m.getDegat(this));
        }
    }

    /**
     * Permet de faire bouger le boss de droite à gauche 
     * @param dt le cours du temps
     */
    public void bougerBoss(long dt){  
        if(coteOuAller == TypeMouvement.DROITE && this.getRight() < getGame().getWidth()) this.moveDA(dt * 0.15, 0);
        else coteOuAller = TypeMouvement.GAUCHE;
        if(coteOuAller == TypeMouvement.GAUCHE && this.getLeft() > 0) this.moveDA(dt * 0.15, 180);
        else coteOuAller = TypeMouvement.DROITE;
    }
    
    /**
     * Le boss se déplace de droite vers la gauche et tire à une cadence aléatoire
     * entre 5s et 10s
     * @param dt le cours du temps
     */
    @Override
    public void evolve(long dt) {
        super.evolve(dt);
        itemAnime.loopAnimation(dt+Utilite.randomBetweenRange(0, 120),  Animation.getAnimationSpeed());
        bougerBoss(dt);
        tempsAvantTirer -= dt;
        if(tempsAvantTirer <= 0){
            tirer();
            Random r = new Random();
            tempsAvantTirer= r.nextInt(5000) + 5000; //entre 5s et 10s
        }
        //si le boss n'est plus vivant
        if(!estVivant()) {
            BossExplosion explosion = new BossExplosion(getGame(), this.getMiddleX(), this.getMiddleY());
            Sons.play("newSounds/bossDeath");
            getGame().addItem(explosion);
            tirerMissileMortel();
            getGame().remove(this);
        }       
    } 
    
    /**
     * Methode appelé a la mort du boss pour qu'il balance des missiles magnétisés
     */
    public void tirerMissileMortel(){
        Missile m = null;
        int nbMissile = VagueInvaders.getNbVagues()*2;
        for(int i=0; i<agressivite; i++){
            m = FabriqueMissile.fabriquerUnMissile(getGame(), getMiddleX()-30, getMiddleY()-20, TypeMissile.INVADERDEFAUT, this);
            m.setRandomDirection(40, 140);
            m.setCoefficientMagnetisme(Utilite.randomBetweenRange(0, 50));
            m.setVitesse(0.20);
            getGame().addItem(m);
        }
    }
}
