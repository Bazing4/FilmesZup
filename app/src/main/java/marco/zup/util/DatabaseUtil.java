package marco.zup.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marco on 2/Dec/16.
 */
class DatabaseUtil extends SQLiteOpenHelper {
    private static final int VERSION = 5;
    private static final String DB_NAME = "Zup";

    DatabaseUtil(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE Movie (" +
                " title TEXT, " +
                " year TEXT, " +
                " rated TEXT, " +
                " released TEXT, " +
                " runtime TEXT, " +
                " genre TEXT, " +
                " director TEXT, " +
                " writer TEXT, " +
                " actors TEXT, " +
                " plot TEXT, " +
                " language TEXT, " +
                " country TEXT, " +
                " awards TEXT, " +
                " poster TEXT, " +
                " metascore TEXT, " +
                " imdbRating TEXT, " +
                " imdbVotes TEXT, " +
                " imdbID TEXT, " +
                " type TEXT )";

        db.execSQL( createQuery );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j ) {}
}
