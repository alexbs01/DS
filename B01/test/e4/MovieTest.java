package e4;

import e1.DateUtilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private static final Movie m1 = new Movie("Matrix");
    private static final Movie m2 = new Movie("Star Wars: Episode IX - The Rise of Skywalker");
    private static final Movie m3 = new Movie("Elvis");
    private static final Movie m4 = new Movie("Fast & Furious X");



    @BeforeAll
    static void insertMovieRatings() {
        m1.insertRating(MovieRating.NOT_RATED);
        m1.insertRating(MovieRating.MASTERPIECE);
        m1.insertRating(MovieRating.GOOD);
        m1.insertRating(MovieRating.NOT_RATED);
        m1.insertRating(MovieRating.EXCELLENT);

        m2.insertRating(MovieRating.AWFUL);
        m2.insertRating(MovieRating.BAD);
        m2.insertRating(MovieRating.MEDIOCRE);

        m3.insertRating(MovieRating.NOT_RATED);
        m3.insertRating(MovieRating.NOT_RATED);
    }

    @Test
    void getTitle() {
        assertEquals("Matrix", m1.getTitle());
        assertEquals("Star Wars: Episode IX - The Rise of Skywalker", m2.getTitle());
        assertEquals("Elvis", m3.getTitle());
    }

    @Test
    void maximumRating() {
        assertEquals(MovieRating.MASTERPIECE, m1.maximumRating());
        assertEquals(MovieRating.MEDIOCRE, m2.maximumRating());
        assertEquals(MovieRating.NOT_RATED, m3.maximumRating());
        assertEquals(MovieRating.NOT_RATED, m4.maximumRating());
     }

    @Test
    void averageRating() {
        assertEquals(8.0, m1.averageRating(), 0.0001);
        assertEquals(2.0, m2.averageRating(), 0.0001);
        assertThrows(java.util.NoSuchElementException.class, m3::averageRating);
        assertThrows(java.util.NoSuchElementException.class, m4::averageRating);
    }
}