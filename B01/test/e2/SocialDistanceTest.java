package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocialDistanceTest {

    @Test
    void squareLayout() {
        char[][] exampleLayout1 = {
                {'A', '.', 'A', 'A', '.', 'A', 'A', '.', 'A', 'A'},
                {'A', 'A', 'A', 'A', 'A', 'A', 'A', '.', 'A', 'A'},
                {'A', '.', 'A', '.', 'A', '.', '.', 'A', '.', '.'},
                {'A', 'A', 'A', 'A', '.', 'A', 'A', '.', 'A', 'A'},
                {'A', '.', 'A', 'A', '.', 'A', 'A', '.', 'A', 'A'},
                {'A', '.', 'A', 'A', 'A', 'A', 'A', '.', 'A', 'A'},
                {'.', '.', 'A', '.', 'A', '.', '.', '.', '.', '.'},
                {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'A', '.', 'A', 'A', 'A', 'A', 'A', 'A', '.', 'A'},
                {'A', '.', 'A', 'A', 'A', 'A', 'A', '.', 'A', 'A'}};
        char[][] resultLayout1 = {
                {'#', '.', '#', 'A', '.', 'A', '#', '.', '#', '#'},
                {'#', 'A', 'A', 'A', '#', 'A', 'A', '.', 'A', '#'},
                {'A', '.', '#', '.', 'A', '.', '.', '#', '.', '.'},
                {'#', 'A', '#', '#', '.', '#', '#', '.', 'A', '#'},
                {'#', '.', '#', 'A', '.', 'A', 'A', '.', 'A', 'A'},
                {'#', '.', '#', 'A', '#', 'A', '#', '.', '#', '#'},
                {'.', '.', 'A', '.', 'A', '.', '.', '.', '.', '.'},
                {'#', 'A', '#', 'A', '#', '#', 'A', '#', 'A', '#'},
                {'#', '.', 'A', 'A', 'A', 'A', 'A', 'A', '.', 'A'},
                {'#', '.', '#', 'A', '#', 'A', '#', '.', '#', '#'}};
        assertArrayEquals(resultLayout1, SocialDistance.seatingPeople(exampleLayout1));

        char[][] exampleLayout2 = {
                {'A', '.', 'A', 'A', '.'},
                {'A', 'A', 'A', 'A', 'A'},
                {'A', '.', 'A', '.', 'A'},
                {'A', 'A', 'A', 'A', '.'},
                {'A', '.', 'A', 'A', '.'}};
        char[][] resultLayout2 = {
                {'#', '.', '#', 'A', '.'},
                {'#', 'A', '#', 'A', '#'},
                {'A', '.', '#', '.', '#'},
                {'#', 'A', 'A', 'A', '.'},
                {'#', '.', 'A', '#', '.'}};
        assertArrayEquals(resultLayout2, SocialDistance.seatingPeople(exampleLayout2));
    }

    @Test
    void rectangularLayout() {
        char[][] exampleLayout1 = {
                {'A', '.', 'A', '.', '.', 'A', 'A', '.', 'A', 'A'},
                {'A', 'A', 'A', '.', 'A', 'A', 'A', '.', 'A', '.'},
                {'A', 'A', 'A', '.', 'A', '.', 'A', '.', '.', '.'},
                {'A', 'A', 'A', '.', 'A', 'A', 'A', '.', 'A', '.'},
                {'A', '.', 'A', '.', '.', 'A', 'A', '.', 'A', 'A'}};
        char[][] resultLayout1 = {
                {'#', '.', '#', '.', '.', 'A', '#', '.', '#', '#'},
                {'A', 'A', 'A', '.', '#', 'A', 'A', '.', '#', '.'},
                {'#', '#', '#', '.', 'A', '.', '#', '.', '.', '.'},
                {'A', 'A', 'A', '.', '#', 'A', 'A', '.', '#', '.'},
                {'#', '.', '#', '.', '.', 'A', '#', '.', '#', '#'}};
        assertArrayEquals(resultLayout1, SocialDistance.seatingPeople(exampleLayout1));

        char[][] exampleLayout2 = {
                {'A', '.', 'A'},
                {'.', 'A', '.'},
                {'.', 'A', '.'},
                {'A', 'A', 'A'},
                {'A', '.', 'A'}};
        char[][] resultLayout2 = {
                {'#', '.', '#'},
                {'.', '#', '.'},
                {'.', 'A', '.'},
                {'#', 'A', '#'},
                {'#', '.', '#'}};
        assertArrayEquals(resultLayout2, SocialDistance.seatingPeople(exampleLayout2));
    }

    @Test
    void strangeButValidLayouts() {
        char[][] exampleLayout1 = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}};
        char[][] resultLayout1 = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}};
        assertArrayEquals(resultLayout1, SocialDistance.seatingPeople(exampleLayout1));

        char[][] exampleLayout2 = {
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'A', 'A', 'A'}};
        char[][] resultLayout2 = {
                {'#', 'A', '#'},
                {'A', 'A', 'A'},
                {'#', '#', '#'},
                {'A', 'A', 'A'},
                {'#', 'A', '#'}};
        assertArrayEquals(resultLayout2, SocialDistance.seatingPeople(exampleLayout2));

        char[][] exampleLayout3 = {
                {'A', 'A', 'A'}};
        char[][] resultLayout3 = {
                {'#', '#', '#'}};
        assertArrayEquals(resultLayout3, SocialDistance.seatingPeople(exampleLayout3));

        char[][] exampleLayout4 = {
                {'A'},
                {'A'},
                {'A'},
                {'A'},
                {'A'}};
        char[][] resultLayout4 = {
                {'#'},
                {'#'},
                {'#'},
                {'#'},
                {'#'}};
        assertArrayEquals(resultLayout4, SocialDistance.seatingPeople(exampleLayout4));

        char[][] exampleLayout5 = {
                {'A'}};
        char[][] resultLayout5 = {
                {'#'}};
        assertArrayEquals(resultLayout5, SocialDistance.seatingPeople(exampleLayout5));
    }

    @Test
    void nonValidLayouts() {
        char[][] raggedLayout = {
                {'.'},
                {'.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.'}};
        assertThrows(IllegalArgumentException.class, () -> SocialDistance.seatingPeople(raggedLayout));

        char[][] badCharLayout = {
                {'.', '.', '.'},
                {'.', 's', '.'},
                {'.', '.', '.'}};
        assertThrows(IllegalArgumentException.class, () -> SocialDistance.seatingPeople(badCharLayout));

        assertThrows(IllegalArgumentException.class, () -> SocialDistance.seatingPeople(null));
    }
}