package per.goweii.anylayer.blurred;

/**
 * @FileName: BurredUtils
 * @author: LYP
 * @date: 2020/8/18 18:02
 * @function: TODO
 */
class BurredUtils {
    static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
        return obj;
    }

    static <T> T requireNonNull(T obj) {
        return requireNonNull(obj, "");
    }

}
