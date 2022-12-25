package e1;

import java.util.List;

public class Pelicula {
    private final String nombre;
    private final int recaudacion;
    private final List<ArtisticTeam> trabajadoresArtistic;
    private final List<TechTeam> trabajadoresTechnical;

    public Pelicula(String nombre, int recaudacion, List<ArtisticTeam> trabajadoresArtistic, List<TechTeam> trabajadoresTechnical) {
        this.nombre = nombre;
        this.recaudacion = recaudacion;
        this.trabajadoresArtistic = trabajadoresArtistic;
        this.trabajadoresTechnical = trabajadoresTechnical;
    }

    public int getRecaudacion() {
        return recaudacion;
    }

    /**
     * Retorna los salarios de una película con formato específico
     * @return name surname (job, [modifiers]): salary
     * */
    public String printSalaries() {
        String listadoSalary;
        float totalPayroll = 0;
        StringBuilder listadoSalaryBuilder = new StringBuilder();

        // Listamos primero los trabajadores del equipo técnico
        for(TechTeam trabajadores : this.trabajadoresTechnical) {
            /* Formamos el listado de los salarios concatenando la información de cada trabajador
               con el del siguiente trabajador*/
            listadoSalaryBuilder.append(trabajadores.printSalary());
            totalPayroll = totalPayroll + trabajadores.getSalary();
        }

        // Y luego listamos los del equipo artístico
        StringBuilder listadoSalaryBuilder1 = new StringBuilder(listadoSalaryBuilder.toString());
        for(ArtisticTeam trabajadores : this.trabajadoresArtistic) {
            listadoSalaryBuilder1.append(trabajadores.printSalary());
            totalPayroll = totalPayroll + trabajadores.getSalary();
        }

        // Convertimos el tipo StringBuilder en tipo string para poder hacer un return de la lista de trabajadores
        listadoSalary = listadoSalaryBuilder1.toString();

        listadoSalary = listadoSalary + "The total payroll for " + this.nombre + " is " + totalPayroll + " euro";

        return listadoSalary;
    }

    /**
     * Retorna datos y derechos de autor del equipo técnico
     * @return name surname (job): royalties
     * */
    public String printRoyalties() {
        String listadoRoyalties;
        double totalRoyalties = 0;
        StringBuilder listadoRoyaltiesBuilder = new StringBuilder();

        for(TechTeam trabajadores : this.trabajadoresTechnical) {
            if(trabajadores instanceof TecProductores) {
                // Formamos el listado de los royalties concatenando la información de cada trabajador con el del siguiente trabajador
                listadoRoyaltiesBuilder.append(trabajadores.getName()).append(" ").append(trabajadores.getSurname()).append(" (Producer): ").append(recaudacion * 0.02).append(" euro\n");
                totalRoyalties =  totalRoyalties + recaudacion * 0.02;

            } else if(trabajadores instanceof TecGuionistas) {
                listadoRoyaltiesBuilder.append(trabajadores.getName()).append(" ").append(trabajadores.getSurname()).append(" (Screenwriter): ").append(recaudacion * 0.05).append(" euro\n");
                totalRoyalties =  totalRoyalties + recaudacion * 0.05;

            } else if(trabajadores instanceof TecDirectores) {
                listadoRoyaltiesBuilder.append(trabajadores.getName()).append(" ").append(trabajadores.getSurname()).append(" (Director): ").append(recaudacion * 0.05).append(" euro\n");
                totalRoyalties =  totalRoyalties + recaudacion * 0.05;

            } else if(trabajadores instanceof TecMusicos) {
                listadoRoyaltiesBuilder.append(trabajadores.getName()).append(" ").append(trabajadores.getSurname()).append(" (Musician): ").append(recaudacion * 0.04).append(" euro\n");
                totalRoyalties =  totalRoyalties + recaudacion * 0.04;

            }

        }

        listadoRoyalties = listadoRoyaltiesBuilder.toString();

        listadoRoyalties = listadoRoyalties + "The total royalties for " + this.nombre + " is " + totalRoyalties + " euro";

        return listadoRoyalties;
    }
}










