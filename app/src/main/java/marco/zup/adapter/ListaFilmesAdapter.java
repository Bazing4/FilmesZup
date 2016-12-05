package marco.zup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import marco.zup.R;
import marco.zup.model.Movie;
import marco.zup.util.PosterUtil;

/**
 * Created by Marco on 3/Dec/16.
 */
public class ListaFilmesAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Movie> filmes;
    private Context context;
    private PosterUtil posterUtil;

    //lista de objetos a serem colocados na lista inicial de filmes
    private class LayoutObj {
        TextView txtYear;
        ImageView imgIcon;
        TextView txtTitle;
        TextView imdbR;
    }

    public ListaFilmesAdapter(Context context, List<Movie> movies) {
        this.filmes = movies;
        this.context = context;
        posterUtil = new PosterUtil(context);
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return filmes.size();
    }
    public Movie getItem(int position) {
        return filmes.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutObj layoutObj;

        if (view == null) {
            view = mInflater.inflate(R.layout.list_movies, null);
            layoutObj = new LayoutObj();
            layoutObj.txtYear = ((TextView) view.findViewById(R.id.movieYear));
            layoutObj.txtTitle = ((TextView) view.findViewById(R.id.movieName));
            layoutObj.imgIcon = ((ImageView) view.findViewById(R.id.moviePoster));
            layoutObj.imdbR = ((TextView)view.findViewById(R.id.movieImdb));
            view.setTag(layoutObj);
        } else {
            layoutObj = (LayoutObj) view.getTag();
        }

        Movie item = filmes.get(position);
        layoutObj.txtTitle.setText(item.getTitle());
        layoutObj.txtYear.setText(item.getYear());
        layoutObj.imdbR.setText("Imdb:"+item.getImdbRating());
        layoutObj.imgIcon.setImageBitmap(posterUtil.getMoviePoster(item));

        return view;
    }

    public void updateMovieList(List<Movie> movies) {
        this.filmes = movies;
    }

}
