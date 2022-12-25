package e1;

public class ArtEspecialistas extends ArtisticTeam {
    int salary = 40;
    boolean dangerous;

    public ArtEspecialistas(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours, boolean dangerous) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
        this.dangerous = dangerous;
    }

    public float getSalary() {
        if(dangerous) {
            return salary * getWorkedHours() + 1000;
        } else {
            return salary * getWorkedHours();
        }
    }

    public String printSalary() {
        if(dangerous) {
           return getName() + " " + getSurname() + " (Stunt performer with extra for danger): " + getSalary() + " euro\n";
        } else {
            return getName() + " " + getSurname() + " (Stunt performer without extra for danger): " + getSalary() + " euro\n";

        }
    }

}
