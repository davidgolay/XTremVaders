package Graphics.VFX;

import iut.Game;
import iut.GameItem;

/**
 *  Cette classe est utile pour gérer des animations d'item
 * @author David
 */
public class ItemAnime extends GameItem{
    /**
     * temps de vie de l'itemAnime
     */
    private long lifeSpend;
    
    /**
     * Animation attitrée de l'ItemAnim
     * On peut la changer
     * Dans le cas du joueur qui se fait touché, qui meurt, ou qui tire par exemple
     */
    private Animation animation; 
    
    /**
     * item parent qui génère l'itemAnime
     * attribut utile pour supprimer le gameItem parent qui a généré l'animation
     */
    private GameItem item; 
    
    /**
     * si l'animation a été jouée en entière
     */
    private boolean estFinie; 
    
    //CORDONN2ES DE L'ITEM ANIME
    private int cordX; 
    private int cordY;
    
    public ItemAnime(Game g, String name, int _x, int _y, TypeAnimation type, GameItem item) {       
        super(g, name, _x, _y);
        this.cordX = _x;
        this.cordY = _y;
        this.lifeSpend = 0;
        this.animation = new Animation(type);
        this.item = item;
        boolean estFini = false;
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
        return "itemAnime";
    }
  
    @Override
    public void evolve(long l) {
              
    }
    
    /**
     * Cette méthode permet de définir l'entier numéro de sprite 
     * @param totalSprite
     * @return 
     */
    public int selectSprite(int totalSprite){
        int ratio = (int)animation.getLifeSpan()/totalSprite;
        int sprite = ((int)lifeSpend/ratio);    
        return sprite;
    }
    
    public void updateSprite(int numSprite){
        String sprite = getAnimation().getSprite(numSprite);
        item.changeSprite(sprite);
    }
    
    /**
     * Cette méthode permet de jouer une animation en entiere, puis une fois
     * toutes les frames consommées, l'itemAnime est remove du jeu
     * @param l 
     */
    public void playAnimation(long l){         
        lifeSpend += l;
        updateSprite(selectSprite(getAnimation().getNombreFrame() ) );   
        if(lifeSpend >= animation.getLifeSpan()) {
            getGame().remove(item);
            this.estFinie = true;
        }
    }  
    
    /**
     * Cette méthode joue une seule fois l'animation, puis une fois
     * tout les sprites de l'animation consommé, l'item affiche le dernier 
     * sprite de l'animation
     * @param l 
     */
    public void playOnce(long l){       
        lifeSpend += l;
        updateSprite(selectSprite(getAnimation().getNombreFrame() ) );
        if(lifeSpend >= animation.getNombreFrame()) {
            item.changeSprite(getAnimation().spriteFile(getAnimation().getRelativeName(),getAnimation().getNombreFrame()));
            this.estFinie = true;
        }
    }
    
    /**
     * REMPLACEE PAR playAnimation(long l)
     * Cette méthode permet de jouer une animation en entiere, puis une fois
     * toutes les frames consommées, l'itemAnime est remove du jeu
     * @param l 
     */
    @Deprecated
    public void playOnceAndDelete(long l){  
        lifeSpend += l;
        updateSprite(selectSprite(getAnimation().getNombreFrame() ) );
        if(lifeSpend >= animation.getNombreFrame()) {
            item.changeSprite(getAnimation().spriteFile(getAnimation().getRelativeName(),getAnimation().getNombreFrame()));
            this.estFinie = true;
            getGame().remove(item);
        }
    }
    
    /**
     * Cette méthode joue en boucle l'animation donnée
     * @param l
     * @param vitesse 
     */   
    public void loopAnimation(long l, long vitesse){
        //si la durée de vie de l'animation est dépassée
         if(lifeSpend >= animation.getLifeSpan()) {
             this.estFinie = true;
         }
        lifeSpend += l*vitesse/10;
        int nbFrame = 40;
        int frameStep = (int)lifeSpend/nbFrame;
        int mod = frameStep % getAnimation().getNombreFrame();
        item.changeSprite(getAnimation().getSprite(mod));
    }  
    
    public void offsetPosition(int x, int y){
        moveXY(x, y);
    }      
    
    public void centerSprite(){
        int posX;
        int posY;
        posX = -getWidth()/2;
        posY = -getHeight()/2;
        moveXY(posX, posY);
    }
    
    public int centerSpriteX(){
        int posX;
        posX = -getWidth()/2;
        return posX;
    }
    
    public int centerSpriteY(){
        int posY;
        posY = -getHeight()/2;
        return posY;
    }
    
    public int relativeX(){
        int posX;
        posX = cordX - getLeft();
        return posX;
    }
    
    public int relativeY(){
        int posY;
        posY = cordY - getTop();
        return posY;
    }
     
    /**
     * @return le typeAnimation attitré à cet itemAnime
     */
    public Animation getAnimation() {
        return animation;
    }

    public void setAnimationType(TypeAnimation Typeanimation) {
        this.estFinie = false;
        this.animation.setType(Typeanimation);
        setLifeSpend(0);
    }
    
    public TypeAnimation getAnimationType() {
        return this.animation.getType();
    }
    

    /**
     * défini le temps de vie de l'itemAnime
     * Utile pour relancer une animation à son début
     * Ou encore quand on change le typeAnimation de l'itemAnime
     * en un autre type d'animation et qu'on veut 
     * relancer l'animation du début
     * @param lifeSpend 
     */
    public void setLifeSpend(long lifeSpend) {
        this.lifeSpend = lifeSpend;
        this.estFinie = false;
    }
    
    /**
     * Méthode utile pour reset l'animation de l'itemAnime
     * @param type 
     */
    public void resetAnimation(TypeAnimation type){
        animation.setType(type);
        lifeSpend = 0;
        this.estFinie = false;
    }

    /**
     * Méthode utile pour savoir si tout les sprites de l'animation 
     * ont été consommés
     * @return 
     */
    public boolean AnimationFinie() {
        return estFinie;
    }
    
    
    
}