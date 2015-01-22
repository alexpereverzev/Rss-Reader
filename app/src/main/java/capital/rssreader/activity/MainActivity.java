package capital.rssreader.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import capital.rssreader.R;
import capital.rssreader.adapter.ListAdapter;
import capital.rssreader.db.RssBL;
import capital.rssreader.db.RssItem;
import capital.rssreader.service.ReadService;
import eu.erikw.PullToRefreshListView;


public class MainActivity extends Activity {

    PendingIntent pi;
    private int PARAM = 100;
    private int RESULT = 200;
    RssBL bl;
    ListAdapter adapter;
    List<RssItem> item;
    @InjectView(R.id.list)
    PullToRefreshListView mList;
    Intent data;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        data = new Intent(this, DetailActivity.class);
        i = new Intent(this, ReadService.class);
        bl = new RssBL(this);
        pi = createPendingResult(PARAM, new Intent(), 0);
        i.putExtra("url", "http://lenta.ru/rss");
        i.putExtra("pendingIntent", pi);
        if(savedInstanceState!=null){
            item=new ArrayList<>();
                   item=(savedInstanceState.getParcelableArrayList("key"));
            adapter=new ListAdapter(this,item);
            mList.setAdapter(adapter);
        }
       else  startService(i);



        mList.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startService(i);

            }
        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data.putExtra("item", item.get(position));
                startActivity(data);
            }
        });


        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                startService(i);
            }
        };


        timer.schedule(hourlyTask, 0l, 1000 * 15 * 60);   // 1000*10*60 every 10 minut

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mList.onRefreshComplete();
        item = bl.getRss();
        adapter = new ListAdapter(this, item);
        mList.setAdapter(adapter);

        // Ловим сообщения о старте задач
        if (resultCode == PARAM) {

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        ArrayList<RssItem> ie=new ArrayList<>();
        if(item!=null) {
            ie.addAll(item);

            outState.putParcelableArrayList("key", ie);
        }
        super.onSaveInstanceState(outState);

    }
}
