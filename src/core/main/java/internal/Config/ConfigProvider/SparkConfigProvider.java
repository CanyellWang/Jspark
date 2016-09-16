package core.main.java.internal.Config.ConfigProvider;

import java.util.Map;

/**
 * Created by ChenXR on 16/9/13.
 */
public class SparkConfigProvider implements ConfigProvider {
    Map<String, String> conf;

    public SparkConfigProvider(Map<String, String> conf) {
        this.conf = conf;
    }

    @Override
    public String get(String key) {
        if (key.startsWith("spark.")) {
            String value = conf.get(key);
            if (value == null) {
                defaultValueString(key);
            }
            return value;

        }
        return null;

    }

    private String defaultValueString(String key) {

        return null;
    }
}
