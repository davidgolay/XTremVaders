package Utilities;

/**
 * Rang de probabilité
 * @author Mathis Poncet
 */
public class RangProba {
    /**
     * Rang minimum du poids d'une branche
     */
    private double min;
    /**
     * Rang maximum du poids d'une branche
     */
    private double max;

    public RangProba(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }
}
