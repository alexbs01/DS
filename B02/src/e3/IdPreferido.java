package e3;

public enum IdPreferido {
    EMAIL(0),
    TELEPHONE(1),
    NOMBRE_USUARIO(2);

    final int idPreferido;

    IdPreferido(int idPreferido) {
        this.idPreferido = idPreferido;
    }

}
