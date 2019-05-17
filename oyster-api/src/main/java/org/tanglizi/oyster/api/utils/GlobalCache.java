package org.tanglizi.oyster.api.utils;

import ch.qos.logback.core.joran.action.TimestampAction;

import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GlobalCache {
    private final long CACHE_SIZE=1024;

    private static GlobalCache singleton=new GlobalCache();

    private TreeMap<String, CacheObject> cache=new TreeMap<>();

    private GlobalCache(){}

    public GlobalCache getSingleton(){
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
        while (cache.size()>=CACHE_SIZE){

        }

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
