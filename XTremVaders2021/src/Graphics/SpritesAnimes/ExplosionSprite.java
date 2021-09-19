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
public class ExplosionSprite {
    
    public static int totalSprite(){
        return 12;
    }
    
    /**
     * Methode permettant de récupérer le bon filepath pour l'explosion
     * classe ralisée avant le systeme d'animation. donc méthode dépréciée
     * @param sprite numero du sprite que l'on désire récupérer
     * @return 
     */
    public static String getSprite(int sprite){
        String prefix = "explosion1/";
        String res = prefix;
        switch(sprite){
            case 0:  res += "explosion1_00000"; break;
            case 1:  res += "explosion1_00001"; break;
            case 2:  res += "explosion1_00002"; break;
            case 3:  res += "explosion1_00003"; break;
            case 4:  res += "explosion1_00004"; break;
            case 5:  res += "explosion1_00005"; break;
            case 6:  res += "explosion1_00006"; break;
            case 7:  res += "explosion1_00007"; break;
            case 8:  res += "explosion1_00008"; break;
            case 9:  res += "explosion1_00009"; break;
            case 10: res += "explosion1_00010"; break;
            case 11: res += "explosion1_00011"; break;
            case 12: res += "explosion1_00012"; break;
            default: res += "explosion1_00012"; break;
        }
        return res;
    }
    
}
