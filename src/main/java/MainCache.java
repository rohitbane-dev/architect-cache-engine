import com.rohit.project.cache.CacheMechanism;
import com.rohit.project.storage.impl.SimpleDBStorage;
import com.rohit.project.storage.interfaces.DBStorage;

public class MainCache {

    public static void main(String[] args) {
        DBStorage<String, String> db = new SimpleDBStorage<>();
        CacheMechanism<String, String> cache= new CacheMechanism<>("LFU", null, 3, db);

        cache.updateData("A", "rohit");
        cache.updateData("B", "bane");
        cache.updateData("C", "rupali");

        String val = cache.getValue("C");
        System.out.println(val);
        cache.getValue("B");
        cache.getValue("C");
        cache.updateData("A", "ramesh");
        cache.updateData("D", "god");
        cache.updateData("A", "devil");
        cache.updateData("Z", "zed");
        System.out.println(cache.store.eviction.cacheStorageMap);

    }
}
