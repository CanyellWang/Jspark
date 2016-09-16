package core.main.java.internal.Config.ConfigProvider;

/**
 * Created by ChenXR on 16/9/13.
 */
public class SystemProvider implements ConfigProvider {
    @Override
    public String get(String key) {
        return System.getProperties().getProperty(key);
    }
}
