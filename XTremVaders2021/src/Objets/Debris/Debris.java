package Objets.Debris;

import Graphics.VFX.ItemAnime;
import Jeu.XtremVaders2021;
import Objets.BonusJoueur.BonusShield;
import Objets.Missiles.Missile;
import Utilities.Sons;
import Utilities.Utilite;
import iut.Game;
import iut.GameItem;


/**
 *
 * @author David
 */
public abstract class Debris extends GameItem {

    private long vitesse;
    private int resistance;
    private ItemAnime itemAnime;
    private TypeDebris typeDebris;
    
    // angle de l'asteroid
    private double angle;
   
    //vitesse de rotation de l'asteroid
    private double vitesseRotation;

    public Debris(Game g, String name, double _x, double _y) {
        super(g, "asteroid/asteroid_1", 0 , 0);              
        this.vitesse = Utilite.randomBetweenRange(20, 30);      
        this.angle = 0;
        this.vitesseRotation = Utilities.Utilite.randomBetweenRange(1, 3);
        int randomX = Utilite.randomBetweenRange(0, getGame().getWidth());
        moveXY(randomX, -this.getHeight());
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
        if(gi.getItemType().equals("MissileJoueur")) {
            Sons.play("newSounds/asteroidCrack");
            Missile m = (Missile) (gi);
            this.resistance -= m.getDegat(this);
            effetDegradation();
        }    
    }

    @Override
    public String getItemType() {
        return "Debrits";
    }

    @Override
    public void evolve(long l) {
        //suppresion des debris si le joueur est mort
        if(!XtremVaders2021.getJoueur().estVivant()) getGame().remove(this);
        angle += l*vitesseRotation/100;
        this.setAngle(angle);    
        itemAnime.playOnce(l);
        this.moveXY(0, (int)l*vitesse/100);
        //si le débris se casse
        if(resistance <= 0) {
            effetExplosion();
            getGame().remove(this);
            genererBonusShield();
        }     
        if(this.getTop()>getGame().getHeight()) {
            getGame().remove(this);
        }
    }
    
    /**
     * Cette méthode est appelé lors de la destructeur d'un debris 
     * (cad: quand sa resistance == 0 )
     */
    public abstract void effetExplosion();
    
    /**
     * Cette méthode est appelé à chaque fois que le joueur touche un debris
     */
    public abstract void effetDegradation();

    public void setItemAnime(ItemAnime itemAnime) {
        this.itemAnime = itemAnime;
    }

    public int getResistance() {
        return resistance;
    }

    public ItemAnime getItemAnime() {
        return itemAnime;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    
    public void deleteDebris(){
        getGame().remove(this);
    }
    
    /**
     * Méthode qui génère un shield avec une probabilité
     */
    private void genererBonusShield(){
        int random = Utilite.randomBetweenRange(0, 100);
        int proba = 66; // 1/3 de chance
        if(random > proba) {
            BonusShield bonusShield = new BonusShield(getGame(), getMiddleX(), getMiddleY());
            getGame().addItem(bonusShield);
        }     
    }
    
    
    

}
