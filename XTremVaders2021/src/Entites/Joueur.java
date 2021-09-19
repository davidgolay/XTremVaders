package Entites;

import Utilities.Sons;
import Graphics.VFX.ItemAnime;
import Objets.Missiles.FabriqueMissile;
import Objets.Missiles.Missile;
import Objets.BonusJoueur.Bonus;
import Graphics.VFX.Animation;
import Graphics.VFX.TypeAnimation;
import Objets.Canon;
import Objets.Missiles.TypeMissile;
import Objets.Shields.Shield;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Joueur du petitjeu
 * @author aguidet
 */
public class Joueur extends Vaisseau implements KeyListener {
    /**
     * Le score du joueur
     */
    private int _score = 0;
    
    /**
     * Permet au joueur de lancer des missiles
     */
    private Missile missile;
    
    /**
     * utile pour attandre le temps avant de pouvoir tirer à nouveau
     */
    private double tempsAvantTirer;
    
    /**
     * boolean qui décrit/défini si le joueur peut tirer ou non
     */
    private boolean canShoot;
    
    /**
     * Le type de missile que possède le joueur dans son inventaire
     */
    private TypeMissile typeMissile;
    
    /**
     * Détermine si le joueur peut aller à gauche
     * (quand il appuie sur la touche gauche du clavier)
     */
    private boolean left;
    /**
     * Détermine si le joueur peut aller à droite
     * (quand il appuie sur la touche droite du clavier)
     */
    private boolean right;
    
    /** 
     * utile pour l'animation du vaisseau
     */
    private ItemAnime itemAnime;
    
    /**
     * canon du joueur
     */
    private Canon canon;
    
    /**
     *  bouclier du joueur
     */
    private Shield shield;
    
    /*
    *utile pour bloquer les actions du vaisseau et sur le vaisseau
    */
    private boolean estActionFreeze;
    
    /**
     * limite de vie
     */
    private int maxPtVie;

    
    /**
     * Initialise le joueur
     * @param g le jeu
     * @param vitesse du joueur
     */
    public Joueur(Game g, double vitesse) {
        super(g, "joueur/playerShip3", 485, 690, vitesse);
        int PV = 100;
        setPtVie(PV);
        this.maxPtVie  = PV;
        this._score          = 0;
        this.estActionFreeze = false;
        this.canShoot        = true;
        this._score          = 0;
        this.tempsAvantTirer = 1000;
        typeMissile          = TypeMissile.NORMAL;
        right                = false;
        left                 = false;
        //définition de la première animation du vaisseau Joueur qui sera jouée
        this.itemAnime = new ItemAnime(getGame(), "transparent", 485, 690, TypeAnimation.SPACESHIP3_NORMAL, this);
        //resetJoueur();
    }

    @Override
    public void tirer() {
        //si les actions du vaisseaux ne sont pas freezées
        if(!estActionFreeze){
            //si le joueur peut tirer
            if(this.canShoot){
                Sons.play("newSounds/spaceShoot");
                this.canShoot = false;
                this.missile = FabriqueMissile.fabriquerUnMissile(getGame(), getMiddleX()-5, getMiddleY()-50, typeMissile, this);
                getGame().addItem(missile);
                itemAnime.setLifeSpend(0);
                itemAnime.setAnimationType(TypeAnimation.SPACESHIP3_SHOOT);
            }
        }
    }
    
    /**
     * Quand le joueur entre en colision avec un missile ennemi il perd une vie
     * S'il rentre en colision avec un invader il perd toutes ses vies
     * @param item avec lequel le joueur est entré en colision 
     */
    @Override
    public void collideEffect(GameItem item) {
        //si les actions du vaisseaux ne sont pas freezées
        if(estVivant() && !estActionFreeze){
            if(item.getItemType().equals("MissileEnnemi")){
                Missile m = (Missile) (item);
                System.out.println(getPtVie());
                setPtVie(getPtVie() - m.getDegat(this) );
                Sons.play("invaderKilled");
                System.out.println(getPtVie());
                itemAnime.setAnimationType(TypeAnimation.SPACESHIP3_COLLISION);         
            }
            else if(item.getItemType().equals("Invader")){
                setPtVie(-getPtVie());
                Sons.play("newSounds/playerTouched");
            }
            else if(item.getItemType().equals("Debrits")){
                setPtVie(getPtVie()-50);
                Sons.play("newSounds/asteroidCollision");
            }
        }
    }

    @Override
    public void evolve(long dt) {
        //Animation de mort
        if(!estVivant()){
            itemAnime.setAnimationType(TypeAnimation.SPACESHIP3_DEAD);
        }
        // selection des animations a lancer selon les cas
        if(itemAnime.getAnimationType() == TypeAnimation.SPACESHIP3_COLLISION && itemAnime.AnimationFinie()){
            itemAnime.setAnimationType(TypeAnimation.SPACESHIP3_NORMAL);
        } else if(itemAnime.getAnimationType() == TypeAnimation.SPACESHIP3_SHOOT && itemAnime.AnimationFinie()){
            itemAnime.setAnimationType(TypeAnimation.SPACESHIP3_NORMAL);
        }       
        
        itemAnime.loopAnimation(dt, Animation.getAnimationSpeed());
        this.tempsAvantTirer -= dt;
        
        if(this.tempsAvantTirer <= 0){
            this.canShoot = true;
            this.tempsAvantTirer = 1000;
        }
        //mouvement du joueur
        appliquerMouvement(dt);
    }

    @Override
    public String getItemType() {
        return "Joueur";
    }
    
    /**
     * Evènement appelé lorsqu'une touche est pressée. Gère les mouvements 
     * du 
     * @param e la touche qui est pressée
     */
    @Override
    public void keyPressed(KeyEvent e) {
        try{
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    left = true;
                    setVitesse(getVitesse() + 0.005); //accélération + on appuie
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    setVitesse(getVitesse() + 0.005); //accélération + on appuie
                    break;
                case KeyEvent.VK_Q:
                    left = true;
                    setVitesse(getVitesse() + 0.005); //accélération + on appuie
                    break;
                case KeyEvent.VK_D:
                    right = true;
                    setVitesse(getVitesse() + 0.005); //accélération + on appuie
                    break;
                case KeyEvent.VK_SPACE:
                   tirer();
                   break;
                case KeyEvent.VK_A:
                   this.canon.tirer();
                   break;
            }
        }catch(Exception x){}
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        try{
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    left = false;
                    setVitesse(0.25); //réinitialisation de la vitesse
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    setVitesse(0.25); //réinitialisation de la vitesse
                    break;
                case KeyEvent.VK_Q:
                    left = false;
                    setVitesse(0.25); //réinitialisation de la vitesse
                    break;
                case KeyEvent.VK_D:
                    right = false;
                    setVitesse(0.25); //réinitialisation de la vitesse
                    break;
            }
        }catch(Exception x){}
    }

    public int getScore() {
        return _score;
    }
    
    public void addScore(){
        _score = _score + 1;
    }  
    
    public void setTypeMissile(TypeMissile typeMissile) {
        this.typeMissile = typeMissile;
    }
    
    /**
     * Changement de direction et calcul de la vitesse suivant si gauche ou
     * droite est appuyé
     * @dt l'évolution du temps
     */
    private void appliquerMouvement(long dt){
        if(this.getRight() < getGame().getWidth() && right) {
            canon.deplacerCanon(dt * getVitesse(), 0);
            moveDA(dt * getVitesse(), 0);        
        }
        else if(this.getLeft() > 0 && left) {
            moveDA(dt * getVitesse(), 180);
            canon.deplacerCanon(dt * getVitesse(), 180);
        }
    }

    /**
     * Cette méthode permet de setter le Shield du joueur et de l'ajouter au jeu
     * @param shield qui ecrasera/settera l'attribut shield du joueur
     */
    public void initShield(Shield shield) {
        this.shield = shield;
        getGame().addItem(shield);
    }
    
    /**
     * Cette méthode créer le double Canon du Joueur et l'ajoute au jeu
     */
    public void creerUnCanon(Canon canon){
        this.canon = canon;
        getGame().addItem(canon);
    }

    public Canon getCanon() {
        return canon;
    }
    
    /**
     * Cette méthode permet d'activer le boolean qui servira à savoir 
     * si on doit bloquer les actions du vaisseau ou non
     */
    public void setEstActionFreeze(boolean estActionFreeze) {
        this.estActionFreeze = estActionFreeze;
    }

    public boolean isEstActionFreeze() {
        return estActionFreeze;
    }
    
    public void resetJoueur(){
        int PV = 100;
        setPtVie(PV);
        this.maxPtVie  = PV;
        this._score          = 0;
        this.estActionFreeze = false;
        this.canShoot        = true;
        this._score          = 0;
        this.tempsAvantTirer = 1000;
        typeMissile          = TypeMissile.NORMAL;
        right                = false;
        left                 = false;
        //définition de la première animation du vaisseau Joueur qui sera jouée
        this.itemAnime = new ItemAnime(getGame(), "transparent", 485, 690, TypeAnimation.SPACESHIP3_NORMAL, this);
        initCanon();
    }

    public int getMaxPtVie() {
        return maxPtVie;
    }

    public void setMaxPtVie(int maxPtVie) {
        this.maxPtVie = maxPtVie;
    }    
    
    /**
     * Cette méthode initialise le canon
     */
    public void initCanon(){
        this.canon     = new Canon(getGame(), this);
        getGame().addItem(canon);
    }
}