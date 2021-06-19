package com.example.quiz_app.utils;



public class ColorPicker {

    private static String[] color = {"#3eb9df", "#3685bc", "#d36280", "#E44F55", "#Fa8056", "#818bCA", "#51bab3", "#4fb66c", "#e3ad17", "#323fdf", "#3e239df", "#3ecadf"};
    private static int curretcolor = 0;

   public static String getColor() {
        curretcolor = (curretcolor + 1) % color.length;
        return color[curretcolor];
    }
}
