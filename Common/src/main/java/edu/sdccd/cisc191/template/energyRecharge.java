package edu.sdccd.cisc191.template;

/**
 * The energyRecharge interface represents an object that has the ability to recharge
 * its energy level. Classes that implement this interface must define methods for getting
 * and setting the energy level, as well as a method for recharging the energy level to its
 * maximum value.
 */
public interface energyRecharge {

    /** The maximum energy level. */
    int energyLevel = 100;

    /**
     * Returns the current energy level.
     *
     * @return the current energy level
     */
    public int getEnergyBack();

    /**
     * Sets the energy level to its maximum value.
     */
    public void energyUp();
}
