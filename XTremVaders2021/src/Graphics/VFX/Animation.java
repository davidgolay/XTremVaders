package Graphics.VFX;

/**
 * Cette classe est utile à la classe ItemAnime.
 * Elle permet de récupérer des informations relatives à une animation.
 * @author David Golay
 */
public class Animation {
    private String relativeName; //nom du fichier pour trouver une image de l'animation
    private int nombreFrame;    // nombre de frame de l'animation
    private TypeAnimation type; //type de l'animation
    private int lifeSpan;       //durée de vie de l'animation
    private static int animationSpeed = 6;
    
    /**
     * Constructeur de l'animation
     * @param type type de l'animation que l'on souhaite 
     */
    public Animation(TypeAnimation type){
        this.type = type;
        updateValues(); //initialisation des attributs selon le type d'animation
    }
    
    /**
     * Cette méthode permet de générer le bon nom de fichier à recréer 
     * pour pouvoir changer de sprite en ayant un String correct. Cela 
     * permet d'éviter de créer des switch case pour chaque frame
     * @param prefix
     * @param numeroSprite
     * @return 
     */
    public String spriteFile(String prefix, int numeroSprite){
        String res = prefix + normaliseFile(numeroSprite);
        return res;
    }
    
    /**
     * Cette méthode normalise le String pour avoir un fichier correct
     * par exemple, si le nombre passé en paramètre est 137, on aura 00137 en sortie
     * Exemples:
     * 8   = 00008
     * 17  = 00017
     * 136 = 00136
     * @param nombre correspond à l'image de l'animation qu'on souhaite 
     * (ex: 24 = 24ème image de l'animation)
     * @return 
     */
    public String normaliseFile(int nombre){
        String res = null;
        if(nombre>=1000){
            res = "0" + nombre;
        } 
        else if(nombre>=100 && nombre < 1000){
            res = "00";
        }
        else if(nombre>=10 && nombre < 100 && nombre < 1000){
            res = "000" + nombre;
        }
        else if(nombre<10){
            res = "0000" + nombre;
        }
        //System.out.println("SPRITE généré: " + res);
        return res;
    }
    
    /**
     * Cette méthode est utile pour récupérer un nom de fichier de sprite 
     * avec un bon numéro d'image en utilisant la methode normaliseFile
     * (ex: image_00001 ou image_00002)
     * @param sprite
     * @return 
     */
    public String getSprite(int sprite){
        String res = spriteFile(relativeName, sprite);
        if(sprite>nombreFrame){
            res = relativeName + normaliseFile(nombreFrame);
        }
        res += optimizedSuffix();
        return res;
    }
    
    /**
     * Cette methode est utile pour créer un nom de fichier qui a été optimisé
     * par un compresseur d'image PNG. ce compresseur retourne des fichiers PNG 
     * renommés avec un _optimized à la fin du nom de fichier
     * @return 
     */
    private String optimizedSuffix(){
        String res = "";
        String optimized = "_optimized";
        switch(type){
            case EXPLOSION1        : res += optimized; break;
            case MENU_PRINCIPAL    : res += optimized; break;
            case ALIEN_FATHER      : res += optimized; break;
            case ALIEN_BLOB        : res += optimized; break;
            case BONUS_UNLOCK      : res += optimized; break;
            case ASTEROID_EXPLODE  : res += optimized; break;
            case ALIEN_SHARPY      : res += optimized; break;
            default                : res = "";
        }
        return res;     
    }
    
    /**
     * Cette méthode défini les valeurs et le nom des fichiers à aller chercher
     * selon le type d'animation
     */
    private void updateValues(){
        switch(type){
            case EXPLOSION1: 
                relativeName = "explosion1/explosion1_";
                nombreFrame = 12 ;
                lifeSpan = 240; 
                break;
        
            case ALIEN_BLOB: 
                relativeName = "invaders/alien_blob/alien_blob_";
                nombreFrame = 100 ;
                lifeSpan = 240;
                break;           

             case ALIEN_SPIDER: 
                relativeName = "invaders/invaderExplosif/invaderExplosif_";
                nombreFrame = 100 ;
                lifeSpan = 240;
                break;

             case ALIEN_SHARPY: 
                relativeName = "invaders/alien_sharpy/alien_sharpy_";
                nombreFrame = 99 ;
                lifeSpan = 240;
                break;
                
            case ALIEN_TEETH: 
                relativeName = "invaders/alien_teeth/alien_teeth_";
                nombreFrame = 99 ;
                lifeSpan = 240;
                break;
            
            case ALIEN_FATHER: 
                relativeName = "invaders/invader_father/compressed/invader_father_";
                nombreFrame = 50 ;
                lifeSpan = 500;
                break; 

            case ALIEN_FATHER_EXPLODE: 
                relativeName = "invaders/invader_father/explode/explode_";
                nombreFrame = 50 ;
                lifeSpan = 500;
                break;
                
            case SPACESHIP3_NORMAL: 
                relativeName = "joueur/playerShip3_normal/playerShip3_normal_";
                nombreFrame = 49 ; 
                lifeSpan = 240;
                break;
                
            case SPACESHIP3_DEAD: 
                relativeName = "joueur/playerShip3_dead/playerShip3_dead_";
                nombreFrame = 24; 
                lifeSpan = 12;
                break;
            
            case MISSILE_BOSS: 
                relativeName = "missile/missile_boss/missile_boss_";
                nombreFrame = 88 ; 
                lifeSpan = 240;
                break;

                
            case SPACESHIP3_SHOOT: 
                relativeName = "joueur/playerShip3/playerShip3_shoot_";
                nombreFrame = 49 ; 
                lifeSpan = 500;
                break; 
                
            case SPACESHIP3_COLLISION: 
                relativeName = "joueur/playerShip3_collision/playerShip3_collision_";
                nombreFrame = 49 ; 
                lifeSpan = 500;
                break;
                
             case BACKGROUND1: 
                relativeName = "background/background_1/background_1_";
                nombreFrame = 225 ; 
                lifeSpan = 20000;
                break;

            case ASTEROID_FULL:
                relativeName = "asteroid/full/full_";
                nombreFrame = 9 ; 
                lifeSpan = 20000;
                break; 

                
            case ASTEROID_BROKEN_1:
                relativeName = "asteroid/broken/broken_";
                nombreFrame = 24; 
                lifeSpan = 20000;
                break;
                
            case ASTEROID_EXPLODE:
                relativeName = "asteroid/explode/asteroid_1_explode_";
                nombreFrame = 49; 
                lifeSpan = 1500;
                break;
                
            case NUKE_LAUNCH:
                relativeName = "missile/nuke/nuke_launch_";
                nombreFrame = 25; 
                lifeSpan = 2000;
                break;
                
            case NUKE_EXPLODE:
                relativeName = "missile/nuke/nuke_explode/nuke_explode_";
                nombreFrame = 7; 
                lifeSpan = 2000;
                break;
                
            case MISSILE_IMPACT:
                relativeName = "missile/missile_impact/missile_impact_";
                nombreFrame = 5; 
                lifeSpan = 400;
                break;
                
            case BONUS_UNLOCK:
                relativeName = "bonus/bonus_unlock_";
                nombreFrame = 32; 
                lifeSpan = 1000;
                break;
            case MENU_PRINCIPAL:
                relativeName = "menus/menu_principal_animation_compressed/menu_principal_animation_";
                nombreFrame = 50; 
                lifeSpan = 2000;
                break;
                
            case BARRE_VIE:
                relativeName = "dashboard/barreVie/barreVie_";
                nombreFrame = 24; 
                lifeSpan = 0;
                break;    
                
            case SCORE:
                relativeName = "score/digit_";
                nombreFrame = 10; 
                lifeSpan = 0;
                break; 
                
        }
    }

    public int getNombreFrame() {
        return nombreFrame;
    }

    /**
     * Cette méthode défini le type d'animation et update toutes les valeurs 
     * de la classe en fonction de celui ci
     * @param type 
     */
    public void setType(TypeAnimation type) {       
        this.type = type;
        updateValues();
    }

    public TypeAnimation getType() {
        return type;
    }

    /**
     * Methode qui donne le chemin des sprites de l'animation
     * @return Chaine de caractères chemin d'acces aux sprites de l'animation
     */
    public String getRelativeName() {
        return relativeName;
    }    

    /**
     * @return durée de vie en int de l'animation
     * Utile dans certains cas seulement
     */
    public int getLifeSpan() {
        return lifeSpan;
    }   

    public static int getAnimationSpeed() {
        return animationSpeed;
    }
    
    /**
     * cette méthode permet de générer un animation random pour la fabrique d'invader
     * @return retourne une animation d'invader normal
     */
    public static TypeAnimation generateRandomInvaderNormal(){
        int nbAnim = 4;
        int randomAnim = Utilities.Utilite.randomBetweenRange(0, nbAnim);
        TypeAnimation type = TypeAnimation.ALIEN_BLOB;
        switch (randomAnim) {
            case 0  : type = TypeAnimation.ALIEN_BLOB; break; 
            case 1  : type = TypeAnimation.ALIEN_SPIDER; break;
            case 2  : type = TypeAnimation.ALIEN_TEETH; break;
            case 3  : type = TypeAnimation.ALIEN_SHARPY; break;
            default : type = TypeAnimation.ALIEN_SHARPY; break;
        }
        return type;
    }
    
    
}
