package marco.zup.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import marco.zup.R;
import marco.zup.adapter.ListaFilmesAdapter;
import marco.zup.model.Movie;
import marco.zup.util.MovieDAO;
import marco.zup.util.PosterUtil;
import marco.zup.util.ErrorRest;
import marco.zup.util.OMDbUtil;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private MovieDAO movieDAO;
    private ProgressDialog progressDialog;
    private PosterUtil posterUtil;
    private Movie movie;

    //anotacao referente aos objetos das view
    @ViewById
    ListView movieList;

    //injeta o servico rest para uso do metodo de busca
    @RestService
    OMDbUtil omDbUtil;

    //sempre e criado uma nova instancia com o @Bean, a nao ser que o @Bean seja um singleton
    @Bean
    ErrorRest errorRest;

    //permite execucao do codigo depois que as dependencias forem inseridas no @Ebean
    @AfterInject
    void afterInject() {
        errorRest = ErrorRest.criaDefault(getApplicationContext());
        omDbUtil.setRestErrorHandler(errorRest);
    }

    //executa uma thread no background da app
    @Background
    void addAndUpdate(String nome) {
        Movie movie = omDbUtil.buscarFilmePorNome(nome);

        if(!movie.isResponseOk()) {
            Toast.makeText(getApplicationContext(),
                    "Filme não encontrado",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String id = movie.getImdbID();

        if(movieDAO.filmeExiste(id)) {
            Toast.makeText(getApplicationContext(),
                    "Filme ja existente",
                    Toast.LENGTH_LONG).show();
        } else {
            movie = posterUtil.salvarImagemPoster(movie);
            movieDAO.insert(movie);
            updateMovieList();
        }
    }

    //permite a execucao do codigo logo depois da injecao dos componentes da view
    @AfterViews
    void afterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieDAO = new MovieDAO(getApplicationContext());
        posterUtil = new PosterUtil(getApplicationContext());

        ListaFilmesAdapter adapter = new ListaFilmesAdapter(this,
                movieDAO.getAll() );
        movieList = (ListView) findViewById(R.id.movieList);
        movieList.setAdapter(adapter);
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Movie movie = (Movie) movieList.getAdapter().getItem(position);
                Intent intent = new Intent(getBaseContext(), MovieDetailsActivity_.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
            }
        });
        registerForContextMenu(movieList);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Salvando");
        progressDialog.setMessage("Aguarde...");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCadastro();
            }
        });
    }

    private void showDialogCadastro() {
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View promptsView = li.inflate(R.layout.caixa_dialogo_new_movie, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);

        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.nomeFilmeText);

        alertDialogBuilder.setCancelable(false).setPositiveButton("Salvar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final String nomeDoFilme = userInput.getText().toString();
                                progressDialog.show();
                                addAndUpdate(nomeDoFilme);
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        alertDialogBuilder.create().show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        movie = (Movie) movieList.getItemAtPosition( info.position );
        menu.setHeaderTitle(movie.getTitle());
        menu.add(0, v.getId(), 0, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        movieDAO.deletar(movie.getImdbID());
        updateMovieList();
        Toast.makeText(getApplicationContext(),
                "Filme excluido",
                Toast.LENGTH_LONG).show();

        return true;
    }

    //executa na ui da thread
    @UiThread
    void updateMovieList() {
        ListaFilmesAdapter adapter = (ListaFilmesAdapter) movieList.getAdapter();
        adapter.updateMovieList(movieDAO.getAll());
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }


}
