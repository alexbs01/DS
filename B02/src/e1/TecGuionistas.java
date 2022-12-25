package e1;

public class TecGuionistas extends TechTeam {
    int salary = 70;
    boolean original;

    public TecGuionistas(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours, boolean original) {
        super(name, surname, dni, phoneNumber, nationality, workedHours);
        this.original = original;
    }

    public float getSalary() {
        if(original) {
            return salary * getWorkedHours() + 4000;

        } else {
            return salary * getWorkedHours();

        }
    }

    @Override
    public String printSalary() {
        if(original) {
            return getName() + " " + getSurname() + " (Screenwriter, original screenplay): " + getSalary() + " euro\n";
        } else {
            return getName() + " " + getSurname() + " (Screenwriter, non original screenplay): " + getSalary() + " euro\n";

        }
    }

}

