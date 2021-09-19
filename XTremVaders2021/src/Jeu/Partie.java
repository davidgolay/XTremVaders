package Jeu;

import Entites.GenerateurBoss;
import Entites.Joueur;
import Entites.VagueInvaders;
import Graphics.Background;
import Graphics.Dashboard.HealthBar;
import Jeu.Menus.FabriqueMenu;
import Jeu.Menus.Menu;
import Jeu.Menus.TypeMenu;
import iut.Game;
import iut.GameItem;
import Graphics.Dashboard.ScoreBar;

/**
 * Cette classe permet d'initialiser une partie et de relancer une partie
 * En invoquant les différents éléments necessaires.
 * @author David
 */
public class Partie extends GameItem {

    /**
     * Menu de démarrage et menu de fin de partie
     */
    private Menu menu;
    
    /**
     * Fond étoilé du jeu
     * Ce fond génére aussi les débris
     */
    private Background background;
    
    /**
     * Attibut permettant de générer les vagues d'invaders
     */
    private VagueInvaders vagueInvaders;   
    
    /**
     * Attribut permettant de générer le boss
     */
    private GenerateurBoss generateurBoss;
    
    /**
     * Attribut permettant d'afficher la barre de score en haut à gauche
     * pendant la partie
     */
    private ScoreBar scoreBar;
    
    /**
     * Attribut permettant d'afficher la barre de vie en cours de partie
     */
    private HealthBar healthBar;
    
    /**
     * Attribut permettant d'afficher le score au centre de l'ecran 
     * en fin de partie
     */
    private ScoreBar scoreFin;
    
    /**
     * Compteur utile pour effectuer certaines instructions seulment une fois
     * dans le evolve. afin d'éviter de tout refaire en boucle et ainsi d'
     * éviter de générer des actions indesirables
     */
    private int cptIteration;
    
    public Partie(Game g, Joueur joueur) {
        super(g, "transparent", 0, 0);
        //Lancement menu démarrage
        this.cptIteration = 0;
        lancerMenuDemarrage();
    }
   

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
    }

    @Override
    public String getItemType() {
        return "partie";       
    }

    @Override
    public void evolve(long l) {       
        //LANCEMENT DU JEU
        //si le menu est de type démarrage et peut lancer une nouvelle partie
        if(menu != null && menu.isNouvellePartie() && menu.getTypeMenu()==TypeMenu.DEMARRAGE){
            System.out.println("LANCEMENT D'UNE PREMIERE PARTIE");
            menu.setNouvellePartie(false);
            getGame().remove(menu);
            nouvellePartie();
            XtremVaders2021.getJoueur().resetJoueur();
        }
        
        //FIN DE PARTIE
        //Si le joueur est mort
        if(!XtremVaders2021.getJoueur().estVivant()) {
            this.cptIteration ++;
            //LANCEMENT MENU FIN DE PARTIE [1 seule fois]
            if(cptIteration == 1){
                System.out.println("JOUEUR MORT");
                XtremVaders2021.getJoueur().setEstActionFreeze(true);
                lancerMenuFin();              
            }
            if(menu.getTypeMenu()==TypeMenu.FIN){
                if(menu.isNouvellePartie()){
                    System.out.println("LANCEMENT D'UNE NOUVELLE PARTIE");
                    getGame().remove(background);
                    menu.setNouvellePartie(false);
                    XtremVaders2021.getJoueur().resetJoueur();
                    nouvellePartie();
                    cptIteration = 0;
                }
            }
        }
    }
    
    /**
    * Cette méthode est appelée pour générer les items du jeu 
    * (sauf le joueur déja créé dans la classe du jeu)
    */
    public void nouvellePartie(){
        //ITEM DE JEU
        if(vagueInvaders != null){
            getGame().remove(vagueInvaders);
        }
        if(generateurBoss != null){
            getGame().remove(generateurBoss);
        }
        if(background != null){
            getGame().remove(background);
        }
        if(scoreFin != null){
            scoreFin.removeItems();
            getGame().remove(scoreFin);
        }
        this.background = new Background(getGame()); 
        this.vagueInvaders = new VagueInvaders(getGame(), 5, 3);
        this.generateurBoss = new GenerateurBoss(getGame());
        getGame().addItem(background);
        getGame().addItem(vagueInvaders);
        getGame().addItem(generateurBoss);
        
        //INTERFACE JOUEUR 
        healthBar = new HealthBar(getGame(), 0, 0);
        scoreBar = new ScoreBar(getGame(), 0, 30);
        getGame().addItem(healthBar);
        getGame().addItem(scoreBar);
        scoreBar.initItems(true);     
    }   
    
    /**
     * Methode qui fabrique un menu de démarrage
     */
    private void lancerMenuDemarrage(){
        this.menu = FabriqueMenu.FabriquerUnMenu(getGame(), TypeMenu.DEMARRAGE);
        getGame().addItem(menu);
    }
    
    /**
     * Méthode qui fabrique un menu de fin
     */
    private void lancerMenuFin(){
        scoreFin = new ScoreBar(getGame(), 385, 180);
        getGame().addItem(scoreFin);
        getGame().remove(scoreBar);
        scoreBar.removeItems();
        getGame().remove(healthBar);
        scoreFin.initItems(false);
        this.menu = FabriqueMenu.FabriquerUnMenu(getGame(), TypeMenu.FIN);
        getGame().addItem(menu);
    }
}
