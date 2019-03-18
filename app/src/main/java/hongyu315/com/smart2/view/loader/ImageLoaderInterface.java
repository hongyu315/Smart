package hongyu315.com.smart2.view.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

public interface ImageLoaderInterface<T extends View>
        extends Serializable
{
    T createImageView(Context paramContext);

    void displayImage(Context paramContext, Object paramObject, T paramT);
}
