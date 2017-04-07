package com.freeman.util;

/**
 * Created by jalmei14 on 4/4/17.
 */

public class ImageProvider {

    private static final String BASE_IMAGE_URL= "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w185";
    private static final String ORIGINAL = "original";

    public static String provideImageURL(String imageName){
        return BASE_IMAGE_URL + IMAGE_SIZE + imageName;
    }

    public static String provideQualityImageURL(String imageName){
        return BASE_IMAGE_URL + ORIGINAL + imageName;
    }

}
