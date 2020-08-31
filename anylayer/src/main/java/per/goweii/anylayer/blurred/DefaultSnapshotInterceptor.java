package per.goweii.anylayer.blurred;

import android.graphics.Bitmap;
import android.view.View;

/**
 * @FileName: DefaultSnapshotInterceptor
 * @author: LYP
 * @date: 2020/8/18 18:07
 * @function: TODO
 */
public class DefaultSnapshotInterceptor implements Blurred.SnapshotInterceptor {
    @Override
    public Bitmap snapshot(View from, int backgroundColor, int foregroundColor, float scale, boolean antiAlias) {
        return BitmapProcessor.get().snapshot(from, backgroundColor, foregroundColor, scale, antiAlias);
    }
}
