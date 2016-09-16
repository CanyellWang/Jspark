package core.main.java.internal.Config.ConfigEntry;

import core.main.java.Exception.ConfigException.ConfigReaderException;
import core.main.java.internal.Config.ConfigReader;

/**
 * Created by ChenXR on 16/9/14.
 */
public class ConfigEntryWithDefaultString extends ConfigEntry {
    private String defaultValue;

    public ConfigEntryWithDefaultString(String key, String doc, Boolean isPublic, CallBack callBack, String defaultValue) {
        super(key, doc, isPublic, callBack);
        this.defaultValue = defaultValue;
    }

    @Override
    public String defaultValueString() {
        return defaultValue;
    }

    @Override
    public Object defaultValue() {
        return callBack.valueConverter(defaultValue);
    }

    @Override
    public Object readFrom(ConfigReader reader) throws ConfigReaderException {
        String value = reader.get(key);
        if (value == null) {
            value = reader.substitude(defaultValue);
        }
        return callBack.valueConverter(value);

    }
}
