import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    public static Movie[] movies = new Movie[0];

    private static void InputNamesAndRatingsForThreeMovies() {
        movies = new Movie[3];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter movie name: ");
            var name = in.nextLine();
            System.out.println("Enter movie rating: ");
            var rating = in.nextDouble();
            in.nextLine();
            movies[i] = new Movie(name, rating);
        }
        System.out.println("successfully input movies");
    }

    private static void PrintMovies() {
        for (int i = 0; i < movies.length; i++) {
            System.out.println(String.format("Movie %d name: %s rating: %s", i + 1, movies[i].getName(), movies[i].getRating()));
        }
    }

    private static void InputMoreMovies() {
        Scanner in = new Scanner(System.in);
        System.out.println("how many additional movies do you want to add? ");
        int addition = in.nextInt();
        in.nextLine();
        int oldSize = movies.length;
        Movie[] newMovieList = new Movie[oldSize + addition];
        System.arraycopy(movies, 0, newMovieList, 0, oldSize);

        for (int i = 0; i < addition; i++) {
            System.out.println("Enter movie name: ");
            var name = in.nextLine();
            System.out.println("Enter movie rating: ");
            var rating = in.nextDouble();
            in.nextLine();
            newMovieList[oldSize + i] = new Movie(name, rating);
        }
        movies = newMovieList;
        System.out.println("successfully input movies");
    }

    private static void PrintStatistics() {
        double sum = 0;
        double maxRating = Double.MIN_VALUE;
        double minRating = Double.MAX_VALUE;

        for (Movie movie : movies) {
            sum += movie.getRating();
            if (movie.getRating() > maxRating) {
                maxRating = movie.getRating();
            }
            if (movie.getRating() < minRating) {
                minRating = movie.getRating();
            }
        }

        double average = sum / movies.length;
        System.out.println("Average Rating: " + average);
        System.out.println("Max Rating: " + maxRating);
        System.out.println("Min Rating: " + minRating);
    }

    private static void searchMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the movie you are searching for:");
        String searchTitle = scanner.nextLine();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getName().equalsIgnoreCase(searchTitle)) {
                System.out.println("Movie: " + movie.getName() + ", Rating: " + movie.getRating());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Movie not found.");
        }
    }

    private static void updateMovieRating() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the movie whose rating you want to update:");
        String searchTitle = scanner.nextLine();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getName().equalsIgnoreCase(searchTitle)) {
                System.out.println("Enter the new rating for \"" + movie.getName() + "\":");
                double newRating = scanner.nextDouble();
                movie.setRating(newRating);

                System.out.println("The rating for \"" + movie.getName() + "\" has been updated to: " + movie.getRating());
                found = true;
                break;
            }
        }


        if (!found) {
            System.out.println("Movie not found.");
        }
    }

    private static void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the movie you want to delete:");
        String searchTitle = scanner.nextLine();

        int indexToDelete = -1;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].getName().equalsIgnoreCase(searchTitle)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("Movie not found.");
        } else {

            Movie[] updatedMovies = new Movie[movies.length - 1];
            int currentIndex = 0;

            for (int i = 0; i < movies.length; i++) {
                if (i != indexToDelete) {
                    updatedMovies[currentIndex++] = movies[i];
                }
            }

            System.out.println("Movie \"" + searchTitle + "\" has been deleted.");


            System.out.println("Updated Movie List:");
            for (Movie movie : updatedMovies) {
                System.out.println(movie.getName());
            }


            movies = updatedMovies;
        }

    }

    private static void printSortedMovies() {
        int n = movies.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (movies[j].getRating() > movies[j + 1].getRating()) {
                    Movie temp = movies[j];
                    movies[j] = movies[j + 1];
                    movies[j + 1] = temp;
                }
            }
        }

        // Display the sorted list by rating
        System.out.println("\nMovies sorted by rating:");
        for (Movie movie : movies) {
            System.out.println(movie.getName() + " - Rating: " + movie.getRating());
        }
    }

    public static void ShowMenu() {
        showMenuContent();
        Scanner in = new Scanner(System.in);
        int requirement = in.nextInt();

        while (requirement != 0) {
            switch (requirement) {
                case 1:
                    InputNamesAndRatingsForThreeMovies();
                    break;
                case 2:
                    PrintMovies();
                    break;
                case 3:
                    InputMoreMovies();
                    break;
                case 4:
                    PrintStatistics();
                    break;
                case 5:
                    searchMovie();
                    break;
                case 6:
                    updateMovieRating();
                    break;
                case 7:
                    deleteMovie();
                    break;
                case 8:
                    printSortedMovies();
                    break;
                default:
                    break;

            }
            showMenuContent();
            requirement = in.nextInt();
        }

    }

    private static void showMenuContent() {
        System.out.println("Press 1 to input movies");
        System.out.println("Press 2 to display movies and ratings");
        System.out.println("Press 3 to input more movies");
        System.out.println("Press 4 to find statistics");
        System.out.println("Press 5 to search for a movie");
        System.out.println("Press 6 to update movie rating");
        System.out.println("Press 7 to delete movie");
        System.out.println("Press 8 to sort movies for their ratings");
        System.out.println("Press 0 to exit");
    }
}
