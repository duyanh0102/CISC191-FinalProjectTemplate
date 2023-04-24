package edu.sdccd.cisc191.template;

/**
 * Represents a student who is also an athlete.
 * Inherits from the Students class and implements the energyRecharge interface.
 */
public class athleteStudent extends Students implements energyRecharge {

    private String sport; // The sport that the athlete plays
    private int energyCharge; // The amount of energy that the athlete has

    /**
     * Constructor for creating an athlete student.
     * @param id The student ID.
     * @param lastName The student's last name.
     * @param firstName The student's first name.
     * @param sport The sport that the athlete plays.
     * @param gpa The student's GPA.
     */
    public athleteStudent(String id, String lastName, String firstName, String sport, double gpa) {
        super(Integer.parseInt(id), lastName, firstName, gpa);
        this.sport = sport;
    }

    /**
     * Gets the sport that the athlete plays.
     * @return The sport that the athlete plays.
     */
    public String getSport() {
        return sport;
    }

    /**
     * Sets the sport that the athlete plays.
     * @param sport The sport that the athlete plays.
     */
    public void setSport(String sport) {
        this.sport = sport;
    }

    /**
     * Gets the energy level of the athlete.
     * @return The energy level of the athlete.
     */
    @Override
    public int getEnergyBack() {
        return energyRecharge.energyLevel;
    }

    /**
     * Sets the energy charge of the athlete.
     * @param energyCharge The energy charge of the athlete.
     */
    public void setEnergyCharge(int energyCharge){
        this.energyCharge = energyCharge;
    }

    /**
     * Recharges the athlete's energy to 100.
     */
    public void energyUp() {
        energyCharge = 100;
    }

    /**
     * Gets the sport that the athlete plays.
     * @return The sport that the athlete plays.
     */
    @Override
    public String sport() {
        return this.sport;
    }
}
