package e1;

public abstract class HumanTeam {
    private String name;
    private String surname;
    private String dni;
    private String phoneNumber;
    private String nationality;
    private int workedHours;

    public HumanTeam(String name, String surname, String dni, String phoneNumber, String nationality, int workedHours) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.workedHours = workedHours;
    }

    /**
     * Retorna el salario del trabajador en función de puesto, las horas
     * y otras características del empleo
     * @return Salario del trabajador
     */
    public abstract float getSalary();

    /**
     * Retorna el salario
     * @return Salario con formato específico para cada puesto de trabajo
     */
    public abstract String printSalary();

    public int getWorkedHours() {
        return workedHours;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}

