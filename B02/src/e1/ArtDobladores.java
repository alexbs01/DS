package e1;

public class ArtDobladores extends ArtisticTeam {
    int salary = 20;

    public ArtDobladores(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
    }

    public float getSalary() {
        return salary * getWorkedHours();
    }

    @Override
    public String printSalary() {
        return getName() + " " + getSurname() + " (Dubber): " + getSalary() + " euro\n";
    }
}
