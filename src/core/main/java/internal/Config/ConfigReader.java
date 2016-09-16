package core.main.java.internal.Config;

import core.main.java.Exception.ConfigException.ConfigReaderException;
import core.main.java.internal.Config.ConfigProvider.ConfigProvider;
import core.main.java.internal.Config.ConfigProvider.EnvProvider;
import core.main.java.internal.Config.ConfigProvider.MapProvider;
import core.main.java.internal.Config.ConfigProvider.SystemProvider;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChenXR on 16/9/13.
 */
public class ConfigReader {
    private static String REF_RE = "\\$\\{(?:(\\w+?):)?(\\S+?)\\}";
    private Map<String, ConfigProvider> bindings;
    private ConfigProvider conf;

    public ConfigReader(ConfigProvider conf) {
        this.conf = conf;
        bindings = new HashMap<String, ConfigProvider>();
        // bind(null, conf);
        // bindEnv(new EnvProvider());
        // bindSystem(new SystemProvider());
    }

    public ConfigReader(Map<String, String> conf) {
        this(new MapProvider(conf));
    }

    public String get(String key) throws ConfigReaderException {

        return substitude(conf.get(key));
    }

    public ConfigReader bind(String prefix, ConfigProvider provider) {
        this.bindings.put(prefix, provider);
        return this;
    }

    public ConfigReader bind(String prefix, Map<String, String> values) {
        return bind(prefix, new MapProvider(values));
    }

    public ConfigReader bindEnv(ConfigProvider provider) {
        return bind("env", provider);
    }

    public ConfigReader bindSystem(ConfigProvider provider) {
        return bind("system", provider);
    }

    private String substitude(String input) throws ConfigReaderException {
        return substitude(input, new HashSet<>());
    }

    private String substitude(String input, Set<String> usedRefs) throws ConfigReaderException {
        if (input != null) {
            Pattern pattern = Pattern.compile(ConfigReader.REF_RE);
            Matcher matcher = pattern.matcher(input);
            return ConfigReader.REF_RE.replaceAll(input, innerSubstitude(matcher, usedRefs));
        }
        return input;
    }

    private String innerSubstitude(Matcher m, Set<String> usedRefs) throws ConfigReaderException {
        String prefix = m.group(1);
        String name = m.group(2);
        String ref = (prefix == null) ? name : (prefix + ":" + name);
        if (usedRefs.contains(ref)) {
            throw new ConfigReaderException("Circular reference in input: ref");
        }
        usedRefs.add(ref);
        String replace = substitude(bindings.get(prefix).get(name), usedRefs);
        if (replace == null) {
            replace = m.group();
        }
        return Matcher.quoteReplacement(replace);

    }


}


