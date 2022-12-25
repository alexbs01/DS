package e1;

public class TecDirectores extends TechTeam {
    int salary = 100;
    int experience;

    public TecDirectores(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours, int experience) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
        this.experience = experience;
    }

    public float getSalary() {
        return salary * getWorkedHours() + 1000 * experience;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String printSalary() {
        return getName() + " " + getSurname() + " (Director, " + getExperience() + " years of experience): " + getSalary() + " euro\n";
    }

}
