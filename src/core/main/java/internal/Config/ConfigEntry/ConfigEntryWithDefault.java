package core.main.java.internal.Config.ConfigEntry;

import core.main.java.Exception.ConfigException.ConfigReaderException;
import core.main.java.internal.Config.ConfigReader;

/**
 * Created by ChenXR on 16/9/14.
 */
public class ConfigEntryWithDefault extends ConfigEntry {
    private Object defaultValue;

    public ConfigEntryWithDefault(String key, String doc, Boolean isPublic, CallBack callBack, Object defaultValue) {
        super(key, doc, isPublic, callBack);
        this.defaultValue = defaultValue;
    }

    @Override
    public Object defaultValue() {
        return defaultValue;
    }

    @Override
    public String defaultValueString() {
        return callBack.StringConverter(defaultValue);
    }
    @Override
    public Object readFrom(ConfigReader reader) throws ConfigReaderException {
        Object value= callBack.valueConverter(reader.get(key));
        if (value==null){
            value=defaultValue;
        }
        return value;

    }


}
