package capital.rssreader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by Alexander on 22.01.2015.
 */
public class DBRssHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "RssDB.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    public DBRssHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // the DAO object we use to access the SimpleData table
    private Dao<RssItem, Integer> RssDao = null;

    @Override
    public void onCreate(final SQLiteDatabase db, final ConnectionSource connectionSource) {
        try {


            TableUtils.createTable(connectionSource, RssItem.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust the various data to
     * match the new version number.
     */
    @Override
    public void onUpgrade(final SQLiteDatabase db, final ConnectionSource connectionSource, final int oldVersion, final int newVersion) {
        try {


            TableUtils.dropTable(connectionSource, RssItem.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<RssItem, Integer> getDateModelDao() throws java.sql.SQLException {
        if (this.RssDao == null) {
            this.RssDao = getDao(RssItem.class);
        }
        return this.RssDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        this.RssDao = null;

    }
}
