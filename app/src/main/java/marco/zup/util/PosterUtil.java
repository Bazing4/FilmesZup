package marco.zup.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import marco.zup.R;
import marco.zup.model.Movie;

/**
 * Created by Marco on 3/Dec/16.
 */
public class PosterUtil {
    private Context context;

    public PosterUtil(Context context) {
        this.context = context;
    }

    public Bitmap getMoviePoster(Movie movie) {
        if( movie.isInvalidPoster() ) {
            return BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.no_image);
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        return BitmapFactory.decodeFile(movie.getPoster(), options);
    }

    public Movie salvarImagemPoster(Movie movie) {
        if( movie.isInvalidPoster() ) {
            return movie;
        }

        Bitmap bitmap = getBitmapFromURI(movie.getPoster());
        criarPastaPadraoDeImagensCasoNaoExista();
        String caminhoImagem = ConstantsUtil.APP_IMAGE_PATH +
                movie.getImdbID() + ".jpg";
        File file = new File(caminhoImagem);

        try(FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        movie.setPoster(caminhoImagem);

        return movie;
    }

    private Bitmap getBitmapFromURI(String uri) {
        Bitmap bitmap = null;

        try {
            InputStream input = new java.net.URL(uri).openStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private void criarPastaPadraoDeImagensCasoNaoExista() {
        File folder = new File( ConstantsUtil.APP_IMAGE_PATH );

        if ( !folder.exists() ) {
            folder.mkdirs();
        }
    }
}
