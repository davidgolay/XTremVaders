package Jeu;

import Utilities.Sons;
import Entites.VagueInvaders;
import Entites.Joueur;
import java.awt.Graphics;
import iut.Vector;
import iut.Game;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * ReprÃ©sente un petit jeu simple
 * @author aguidet
 */
public class XtremVaders2021 extends Game {
    /**
     * Joueur qui est initialisé au départ
     */
    private static Joueur joueur;
    
    private Partie partie;
   
    
    /**
     * @param aArgs the command line arguments Fonction principal (main)
     * Fonction principale du jeu
     */
    public static void main(String[] aArgs) {
        XtremVaders2021 jeu = new XtremVaders2021(1024, 800);
        jeu.play();
    }

    /**
     * Initialise le jeu
     * @param width la largeur de l'écran
     * @param height la hauteur de l'écran
     */
    public XtremVaders2021(int width, int height) {
        super(width, height, "XtremeVaders");
        Sons.play("lostLands");
        //GameItem.DRAW_HITBOX=true;
    }
    
    /**
     * Crée les items au début du jeu
     */
    @Override
    protected void createItems() { 
        joueur = new Joueur(this, 0.25d);
        //joueur.resetJoueur();
        XtremVaders2021.getJoueur().setEstActionFreeze(true);
        joueur.setPtVie(3);
        this.addItem(joueur);
        this.partie = new Partie(this, joueur);
        this.addItem(partie);        
    }

    protected void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(00, 0, getWidth(), getHeight());
    }

    /**
     * Appelée lorsque le joueur a perdu la partie
     */
    protected void lost() {
        JOptionPane.showMessageDialog(this, "Vous avez perdu! \nVous ne posséder plus de vie");      
    }

    /**
     * Appelée lorsque le joueur a perdu la partie
     */
    protected void win() {
        JOptionPane.showMessageDialog(this, "Et c'est gagné!!!");
    }

    protected boolean isPlayerWin() {
        // bon courage ...
        return VagueInvaders.getNbVagues() > 100;
    }

    protected boolean isPlayerLost() {
        // et oui, le joueur ne perd jamais
        // il est bloqué dans une boucle infinie
        return false;
    }

    /**
     * gets the gravity of the Game
     */
    public Vector getGravity() {
        return null;
    }   

    public static Joueur getJoueur() {
        return joueur;
    }

    public static void setJoueur(Joueur joueur) {
        XtremVaders2021.joueur = joueur;
    }
    
    
    
    
}