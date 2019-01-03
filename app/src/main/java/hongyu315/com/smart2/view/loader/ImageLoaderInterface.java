package hongyu315.com.smart2.view.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

public abstract interface ImageLoaderInterface<T extends View>
        extends Serializable
{
    public abstract T createImageView(Context paramContext);

    public abstract void displayImage(Context paramContext, Object paramObject, T paramT);
}
