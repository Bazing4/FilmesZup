package marco.zup.builder;

import marco.zup.model.Movie;

/**
 * Created by Marco on 2/Dec/16.
 */
public class MovieBuilder {
    private Movie movie;
    public Movie build() {
        return movie;
    }
    public static MovieBuilder newInstance() {
        return new MovieBuilder();
    }
    private MovieBuilder() {
        movie = new Movie();
    }


    public MovieBuilder setTitle(String title) {
        movie.setTitle(title);
        return this;
    }

    public MovieBuilder setYear(String year) {
        movie.setYear(year);
        return this;
    }

    public MovieBuilder setRated(String rated) {
        movie.setRated(rated);
        return this;
    }

    public MovieBuilder setReleased(String released) {
        movie.setReleased(released);
        return this;
    }

    public MovieBuilder setRuntime(String runtime) {
        movie.setRuntime(runtime);
        return this;
    }

    public MovieBuilder setGenre(String genre) {
        movie.setGenre(genre);
        return this;
    }

    public MovieBuilder setDirector(String director) {
        movie.setDirector(director);
        return this;
    }

    public MovieBuilder setWriter(String writer) {
        movie.setWriter(writer);
        return this;
    }

    public MovieBuilder setActors(String actors) {
        movie.setActors(actors);
        return this;
    }

    public MovieBuilder setPlot(String plot) {
        movie.setPlot(plot);
        return this;
    }

    public MovieBuilder setLanguage(String language) {
        movie.setLanguage(language);
        return this;
    }

    public MovieBuilder setCountry(String country) {
        movie.setCountry(country);
        return this;
    }

    public MovieBuilder setAwards(String awards) {
        movie.setAwards(awards);
        return this;
    }

    public MovieBuilder setPoster(String poster) {
        movie.setPoster(poster);
        return this;
    }

    public MovieBuilder setMetascore(String metascore) {
        movie.setMetascore(metascore);
        return this;
    }

    public MovieBuilder setImdbRating(String imdbRating) {
        movie.setImdbRating(imdbRating);
        return this;
    }

    public MovieBuilder setImdbVotes(String imdbVotes) {
        movie.setImdbVotes(imdbVotes);
        return this;
    }

    public MovieBuilder setImdbID(String imdbID) {
        movie.setImdbID(imdbID);
        return this;
    }

    public MovieBuilder setType(String type) {
        movie.setType(type);
        return this;
    }
}
