package e4;

public enum MovieRating { // Asignamos a cada enumerado de MovieRating un valor numérico
    NOT_RATED(-1),
    AWFUL(0),
    BAD(2),
    MEDIOCRE(4),
    GOOD(6),
    EXCELLENT(8),
    MASTERPIECE(10);

    final int levelOfRating;

    MovieRating(int levelOfRating) { // Creamos el constructor de MovieRating
        this.levelOfRating = levelOfRating;
    }

    int getNumericRating() { // Retornamos el valor numérico del enumerado
        return this.levelOfRating;
    }

    public boolean isBetterThan(MovieRating rate) { // Comprueba si la puntuación de una peli es mejor que la de otra
        return this.levelOfRating < rate.levelOfRating;
    }
}
