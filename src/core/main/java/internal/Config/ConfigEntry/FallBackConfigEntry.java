package core.main.java.internal.Config.ConfigEntry;

import core.main.java.Exception.ConfigException.ConfigEntryException;
import core.main.java.Exception.ConfigException.ConfigReaderException;
import core.main.java.internal.Config.ConfigReader;

/**
 * Created by ChenXR on 16/9/14.
 */
public class FallBackConfigEntry extends ConfigEntry {
    private ConfigEntry fallBack;

    public FallBackConfigEntry(String key, String doc, Boolean isPublic, ConfigEntry fallBack) {
        super(key, doc, isPublic, fallBack.callBack);
    }

    @Override
    public String defaultValueString() {
        return String.format("<value of %s>", fallBack.key);
    }

    @Override
    public Object readFrom(ConfigReader reader) throws ConfigReaderException, ConfigEntryException {

        Object value = callBack.valueConverter(reader.get(key));
        if (value==null){
            value=fallBack.readFrom(reader);
        }
        return value;
    }
}
