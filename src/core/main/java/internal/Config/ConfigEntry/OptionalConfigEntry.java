package core.main.java.internal.Config.ConfigEntry;


import core.main.java.Exception.ConfigException.ConfigReaderException;
import core.main.java.internal.Config.ConfigReader;

/**
 * Created by ChenXR on 16/9/14.
 */
public class OptionalConfigEntry extends ConfigEntry {
    public OptionalConfigEntry(String key, String doc, Boolean isPublic, CallBack callBack) {
        super(key, doc, isPublic, callBack);
    }

    @Override
    public String defaultValueString() {
        return "<undefined>";
    }

    public Object readFrom(ConfigReader reader) throws ConfigReaderException {
        return callBack.valueConverter(reader.get(key));
    }
}
