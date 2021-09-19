package Objets.Missiles;

import Graphics.SpritesAnimes.ExplosionNuke;
import Entites.Vaisseau;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.GameItem;
import iut.BoxGameItem;

public abstract class Missile extends BoxGameItem {
    
    /**
     * Vitesse du missile
     */
    private double vitesse = 0.3f; 
    
    /**
     * Le vaisseau auquel appartient le missile
     */
    private Vaisseau vaisseau;
    
    /**
     * angle du missile
     */
    private double angle;
    /**
     * vitesse de rotation du missile
     */
    private double vitesseRotation;
    
    /**
     * direction aléatoir du missile
     */
    private double randomDirection;
    
    //coeffiscient de magnetisme du missile avec le joueur
    private int coefficientMagnetisme;

    /**
     * Construit le missile aux coordonnées du vaisseau qui tire ce missile
     */
    public Missile(Game aG, String nomSprite ,int aX, int aY, Vaisseau vaisseau) {
        super(aG, nomSprite, aX, aY);
        this.vaisseau = vaisseau;
        this.angle = 0;
        this.vitesseRotation = Utilities.Utilite.randomBetweenRange(4, 8);
        this.coefficientMagnetisme = 0;
    }
    
    /**
     * Cas général de colision
     * Le missile est supprimé quand il touche un débrit spaciaux
     * @param gameItem l'item avec qui le missile est rentré en collision
     */
    @Override
    public void collideEffect(GameItem gameItem) {
        //collisions entre un missile et un débrit
        if(gameItem.getItemType().equals("Debrits")){
            effetExplosion();
            getGame().remove(this);
        }
    }

    @Override
    public String getItemType() {
        return "Missile";
    }

    public void evolve(long dt) {            
        deplacement(dt);
        if(!this.getItemType().equals("MissileJoueur") && !this.getItemType().equals("MissileCanon") ){
            angle += dt*vitesseRotation/10;
            this.setAngle(angle);    
        }      
        if(this.getTop() < 0 || this.getBottom() >= getGame().getHeight() || !XtremVaders2021.getJoueur().estVivant()){
            this.getGame().remove(this);
        }
    }
    
    public abstract TypeMissile getMissileType();
    
    /**
     * Cette méthode sera définie dans chaque missile, elle sera utile au moment
     * de la destruction de l'item missile et permettra entre autre de générer
     * un object animé pour créer l'animation d'explosion 
     * indépendement du missile
     */
    public abstract void effetExplosion();
    
    /**
     * Déplacement du missile dans le temps
     * @param dt la valeur du temps
     */
    public abstract void deplacement(long dt);

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    
    public Vaisseau getVaisseau() {
        return vaisseau;
    }
    
    /**
     * Dégat occasionné par ce type de missile plus ou moins important suivant 
     * l'item qu'il a touché
     * @param itemType l'item qui a été touché par le missile
     * @return la valeur des dégâts occasionnés
     */
    public abstract int getDegat(GameItem itemType);
    
    /**
     * Cette méthode permet de seter une direction aléatoire du missile
     * @param min minimum de la direction
     * @param max maximum de la direction
     */
    public void setRandomDirection(int min, int max){
        this.randomDirection = Utilities.Utilite.randomBetweenRange(min, max);
    }

    public double getRandomDirection() {
        return randomDirection;
    }
    
    /**
     * Cette méthode permet de définir le coefficient de magnétisme d'un missile.
     * C'est à dire qu'il sera plus ou moins attriré par la position en x du 
     * joueur
     * @param coefficientMagnetisme nouveau coefficient de magnétisme
     */
    public void setCoefficientMagnetisme(int coefficientMagnetisme) {
        this.coefficientMagnetisme = coefficientMagnetisme;
    }

    public int getCoefficientMagnetisme() {
        return coefficientMagnetisme;
    }
    
    /**
     * Cette méthode permet au missile de se déplacer de manière plus ou moins 
     * magnétiser à la position du joueur dans l'espace.
     * AKA les missiles thermiques
     */
    public void trackPlayerPosition(){
        double cordX = XtremVaders2021.getJoueur().getMiddleX() - this.getMiddleX();
        this.moveXY(cordX*this.getCoefficientMagnetisme()/1000, 0);
    }
    
    /**
     * Cette méthode permet de définir à quel point un missile toutnera
     * sur lui même. Par exemple, les missiles invaders tournent sur eux même,
     * mais pas les missiles du joueur.
     * @param vitesseRotation 
     */
    public void setVitesseRotation(double vitesseRotation) {
        this.vitesseRotation = vitesseRotation;
    }    
}