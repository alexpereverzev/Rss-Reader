package capital.rssreader.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

import java.util.List;

import capital.rssreader.activity.MainActivity;
import capital.rssreader.db.RssBL;
import capital.rssreader.db.RssItem;
import capital.rssreader.reader.RssReader;

/**
 * Created by Alexander on 22.01.2015.
 */
public class ReadService extends IntentService {

    List<RssItem> current;

    public ReadService() {
        super("read");
    }


    public ReadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i = new Intent();
        RssReader rssReader = new RssReader(intent.getStringExtra("url"));
        PendingIntent pi = intent.getParcelableExtra("pendingIntent");
        try {
            List<RssItem> item=  rssReader.getItems();

           RssBL bl=new RssBL(ReadService.this);
            current=bl.getRss();
            if(current.size()==0){
              for(RssItem r:item){
                  bl.saveItem(r);
                }

            }
            else if(current.get(0).getTitle().equals(item.get(0).getTitle())){

            }
            else  {
                for(int y=0; y<item.size(); y++){
                    if(item.get(y).getTitle().equals(current.get(0).getTitle())){
                        for(int k=0; k<y; k++){
                            bl.saveItem(item.get(k));
                        }
                        break;
                    }
                }


            }
            pi.send(ReadService.this, 200, i);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
