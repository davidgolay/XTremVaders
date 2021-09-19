package Utilities;

import iut.Audio;

/**
 *
 * @author lr468444
 */
public class Sons extends Audio{
    
    /**
     * Constructeur
     * @param s 
     */
    public Sons(String s){
        super(s);
    }
    /**
     * Méthode static qui une fois appelé permet de jouer le son mis en paramètre 
     * @param s 
     */
    public static void play(String s){
        Thread threadSon = new Thread(){
            public void run(){
                Audio son = new Audio(s);
                son.run();
            }
        };
        threadSon.start();    
        
    }
    
}
