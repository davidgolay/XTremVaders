package Utilities;

import java.util.Random;

/**
 *
 * @author David
 */
public class Utilite {

    /**
     * Méthode qui détermine un nombre aléatoire entre 2 entiers positifs
     */
    public static int randomBetweenRange(int min, int max) {
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
    
}
