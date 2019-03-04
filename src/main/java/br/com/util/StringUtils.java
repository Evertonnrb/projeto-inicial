package br.com.util;

/**
 * @author - Everton Ribeiro
 * @email - everton.nrb@gmail.com
 **/
public class StringUtils {

    public static String likeLower(String param){
        return "%"+param.toLowerCase()+"%";
    }

    public static String like(String param){
        return "%"+param+"%";
    }
}
