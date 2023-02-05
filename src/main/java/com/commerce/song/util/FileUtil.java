package com.commerce.song.util;

import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    private static final Tika tika = new Tika();

    public static boolean isImgFile(InputStream inputStream, String fileName) throws IOException {
        List<String> isTypeList = Arrays.asList("image/jpeg", "image/pjpeg", "image/png", "image/gif", "image/bmp");

        String mimeType = tika.detect(inputStream);
        boolean isMimeType = isTypeList.stream().anyMatch(isType -> isType.equalsIgnoreCase(mimeType));

        if(!isMimeType) return false;


        int index = fileName.lastIndexOf(".");
        String ext = fileName.substring(index + 1);

        return "jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext) || "bmp".equals(ext);
    }
}
