package core.main.java.internal.Config.ConfigEntry;

import core.main.java.Exception.ConfigException.ConfigEntryException;
import core.main.java.Exception.ConfigException.ConfigReaderException;
import core.main.java.internal.Config.ConfigReader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ChenXR on 16/9/13.
 */
public abstract class ConfigEntry {
    String key;
    String doc;
    Boolean isPublic;
    CallBack callBack;
    private static Map<String, ConfigEntry> knowConfigs = new ConcurrentHashMap<String, ConfigEntry>();

    public static void registerEntry(ConfigEntry entry) throws ConfigEntryException {
        ConfigEntry existing = knowConfigs.putIfAbsent(entry.key, entry);
        if (existing == null) {
            throw new ConfigEntryException("ConfigEntry do not exist!");
        }
    }

    public static ConfigEntry findEntry(String key) {
        return knowConfigs.get(key);
    }

    public ConfigEntry(String key, String doc, Boolean isPublic, CallBack callBack) {
        this.key = key;
        this.doc = doc;
        this.isPublic = isPublic;
        this.callBack = callBack;
    }

    public String defaultValueString() {
        return null;
    }

    public Object readFrom(ConfigReader reader) throws ConfigEntryException, ConfigReaderException {
        return null;
    }

    public Object defaultValue() {
           return null;
    }


}
