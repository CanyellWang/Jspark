package core.main.java.internal.Config.ConfigEntry;

/**
 * Created by ChenXR on 16/9/14.
 */
public interface CallBack {
    Object valueConverter(String str);

    String StringConverter(Object obj);
}
