package per.goweii.anylayer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import per.goweii.anylayer.blurred.Blurred;


/**
 * @author Cuizhen
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public final class AnyLayer {

    public static void init(@NonNull Application application) {
        ActivityHolder.init(application);
    }

    public static void initBlurred(@NonNull Context context) {
        Blurred.init(context);
    }

    public static void recycleBlurred() {
        Blurred.recycle();
    }

    @NonNull
    public static GlobalConfig globalConfig() {
        return GlobalConfig.get();
    }

    public static void dialog(@NonNull LayerActivity.OnLayerCreatedCallback callback) {
        LayerActivity.start(ActivityHolder.getApplication(), callback);
    }

    @NonNull
    public static DialogLayer dialog() {
        return new DialogLayer(ActivityHolder.requireCurrentActivity());
    }

    @NonNull
    public static DialogLayer dialog(@NonNull Class<Activity> clazz) {
        return new DialogLayer(ActivityHolder.requireActivity(clazz));
    }

    @NonNull
    public static DialogLayer dialog(@NonNull Context context) {
        return new DialogLayer(context);
    }

    @NonNull
    public static PopupLayer popup(@NonNull Context context) {
        return new PopupLayer(context);
    }

    @NonNull
    public static PopupLayer popup(@NonNull View targetView) {
        return new PopupLayer(targetView);
    }

    @NonNull
    public static ToastLayer toast() {
        return new ToastLayer(ActivityHolder.requireCurrentActivity());
    }

    @NonNull
    public static ToastLayer toast(@NonNull Context context) {
        return new ToastLayer(context);
    }
}
