package capital.rssreader.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Alexander on 22.01.2015.
 */
@DatabaseTable(tableName = "feed")
public class RssItem implements Parcelable {

    public RssItem(){}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(data);

    }

    public static final Creator<RssItem> CREATOR = new Creator<RssItem>() {
        public RssItem createFromParcel(Parcel in) {
            return new RssItem(in);
        }

        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };

    private RssItem(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {

        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        data=in.readString();
           }

    public static final class COL {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String DATA = "data";
        public static final String IMAGE = "url";
    }

    @DatabaseField(columnName = COL.ID, generatedId = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DatabaseField(columnName = COL.TITLE)
    private String title;

    @DatabaseField(columnName = COL.DESCRIPTION)
    private String description;

    private String link;

    @DatabaseField(columnName = COL.IMAGE)
    private String imageUrl;

    @DatabaseField(columnName = COL.DATA)
    private String data;
}
