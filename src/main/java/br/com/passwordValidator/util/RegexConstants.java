package br.com.passwordValidator.util;

public final class RegexConstants {


    public static final String HAS_SPACE_BETWEEN_REGEX = "(?=.*\\s).*";
    public static final String HAS_DIGIT_REGEX = "(?=.*\\d).*";
    public static final String LOWER_LETTER_REGEX = "(?=.*[a-z]).*";
    public static final String HAS_UPPER_LETTERS_REGEX = "(?=.*[A-Z]).*";
    public static final String HAS_SPECIAL_CHAR_REGEX = "(?=.*[!@#$%^&*()-+]).*";
    public static final String LENGTH_REGEX = ".{9,}";
    public static final String HAS_REPETITION_REGEX = "^.*(.).*\\1.*$";

}
