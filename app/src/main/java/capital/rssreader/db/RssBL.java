package capital.rssreader.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 22.01.2015.
 */
public class RssBL {
    private Context mContext;

    private DBRssHelper databaseHelper;

    public RssBL(Context context) {
        mContext = context;
        databaseHelper = new DBRssHelper(mContext);
    }

    public RssBL(){

    }

    public List<RssItem> getRss() {


        List<RssItem> result = new ArrayList<RssItem>();
        try {

            return databaseHelper.getDao(RssItem.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean checkItem(int id) {
        boolean result = false;
        try {
            Dao<RssItem, Integer> dao = databaseHelper.getDateModelDao();
            QueryBuilder<RssItem, Integer> qb = dao.queryBuilder();
            qb.where().eq("rss_id", id);

            PreparedQuery<RssItem> pq = qb.prepare();
            List<RssItem> res = dao.query(pq);
            if (res != null)
                result = true;
            else result = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveItem(RssItem model) {
        databaseHelper = new DBRssHelper(mContext);
        try {
            Dao<RssItem, Integer> dao = databaseHelper.getDateModelDao();
            dao.create(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        databaseHelper.close();
    }

}
