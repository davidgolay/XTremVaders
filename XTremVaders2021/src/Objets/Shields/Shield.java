package Objets.Shields;

import Graphics.SpritesAnimes.ImpactShield;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.GameItem;


/**
 * Classe abstraite qui décrit le comportement des boucliers
 * @author David Golay
 */
public abstract class Shield extends GameItem {
    
    private long duree;
    private int resistance;
    private int cptImpact;
    private int valueSprite;
    private long lifeSpend;

    public Shield(Game g, double _x, double _y, int resistance) {
        super(g, "shield/shieldBasic_00000", (int)_x,(int)_y);
        this.resistance = resistance;
        this.cptImpact = 0;
        this.lifeSpend = 0;
        this.duree = 10000;
    }
        
    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }
    
    @Override
    public void evolve(long l) {
        if(!XtremVaders2021.getJoueur().estVivant()) getGame().remove(this);
        //si le bouclier est en fin de vie
        if(isLiveTimeOver()){
            getGame().remove(this);
        }
        this.setLifeSpend(getLifeSpend()+l);
        int deltaPosX = XtremVaders2021.getJoueur().getMiddleX() - this.getMiddleX();
        moveXY(deltaPosX, 0);
    }
    
    @Override
    public void collideEffect(GameItem gi) {       
        if(gi.getItemType().equals("MissileEnnemi")){
            getGame().remove(gi);
            // creation d'une explosion
            ImpactShield impactShield = new ImpactShield(getGame(), "explosion1/explosion1_00001", gi.getMiddleX(), gi.getMiddleY());
            getGame().addItem(impactShield);
            //insérer la logique des resistances de missile ici
            this.setCptImpact(this.getCptImpact()+1);
            
            //si le bouclier est cassé
            if(isBroken()){
                getGame().remove(this);
            }
            //selectSprite();
        }
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    
    /**
     * Methode qui dit si le temps de vie du blouclier est écoulé
     * @return 
     */
    public boolean isLiveTimeOver(){
        boolean isOver = false;
        if(lifeSpend > duree){
            isOver = true;
        }
        return isOver;
    }
    
    /**
     * Méthode qui dit si le bouclier est cassé
     * Autrement dit, sa résistance est à zero
     * @return 
     */
    public boolean isBroken(){
        boolean res = false;
        if(cptImpact >= resistance){
            res = true;
        }
        return res;
    }

    public int getResistance() {
        return resistance;
    }

    public int getValueSprite() {
        return valueSprite;
    }

    public int getCptImpact() {
        return cptImpact;
    }

    public void setCptImpact(int cptImpact) {
        this.cptImpact = cptImpact;
    }

    public void setLifeSpend(long lifeSpend) {
        this.lifeSpend = lifeSpend;
    }

    public long getLifeSpend() {
        return lifeSpend;
    }
    
    
    
    
    
}
