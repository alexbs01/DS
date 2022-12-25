package e1;

public class TecProductores extends TechTeam {
    int salary = 90;

    public TecProductores(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
    }

    public float getSalary() {
        return salary * getWorkedHours();
    }

    public String printSalary() {
        return getName() + " " + getSurname() + " (Producer): " + getSalary() + " euro\n";
    }

}
