package e1;

public class TecMusicos extends TechTeam {
    int salary = 60;

    public TecMusicos(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
    }

    public float getSalary() {
        return salary * getWorkedHours();
    }

    public String printSalary() {
        return getName() + " " + getSurname() + " (Musician): " + getSalary() + " euro\n";
    }

}
