package org.launchcode.artgallery.models;

import java.util.Comparator;

public class StyleComparator implements Comparator<Style> {

    @Override
    public int compare(Style s1, Style s2) {
        return s1.getName().compareTo(s2.getName());
    }
    //使用 ArtistComparator.compare(a1, a2) 方法来比较两个 Artist 对象。
    //按照 name 的 字母顺序（因为 compareTo() 是 String 的比较方法）对 artists 进行排序。
}
