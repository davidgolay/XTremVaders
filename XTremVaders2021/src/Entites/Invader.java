package Entites;

import Utilities.TypeMouvement;
import Utilities.Sons;
import Graphics.VFX.ItemAnime;
import Graphics.VFX.Animation;
import Graphics.SpritesAnimes.EnnemiExplosion;
import Jeu.XtremVaders2021;
import Utilities.Utilite;
import Objets.Missiles.Missile;
import Objets.BonusJoueur.Bonus;
import Objets.BonusJoueur.FabriqueBonus;
import Objets.BonusJoueur.TypeBonus;
import iut.Game;
import iut.GameItem;
import java.util.Random;

/**
 * Classe général des invaders
 * @author Mathis Poncet
 */
public abstract class Invader extends Vaisseau{
   /**
    * Le nombre des invaders en vie
    */
    private static int nombreEnnemi = 0;
    
    /**
     * Missile du vaisseau
     */
    private Missile missile;
    
    /**
     * Le temps maximum avant qu'un tir puisse être produit par le vaisseau
     */
    private long tempAvantTir;
    
    /**
     * L'invader possède un type de bonus définit à sa création 
     * Lorsque le joueur le tue, si le type du bonus est différent de aucun
     * un nouveau bonus est lâché par l'invader
     */
    private TypeBonus typeBonus; 
    
    /**
     * La vitesse des invaders
     */
    private static double vitesseInvaders;
    
    private ItemAnime itemAnime;   
    
    /**
     * Constructeur par initialisation
     * @param g le jeu 
     * @param sprite de l'invader
     * @param x l'abcisse de départ
     * @param vitesse de l'invader
     * @param y l'ordonnée de départs
     */
    public Invader(Game g, String sprite,int x, int y, double vitesse) {
        super(g, "alienGreen1", x, y, vitesse);
        Invader.nombreEnnemi ++;
        Random r = new Random();
        vitesseInvaders = vitesse;
        tempAvantTir = r.nextInt(5000)+3000; //entre 3s à 8s
        // création du bonus
        this.typeBonus = Bonus.genererBonus();
        VagueInvaders.ajouterBonus(typeBonus);
    }
    
    /**
     * Effets des collisions avec le joueur et le missile
     * @param gi l'item avec qui l'ennemi est en collision
     */
    @Override
    public void collideEffect(GameItem gi) {
        if(gi.getItemType().equals("MissileJoueur")){
            Missile m = (Missile) gi;
            setPtVie(getPtVie() - m.getDegat(this));
        }  
        //effet de la propagation de l'explosion de la bombe nuke
        if(gi.getItemType().equals("explosioNuke")){
            setPtVie(getPtVie() - 100);
        }
        if(!estVivant()){          
            
            //suppression de l'ennemi
            Sons.play("newSounds/explosion");
            getGame().remove(this);
            VagueInvaders.retirerInvader(this);     
            
            // le vaisseau meurt
            // creation d'une explosion
            EnnemiExplosion explosionItem = new EnnemiExplosion(getGame(), "explosion1/explosion1_00000", getMiddleX(), getMiddleY());
            getGame().addItem(explosionItem);
            
            nombreEnnemi --;
            XtremVaders2021.getJoueur().addScore();
            
            //si l'invader possède un bonus il est lâché
            if(this.typeBonus != TypeBonus.AUCUN){
                getGame().addItem(FabriqueBonus.fabriquerUnBonus(getGame(), getMiddleX(), getMiddleY(), typeBonus));
            }
        }
    }

    @Override
    public String getItemType() {
        return "Invader";
    }

    public static int getNombreEnnemi() {
        return nombreEnnemi;
    }

    @Override
    public void evolve(long dt) {
        super.evolve(dt);
        itemAnime.loopAnimation(dt+Utilite.randomBetweenRange(0, 120),  Animation.getAnimationSpeed());
        tempAvantTir -= dt;
        if(tempAvantTir <= 0){
            tirer();
            Random r = new Random();
            tempAvantTir = r.nextInt(7000)+3000; //entre 3 et 10s
        }
    }
    
    /**
     * Permet de savoir quel côté a été touché par un invader
     * @return le côté qui a été touché
     */
    public TypeMouvement coteTouche(){
        TypeMouvement res = null;
        if(this.getRight() <= 64){
            res = TypeMouvement.GAUCHE;
        }else if(this.getLeft() >= getGame().getWidth() - 64){
            res = TypeMouvement.DROITE;
        }
        return res;
    }
    
    /**
     * Permet de bouger un invader à droite, à gauche et à droite d'un certain
     * nombre de pixel
     * @param mouvement à effectuer pour se déplacer sur l'un des côtés
     */
    public void bouger(TypeMouvement mouvement, double vitesse){
        switch(mouvement){
            case DROITE:
                this.moveDA(vitesse, 0);
                break;
            case GAUCHE:
                this.moveDA(vitesse, 180);
                break;
            case BAS:
                this.moveXY(0, +64);
                break;
        }
    }
    
    /**
     * Permet à l'invader de tirer un missile
     */
    @Override
    public abstract void tirer();
      
    public abstract TypeInvader getTypeInvader();
    
    public static double getVitesseInvaders() {
        return vitesseInvaders;
    }

    public static void setVitesseInvaders(double vitesseInvaders) {
        Invader.vitesseInvaders = vitesseInvaders;
    }

    public void setItemAnime(ItemAnime itemAnime) {
        this.itemAnime = itemAnime;
        moveXY(itemAnime.centerSpriteX(), itemAnime.centerSpriteY());
    }

    public void setMissile(Missile missile) {
        this.missile = missile;
    }

}
