package e1;

public class ArtInterpretes extends ArtisticTeam {
    int salary = 200;
    enum roles {principal, secundario, extra}
    roles rol;

    public ArtInterpretes(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours, roles rol) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
        this.rol = rol;
    }

    public float getSalary() {
        if(rol == roles.principal) {
            return salary * getWorkedHours() * 3;
        } else {
            return salary * getWorkedHours();
        }
    }

    public String printSalary() {
        if(rol == roles.principal) {
            return getName() + " " + getSurname() + " (Actor protagonist): " + getSalary() + " euro\n";

        } else if(rol == roles.secundario) {
            return getName() + " " + getSurname() + " (Actor secondary): " + getSalary() + " euro\n";

        } else {
            return getName() + " " + getSurname() + " (Extra): " + getSalary() + " euro\n";
        }
    }

}
