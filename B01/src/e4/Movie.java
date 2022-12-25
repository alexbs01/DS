package e4;

public class Movie {
    String title;               // Cada vez que se haga una clase movie, tendrá 4 campos; Título, ratio, ratio máximo y medio.
    MovieRating rating;
    MovieRating maximumRating;
    double averageRating;
    int counter;

    public Movie(String title) {              // Cada uno de estos campos se rellenará al llamar al método movie.
        this.title = title;                   // El título guarda el nombre de la película.
        rating = MovieRating.NOT_RATED;       // El ratio se guarda como NOT_RATED, ya que no ha recibido ninguna puntuación aun.
        maximumRating = MovieRating.NOT_RATED;// El ratio máximo se guarda como NOT_RATED, ya que no ha recibido ninguna puntuación aun y luego queremos devolver un nombre cuando nos pregunten por él.
        averageRating = -1;                   // El ratio medio se guarda como -1 ya que no ha recibido ninguna puntuación aun y luego queremos devolver un número cuando nos pregunten por él.
        counter = 0;
    }

    public String getTitle() { //Método para devolver el nombre de la película, se guarda en title como ya comentamos antes.
        return title;
    }

    public void insertRating(MovieRating movieRating) {    // Método para insertar una crítica nueva a una película guardada anteriormente.
        if(this.maximumRating.isBetterThan(movieRating)) { // Miramos si la puntuación nueva es la mejor o no, para poder guardarla en maximumRating.
            this.maximumRating = movieRating;
        }

        this.rating = movieRating; // Almacena la puntuación actual, si es un NOT_RATED no entra en el condicional.

        if(isRated()) { // Si tiene puntuación hace la media
            if (movieRating.getNumericRating() >= 0) {
                counter++;
            }
            if(this.averageRating == -1) { // Si es la primera puntuación que se le da, simplemente se suma.
                this.averageRating = movieRating.getNumericRating();

            } else { // Si ya no es la primera crítica, hará la media de la media anterior más la puntuación actual.
                this.averageRating = this.averageRating * (counter - 1) / counter + (double)movieRating.getNumericRating() / counter;


            }
        }
    }

    private boolean isRated() { // Comprueba si la puntuación es NOT_RATED o no
        return(this.rating != MovieRating.NOT_RATED);
    }

    public MovieRating maximumRating() { // Retorna la puntuación máxima de la película.
        return this.maximumRating;
    }

    public double averageRating() { // Devuelve la media de puntuaciones recibidas.
        if(averageRating >= 0) {
            return averageRating;
        } else {
            throw new java.util.NoSuchElementException("No valid"); // Si no es una media válida lanza un error
        }

    }
}
