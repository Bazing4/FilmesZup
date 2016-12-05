package marco.zup.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import marco.zup.R;
import marco.zup.model.Movie;
import marco.zup.util.PosterUtil;

@EActivity(R.layout.activity_movie_detail)
public class MovieDetailsActivity extends AppCompatActivity {
    private PosterUtil posterUtil;

    @AfterViews
    void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        posterUtil = new PosterUtil(getApplicationContext());

        fill();
    }

    @ViewById
    ImageView moviePoster;

    @ViewById
    TextView movieTitle;

    @ViewById
    TextView movieGenre;

    @ViewById
    TextView movieRuntime;

    @ViewById
    TextView movieDirector;

    @ViewById
    TextView moviePlot;

    @ViewById
    TextView movieActors;

    @ViewById
    TextView movieLanguage;

    @ViewById
    TextView metaScore;

    //metodo para o preenchimento da view de detalhes do filme
    private void fill() {
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        moviePoster.setImageBitmap(posterUtil.getMoviePoster(movie));
        movieTitle.setText(movie.getTitle() + " (" + movie.getYear() + ")");
        movieGenre.setText(movie.getGenre());
        movieRuntime.setText(movie.getRuntime());
        moviePlot.setText(movie.getPlot());
        metaScore.setText("Metascore:"+ movie.getMetascore());
        movieLanguage.setText(movie.getLanguage());
        movieActors.setText(movie.getActors());
        movieDirector.setText(movie.getDirector());
    }
}
