package Graphics.SpritesAnimes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class ShieldSprite {
    
    public static int totalSprite(){
        return 6;
    }
    
    /**
     * Methode permettant de récupérer le bon filepath pour l'explosion
     * classe ralisée avant le systeme d'animation. donc méthode dépréciée
     * @param sprite numero du sprite que l'on désire récupérer
     * @return 
     */
    public static String getSprite(int sprite){
        String prefix = "shield/shieldBasic_";
        String res = "";       
        switch(sprite){
            case 0: res = prefix + "00000"; break;
            case 1: res = prefix + "00001"; break;
            case 2: res = prefix + "00002"; break;
            case 3: res = prefix + "00003"; break;
            case 4: res = prefix + "00004"; break;
            case 5: res = prefix + "00005"; break;
            default: res = prefix + "00000"; break;
        }
        return res;
    }
    
}
