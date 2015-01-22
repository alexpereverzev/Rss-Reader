package capital.rssreader;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Alexander on 22.01.2015.
 */
public class App extends Application {

    private ImageLoaderConfiguration mConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();

        mConfiguration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(10)
                .memoryCacheExtraOptions(350, 350)
                .diskCacheExtraOptions(350, 350, null)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPriority(Thread.NORM_PRIORITY)
                .diskCacheSize(2000 * 1024 * 1024)
                .memoryCache(new LruMemoryCache(1000 * 1024 * 1024))
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(mConfiguration);



    }


}
