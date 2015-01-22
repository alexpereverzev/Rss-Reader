package capital.rssreader.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import capital.rssreader.App;
import capital.rssreader.R;
import capital.rssreader.db.RssItem;

/**
 * Created by Alexander on 22.01.2015.
 */
public class ListAdapter extends BaseAdapter {

    private Context ctx;
    private LayoutInflater lInflater;
    private List<RssItem> objects;

    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .resetViewBeforeLoading(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheOnDisk(true)

            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public ListAdapter(Context context, List<RssItem> users) {
        ctx = context;
        objects = users;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) return 0;
        else return 1;
    }

    @Override
    public int getCount() {

        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.feed, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        RssItem rss = getRss(position);
        holder.mTitle.setText(rss.getTitle());
        holder.mData.setText(rss.getData());
        ImageLoader.getInstance().displayImage(rss.getImageUrl(), holder.mIcon, options);

        return view;
    }


    private RssItem getRss(int position) {
        return ((RssItem) getItem(position));
    }


    static class ViewHolder {
        @InjectView(R.id.icon)
        ImageView mIcon;
        @InjectView(R.id.title)
        TextView mTitle;
        @InjectView(R.id.data)
        TextView mData;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
