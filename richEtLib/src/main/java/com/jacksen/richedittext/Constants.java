package com.jacksen.richedittext;

/**
 * Created by jacksen on 2015/12/10.
 */
public class Constants {

    /**
     * " "
     */
    public static final String SPACE = " ";
    /**
     * "-"
     */
    public static final String HYPHEN = "-";
    /**
     * "/"
     */
    public static final String BIAS = "/";

    /**
     * default ID Card number length
     */
    public static final int ID_CARD_LENGTH = 18;

    /**
     * max id card number length (contains separator)
     */
    public static final int MAX_ID_CARD_LENGTH = 20;

    /**
     * default phone number length
     */
    public static final int PHONE_LENGTH = 11;

    /**
     * max phone number length (contains separator)
     */
    public static final int MAX_PHONE_LENGTH = 13;

    /**
     * default phone number split type
     * eg: 188 888 88888
     */
    public static final int DEFAULT_PHONE_SPLIT_TYPE = 335;

    /**
     * default id card number split type
     * eg: 111222 1990101 3333
     */
    public static final int DEFAULT_ID_CARD_SPLIT_TYPE = 684;

    /**
     * default phone number pattern
     * eg: 188 888 88888
     */
    public static final int[] DEFAULT_PHONE_PATTERN = new int[]{3, 3, 5};
    /**
     * default insert position
     * eg: 188 888 88888
     */
    public static final int[] DEFAULT_PHONE_POSITION = new int[]{4, 8, 13};

    /**
     * default id card pattern
     */
    public static final int[] DEFAULT_ID_CARD_PATTERN = new int[]{6, 8, 4};
}
