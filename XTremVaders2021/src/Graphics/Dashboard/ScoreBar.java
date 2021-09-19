/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Dashboard;

import Graphics.VFX.ItemAnime;
import Graphics.VFX.TypeAnimation;
import Jeu.XtremVaders2021;
import iut.Game;
import iut.GameItem;



/**
 *
 * @author David
 */
public class ScoreBar extends GameItem {
    
    private ItemAnime scoreBar;
    
    private ItemAnime unite;
    
    private ItemAnime dizaine;
    
    private ItemAnime centaine;
    
    private ItemAnime milier;
    
    private ItemAnime dizaineMilier;
    
    private int espaceDigit = 20;
    
    private int offset = 70;

    public ScoreBar(Game g, int _x, int _y){
        super(g, "transparent", _x, _y);
        this.unite = new ItemAnime(getGame(), "score/digit_00000", _x + 4*espaceDigit + offset, _y, TypeAnimation.SCORE, this);
        this.dizaine = new ItemAnime(getGame(), "score/digit_00000", _x + 3*espaceDigit + offset, _y, TypeAnimation.SCORE, this);
        this.centaine = new ItemAnime(getGame(), "score/digit_00000", _x + 2*espaceDigit + offset, _y, TypeAnimation.SCORE, this);
        this.milier = new ItemAnime(getGame(), "score/digit_00000", _x + 1*espaceDigit + offset, _y, TypeAnimation.SCORE, this);
        this.dizaineMilier = new ItemAnime(getGame(), "score/digit_00000", _x + offset, _y, TypeAnimation.SCORE, this);
        this.scoreBar = new ItemAnime(getGame(), "score/scoreBar", _x, _y, TypeAnimation.SCORE, this);
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
        return "scoreBar";
    }

    @Override
    public void evolve(long l) {
        unite.changeSprite(unite.getAnimation().getSprite(this.getUnite()));
        dizaine.changeSprite(dizaine.getAnimation().getSprite(this.getDizaine()));
        centaine.changeSprite(centaine.getAnimation().getSprite(this.getCentaine()));
        milier.changeSprite(milier.getAnimation().getSprite(this.getMilier()));
        dizaineMilier.changeSprite(dizaineMilier.getAnimation().getSprite(this.getDizaineMilier()));       
    }

    public int getUnite() {
        int score = XtremVaders2021.getJoueur().getScore();
        score = score%10;
        return score;
    }

    public int getDizaine() {
        int score = XtremVaders2021.getJoueur().getScore();
        score = (score/10)%10;
        return score;
    }

    public int getCentaine() {
        int score = XtremVaders2021.getJoueur().getScore();
        score = (score/100)%10;
        return score;
    }

    public int getMilier() {
        int score = XtremVaders2021.getJoueur().getScore();
        score = (score/1000)%10;
        return score;
    }
    
    public int getDizaineMilier() {
        int score = XtremVaders2021.getJoueur().getScore();
        score = score/10000;
        return score;
    }
    
    
    public void initItems(boolean afficherTexte){
        getGame().addItem(unite);
        getGame().addItem(dizaine);
        getGame().addItem(centaine);
        getGame().addItem(milier);
        getGame().addItem(dizaineMilier);
        if(afficherTexte){
            getGame().addItem(scoreBar);
        }     
    }
    
    public void removeItems(){
        getGame().remove(unite);
        getGame().remove(dizaine);
        getGame().remove(centaine);
        getGame().remove(milier);
        getGame().remove(dizaineMilier);
        getGame().remove(scoreBar);    
    }  
}
