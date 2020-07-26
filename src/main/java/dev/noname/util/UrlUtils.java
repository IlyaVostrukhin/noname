package dev.noname.util;

public class UrlUtils {
    private UrlUtils() {
    }

    private static boolean isAjaxUrl(String url) {
        return url.startsWith("/ajax/");
    }

    private static boolean isAjaxJsonUrl(String url) {
        return url.startsWith("/ajax/json/");
    }

    private static boolean isAjaxHtmlUrl(String url) {
        return url.startsWith("/ajax/html/");
    }

    private static boolean isStaticUrl(String url) {
        return url.startsWith("/static/");
    }

    private static boolean isMediaUrl(String url) {
        return url.startsWith("/media/");
    }
}
