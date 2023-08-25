package com.ujo.entityro.utils;

import java.util.Arrays;

public class StringUtils {

    /**
     * 빈 값 또는 null 체크
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 줄 내림 기호 제거
     */
    public static String removeNextLine(String str) {
        return str.replaceAll("\\n", "");
    }


    /**
     *연속된 공백 -> 하나의 공백으로
     * */
    public static String toOneSpace(String str) {
        return str.replaceAll("\\s+", " ");
    }
    
    /**
     * 공백 기준으로 배열 생성
     * */
    public static String[] splitWithSpace(String str) {
        return toOneSpace(str).split(" ");
    }
    
    /**
     * camelCase 변환
     * */
    public static String toCamelCase(String str) {
        String[] words = str.split("[-_]");
        return Arrays.stream(words, 1, words.length).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .reduce(words[0], String::concat);
    }
}
