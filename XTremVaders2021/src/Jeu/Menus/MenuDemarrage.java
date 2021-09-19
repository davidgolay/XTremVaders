/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu.Menus;

import Entites.GenerateurBoss;
import Entites.VagueInvaders;
import Graphics.Background;
import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author David
 */
public class MenuDemarrage extends Menu{   
    
    private MenuAnime menuAnime;

    public MenuDemarrage(Game g, double _x, double _y) {
        super(g, _x, _y);
        this.menuAnime = new MenuAnime(getGame(), 0, 0);
        getGame().addItem(menuAnime);
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
        return "menu_principal";
    }

    @Override
    public void evolve(long l) {
        setCptIteration(getCptIteration()+1);      
        normaliseChoice();
        updateSpriteMenu();
        if(isEnterPressed()) {
            choiceMade();           
            if(isNouvellePartie() && getCptIteration() == 0){
                setEnterPressed(false);
                getGame().remove(menuAnime);
                getGame().remove(this);               
            }
            else if(isSousMenuActif() && getCptIteration() == 0){  
                setEnterPressed(false);
                sceneSecondaire();
            }
        }      
    }
    
    /**
     * cette méthode est appelée dans evolve seulement après que ENTER a été pressé,
     * elle défini les booléans des menus ou actions qui seront ensuite effectuées dans
     * evolve. Pour cela, elle reinitialise cptItération à zero, pour que les
     * actions correspondantes (booleans activé) soitent effectués qu'une seule fois
     * Grace au test de la condition cptIteration = 0 dans evolve
     */
    @Override
    public void choiceMade() {
        switch(getChoice()) {
            
            case 0 : // cas qui declenchera une nouvelle partie
                setCptIteration(0);
                setSousMenuActif(false);
                setNouvellePartie(true);
            break;
            case 1 : // cas qui delenchera le menu de commande
                setCptIteration(0);
                setSousMenuActif(true); 
                setMenuPrincipalActif(false);
            break;
            case 2 : // cas qui fermera l'application
                System.exit(0);
            break;
        }      
    }
      

    /**
     * Cette méthode est appelé quand on est dans le sous menu de commandes
     * elle permet de changer le sprite de menu pour afficher l'image qui 
     * contient toutes les commandes du jeu
     */
    @Override
    public void sceneSecondaire(){
        this.changeSprite("menus/menu_commandes/menu_commandes");
    }
    
    /**
     * Cette méthode est appelé lors du lancement du jeu (par défaut) 
     * et lorsqu'on quitte le sous menu de commandes.
     * Elle permet de'afficher le bon sprite de 
     * du choix que l'on souhaite faire dans le menu principale
     */
    @Override
    public void scenePrincipale(){
        String relativeName = "menus/menu_principal/menu_principal_";
        switch(getChoice()) {
            case 0 : this.changeSprite(relativeName + "00000"); break;
            case 1 : this.changeSprite(relativeName + "00001"); break;
            case 2 : this.changeSprite(relativeName + "00002"); break;
            default : this.changeSprite(relativeName + "00000"); break;    
        }
    }

    @Override
    public TypeMenu getTypeMenu() {
        return TypeMenu.DEMARRAGE;
    }
}
