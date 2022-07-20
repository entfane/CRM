package com.example.crm.adapter;


import com.example.crm.utils.SortUtils;

public class SortUtilsAdapter {

    public static SortUtils intToSortUtils(int sort) {
        switch (sort) {
            case 1:
                return SortUtils.FIRST_NAME;
            case 3:
                return SortUtils.EMAIL;
            default:
                return SortUtils.LAST_NAME;
        }
    }

}
