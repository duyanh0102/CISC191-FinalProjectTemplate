package edu.sdccd.cisc191.template;

/**
 * A subclass of Students that implements energy recharge.
 */
public class normalStudent extends Students implements energyRecharge {

    private int energyCharge;

    /**
     * Creates a new normalStudent with the given information.
     * @param id The student ID.
     * @param lastName The last name of the student.
     * @param firstName The first name of the student.
     * @param gpa The student's grade point average.
     */
    public normalStudent(String id, String lastName, String firstName, double gpa) {
        super(Integer.parseInt(id), lastName, firstName, gpa);
    }

    /**
     * Returns the sport the student plays (which is none in this case).
     * @return The string "none".
     */
    @Override
    public String sport() {
        return "none";
    }

    /**
     * Returns the energy level of the student.
     * @return The energy level of the student.
     */
    public int getEnergyBack() {
        return energyRecharge.energyLevel;
    }

    /**
     * Sets the energy charge of the student to the given value.
     * @param energyCharge The new energy charge.
     */
    public void setEnergyCharge(int energyCharge){
        this.energyCharge = energyCharge;
    }

    /**
     * Increases the energy level of the student to the maximum (100).
     */
    public void energyUp() {
        energyCharge = 100;
    }
}
