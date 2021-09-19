package Entites;


import Utilities.TypeMouvement;
import Jeu.XtremVaders2021;
import Objets.BonusJoueur.TypeBonus;
import Utilities.Sons;
import iut.Game;
import iut.GameItem;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe générer une vague d'invaders
 * @author Mathis Poncet
 */
public class VagueInvaders extends GameItem{
    /**
     * Nomre de lignes d'invaders
     */
    private int nbSurLigne;
    /**
     * Nombre de colonne d'invader
     */
    private int nbSurCol;
    /**
     * Permet de connaître le nombre de vagues qui se sont déjà déroulés dans le
     * jeu
     */
    private static int nbVagues;
    /**
     * Contient les invaders de la vague
     */
    private static ArrayList<Invader> invaders;
    /**
     * Temps entre 2 déplacements du groupe d'invader
     */
    private double tempsAvantBouger;
    /**
     * Délai avant le déplacement du grou^pe d'invaders
     * De base à une seconde
     */
    private double delai = 800;
    /**
     * Permet aux invaders d'aller sur un côté (Gauche ou Droit)
     */
    private TypeMouvement coteOuAller;
    /**
     * Compte le nombre de déplacement vers le bas
     */
    private int cptDeplacerBas;
    /**
     * Permet de savoir si les invaders sont entrain de se déplacer vers le bas
     */
    private boolean allerBas;
     
    
    /**
     * Liste des bonus présents dans la vague des invaders
     */
    private static ArrayList<TypeBonus> listeBonus;
    
    /**
     * Constructeur par initialisation
     * @param g le jeu 
     * @param nbSurLigne nombre de lignes d'invaders
     * @param nbSurCol nombre de colonnes d'invaders
     */
    public VagueInvaders(Game g, int nbSurLigne, int nbSurCol) {
        super(g, "", -1, -1);
        this.cptDeplacerBas = 0;
        invaders = new ArrayList<>();
        this.tempsAvantBouger = this.delai;
        this.allerBas = false;
        this.nbSurCol = nbSurCol;
        this.nbSurLigne = nbSurLigne;
        this.coteOuAller = TypeMouvement.DROITE;
        nbVagues = 0;
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }
    
    @Override
    public void draw(Graphics g) throws Exception {
    
    }
    
    @Override
    public void collideEffect(GameItem gi) {
        
    }

    @Override
    public String getItemType() {
        return "VagueInvaders";
    }
    
    @Override
    public void evolve(long dt) {
        //génération de la vague s'il n'y a pas d'invaders sur la carte
        if(invaders.isEmpty()){
            this.genererVague();
        }
        bouger(dt * Invader.getVitesseInvaders());
     }
    
    /**
     * Génére les invaders par ligne et colonne suivant le nombre de ligne voulue
     * et le nombre de colonne voulue. A chaque génération, le nombre de vague est
     * incrémenté. Les invaders générés sont ajoutés à la liste des invaders de
     * la vague
     */
    public void genererVague(){
        nbVagues++;
        Sons.play("newSounds/newWave");
        System.out.println("Nb vague = " + nbVagues);
        //initialisation de la liste des bonus de la vague
        listeBonus = new ArrayList<>();
        this.coteOuAller = TypeMouvement.DROITE; //réintialisation mouvement
        //GENERATION DES INVADERS DE LA VAGUE
        for (int l = 0; l < this.nbSurLigne; l++) {
            for (int c = 0; c < this.nbSurCol; c++) {
                Invader invader = FabriqueInvader.fabriquerUnInvader(getGame(),64 + l*64, 0 + c*64, TypeInvader.NORMAL, vitesseVague());//new Invader(getGame(),"cold_un" ,64 + l*64, 0 + c*64);
                this.invaders.add(invader);
                super.getGame().addItem(invader);
            }
        }
    }
    
    /**
     * Permet de savoir le nombre de vagues qui se sont passées
     * @return le nombre de vagues qui se sont déroulés
     */
    public static int getNbVagues() {
        return nbVagues;
    }
    
    /**
     * Permet de savoir quel côté a été touché par le groupe d'invaders contenue
     * dans la liste. Suivant le côté touché coteOuAller est modifié. Permet aussi
     * d'indiquer qu'il faut que le groupe d'invader descende
     */
    public void gestionCoteTouche(){
        allerBas = false;
        for (Invader invader : invaders) {
            if(invader.coteTouche() == TypeMouvement.DROITE && cptDeplacerBas == 0){
                allerBas = true;
                this.coteOuAller = TypeMouvement.GAUCHE;
            }else if(invader.coteTouche() == TypeMouvement.GAUCHE && cptDeplacerBas == 0){
                allerBas = true;
                this.coteOuAller = TypeMouvement.DROITE;
            }
        }
    }
    
    /**
     * Permet aux invaders de bouger vers le bas
     */
    private void bougerBas(double vitesse){
        cptDeplacerBas ++;
        for (Invader invader : invaders) {
            invader.bouger(TypeMouvement.BAS, vitesse);
        }
    }
    
    /**
     * Permet au groupe d'invader de bouger latéralement 
     */
    private void bougerLateralement(double vitesse){
        for (Invader invader : invaders) {
            invader.bouger(coteOuAller, vitesse);
        }
    }
    
    /**
     * Permet de faire bouger les invaders latéralement ou horizontalement suivant
     * si il faut aller vers le bas (connu grâce au boolean allerBas)
     * @param vitesse des invaders
     */
    public void bouger(double vitesse){
        gestionCoteTouche();
        if(allerBas){
            bougerBas(vitesse);
        }else{
            cptDeplacerBas = 0;
            bougerLateralement(vitesse);
        }  
    }
    
    public static ArrayList<Invader> getInvaders() {
        return invaders;
    }
    
    /**
     * Permet de retirer un invader de la liste
     * @param invader retire l'invader de la liste quand il est mort
     */
    public static void retirerInvader(Invader invader){
        invaders.remove(invader);
    }    

    /**
     * Augmente la vitesse en fonction du nombre de vague
     */
    public static double vitesseVague(){
        return Math.pow(-6.0, -5 * (nbVagues * nbVagues)) + 0.0107 * nbVagues + 0.1251;
    }
    
    public static ArrayList<TypeBonus> getListeBonus() {
        return listeBonus;
    }
    
    public static void ajouterBonus(TypeBonus b){
        listeBonus.add(b);
    }
}
