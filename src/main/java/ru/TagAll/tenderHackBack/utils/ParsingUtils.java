package ru.TagAll.tenderHackBack.utils;

public class ParsingUtils {

    /**
     * Берем url вида - /customer/{customerId}/notification/{notificationType}
     * для того, чтобы достать customerId или notificationType указываем их позиции в url,
     * начиная с 0.
     *
     * @param url, i
     * @return url_parameter
     */

    public static String parseUrlParameter(String url, int i){
        String[] params = url.split("/");
        return params[i];

    }
}
