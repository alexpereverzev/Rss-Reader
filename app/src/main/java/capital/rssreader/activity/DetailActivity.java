package capital.rssreader.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import butterknife.ButterKnife;
import butterknife.InjectView;
import capital.rssreader.App;
import capital.rssreader.R;
import capital.rssreader.db.RssItem;

/**
 * Created by Alexander on 22.01.2015.
 */
public class DetailActivity extends Activity {

    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .resetViewBeforeLoading(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)

            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();


    @InjectView(R.id.icon)
    ImageView mIcon;
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.data)
    TextView mData;
    @InjectView(R.id.content)
    TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        RssItem dat= getIntent().getParcelableExtra("item");
        ImageLoader.getInstance().displayImage(dat.getImageUrl(),mIcon, options);

        mTitle.setText(dat.getTitle());
        mData.setText(dat.getData());
        mContent.setText(dat.getDescription());
    }
}
