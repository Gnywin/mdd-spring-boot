package mdd.logistics.system.cache;

import mdd.logistics.controller.sms.vo.SmsVo;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 猫大东
 *
 * @param <K>
 * @param <V>
 */
public class MddCodeCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_CACHE_SIZE = 2000;

    public MddCodeCache() {
        super((int) Math.ceil(MAX_CACHE_SIZE / 0.75) + 1, 0.75f, true);
    }

    @Override
    public V get(Object key) {
        V val = super.get(key);
        if (val instanceof SmsVo) {
            if (((SmsVo) val).getExpireTime() > System.currentTimeMillis()) {
                return val;
            }
        }
        super.remove(key);
        return null;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
