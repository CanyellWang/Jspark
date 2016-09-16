package core.main.java.internal.Config.ConfigProvider;

import java.util.Map;

/**
 * Created by ChenXR on 16/9/13.
 */
public class MapProvider implements ConfigProvider {
    private Map<String, String> conf;

    public MapProvider(Map<String, String> conf) {
        this.conf = conf;
    }

    @Override
    public String get(String key) {
        return conf.get(key);
    }
}
