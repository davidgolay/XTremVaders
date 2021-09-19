package Entites;

import Utilities.Sons;
import iut.Game;
import iut.GameItem;
import java.awt.Graphics;

/**
 * Le générateur de boss ne s'affiche pas. Il sert à créer un boss suivant 
 * le nombre de vague qui se sont déroulées
 * @author Mathis Poncet
 */
public class GenerateurBoss extends GameItem{
    
    /**
     * Utile pour générer une seul fois le boss
     * dans les vagues divisibles par 4 
     */
    private boolean peutGenererBoss;
    
    /**
     * Utile pour générer une seul fois le boss
     * dans les vagues divisibles par 4 
     */
    private int ancienneVague;
    
    // frequence apparition boss
    private int frequence;
    
    private Boss boss;   
    
    public GenerateurBoss(Game game) {
        super(game, "", -1, -1);
        this.ancienneVague = 1;
        this.peutGenererBoss = false;
        this.frequence = 4;
    }

    @Override
    public void draw(Graphics grphcs) throws Exception {

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
        return "GénérateurBoss";
    }

    @Override
    public void evolve(long dt) {      
        //CONDITION QUI PERMET DE RESET LE PEUTGENERERBOSS A TRUE
        //AFIN DE GENERER UNE SEULE FOIS UN BOSS
        if(ancienneVague != VagueInvaders.getNbVagues() ){
            peutGenererBoss = true;
            ancienneVague = VagueInvaders.getNbVagues();
        }
        
        //SI LE NOMBRE DE VAGUE EST UNE VAGUE QUI GENERE UN BOSS (TOUT LES 4 vagues)
        if(peutGenererBoss && VagueInvaders.getNbVagues()%frequence == 0){        
            genererBoss();
            peutGenererBoss = false;
        }
    }
    
    /**
     * Méthode qui génère le boss et l'ajoute au jeu
     */
    private void genererBoss(){
        Sons.play("newSounds/bossSpawn");
        this.boss = new Boss(getGame(), getGame().getWidth()/2, 5, 0.15, 60);
        getGame().addItem(boss);
    }
}
