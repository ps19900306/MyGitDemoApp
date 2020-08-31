package per.goweii.anylayer.blurred;

/**
 * create by: 86136
 * create time: 2020/8/31 17:26
 * Function description:
 */
class Utils {

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
