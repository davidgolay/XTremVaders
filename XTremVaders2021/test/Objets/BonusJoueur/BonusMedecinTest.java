/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets.BonusJoueur;

import Entites.Joueur;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.Vector;
import java.awt.Graphics;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author math7
 */
public class BonusMedecinTest {
    
    public BonusMedecinTest() {
    }

    /**
     * Test of getItemType method, of class BonusMedecin.
     */
    @Test
    public void testGetItemType() {
    }

    /**
     * Test of getTypeBonus method, of class BonusMedecin.
     */
    @Test
    public void testGetTypeBonus() {
    }

    /**
     * Test of bouger method, of class BonusMedecin.
     */
    @Test
    public void testBouger() {
    }

    /**
     * Test of lancerEffet method, of class BonusMedecin.
     */
    @Test
    public void testLancerEffet() {
        XtremVaders2021 game = new XtremVaders2021(0, 0);
        BonusMedecin bonus = new BonusMedecin(game, 0, 0);
        Joueur joueur = new Joueur(game, 0);
        joueur.setMaxPtVie(200);
        game.setJoueur(joueur);
        
        //cas où le bonus ne rend pas la vie du joueur complète
        joueur.setPtVie(165);
        //le joueur attrape le bonus
        joueur.collideEffect(bonus);
        bonus.collideEffect(joueur);
        assertEquals(185, joueur.getPtVie());

        //cas où le bonus rend la vie du joueur complète
        bonus = new BonusMedecin(game, 0, 0);
        joueur.setPtVie(180);
        //le joueur attrape le bonus
        joueur.collideEffect(bonus);
        bonus.collideEffect(joueur);
        assertEquals(200, joueur.getPtVie());
        
        //cas où le bonus peut rendre la vie du joueur supérieur à la vie maximum possible
        bonus = new BonusMedecin(game, 0, 0);
        joueur.setPtVie(188);
        //le joueur attrape le bonus
        joueur.collideEffect(bonus);
        bonus.collideEffect(joueur);
        assertEquals(200, joueur.getPtVie());
    }

    /**
     * Test of finEffet method, of class BonusMedecin.
     */
    @Test
    public void testFinEffet() {
    }
    
}
