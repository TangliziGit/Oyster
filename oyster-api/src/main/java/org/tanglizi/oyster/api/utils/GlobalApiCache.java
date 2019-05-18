package org.tanglizi.oyster.api.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class GlobalApiCache {
    private final long CACHE_SIZE=1024;

    // expiration for sec
    private final long DEFUALTE_EXPIRATION=3600;

    private static GlobalApiCache singleton=new GlobalApiCache();

    private TreeMap<String, CacheObject> cache=new TreeMap<>();

    private GlobalApiCache(){}

    public static GlobalApiCache getSingleton(){
        return singleton;
    }

    public Object get(String key){
        long now=System.currentTimeMillis();

        CacheObject cacheObject=cache.get(key);
        if (cacheObject != null){
            if (cacheObject.getExpiration()<=0 || cacheObject.getExpiration()>now){
                cache.remove(key);
            }

            return cacheObject.getValue();
        }

        return null;
    }

    public void set(String key, Object value){
        this.set(key, value, DEFUALTE_EXPIRATION);
    }

    public void set(String key, Object value, long expiration){
        if (cache.size() > CACHE_SIZE)
            this.flash();

        expiration = (expiration>0)?(System.currentTimeMillis()/1000 + expiration):-1;
        CacheObject cacheObject=new CacheObject(value, expiration);
        cache.put(key, cacheObject);
    }

    public void flash(){
        long now=System.currentTimeMillis();
        Iterator<Map.Entry<String, CacheObject>> it=cache.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, CacheObject> entry=it.next();
            CacheObject cacheObject=entry.getValue();

            if (cacheObject.getExpiration()>0 && cacheObject.getExpiration() > now)
                it.remove();
        }

        // 若清理后仍然大于总量（因为expiration可以为负），暴力直接删除
        if (cache.size() > CACHE_SIZE)
            cache.clear();
    }

    public void del(String key){
        cache.remove(key);
    }

    class CacheObject{
        private Object value;
        private long expiration;

        public CacheObject(Object value, long expiration) {
            this.value = value;
            this.expiration = expiration;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public long getExpiration() {
            return expiration;
        }

        public void setExpiration(long expiration) {
            this.expiration = expiration;
        }

    }
}
