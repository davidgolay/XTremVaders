package Entites;

import Graphics.VFX.Animation;
import Graphics.VFX.ItemAnime;
import iut.Game;

/**
 * Frabrique à invader
 * @author Mathis Poncet
 */
public class FabriqueInvader {
    /**
     * Permet de fabriquer un invader suivant le type passé en paramètre
     * @param g le jeu
     * @param x abssice de départ
     * @param y ordonnée de départ
     * @param type de l'invader
     * @param vitesse la vitesse d'un invader
     * @return l'invader avec le bon type
     */
    public static Invader fabriquerUnInvader(Game g,int x, int y,TypeInvader type, double vitesse){
       Invader invader = null;
       switch(type){
           case NORMAL:
               invader = new InvaderNormal(g, "alienGreen1" ,x, y, vitesse);
               ItemAnime iNormal = new ItemAnime(g, "alienGreen1", x, y, Animation.generateRandomInvaderNormal(), invader);
               invader.setItemAnime(iNormal);
               invader.setPtVie(20);
               break;
       }
       return invader;
   } 
}
