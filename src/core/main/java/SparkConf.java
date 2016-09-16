package core.main.java;

import core.main.java.internal.Config.ConfigProvider.EnvProvider;
import core.main.java.internal.Config.ConfigProvider.SparkConfigProvider;
import core.main.java.internal.Config.ConfigReader;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ChenXR on 16/9/13.
 */
public class SparkConf {
    private Map<String,String> settings;
    public SparkConf(){
        this(true);
    }
    public SparkConf(Boolean loadDefaults){
        settings=new ConcurrentHashMap<String, String>();
        if (loadDefaults){
            loadFromSystemProps(false);
        }
    }

    private ConfigReader reader(){
        ConfigReader reader=new ConfigReader(new SparkConfigProvider(settings));
        return reader.bindEnv(new EnvProvider());
    }
    private SparkConf loadFromSystemProps(Boolean slient){

        return null;
    }

    private SparkConf set(String key,String value,boolean silent){
        if (key==null){
            throw new NullPointerException("null key");
        }
        if (value==null){
            throw new NullPointerException("null value for"+key);
        }
        if (!silent){

        }
        settings.put(key,value);
        return this;
    }
    public SparkConf set(String key,String value){
        return set(key,value,false);
    }
    public SparkConf setMaster(String master){
        return set("spark.master",master);
    }
    public SparkConf setAppName(String appName){
        return set("spark.app.name",appName);
    }
}
