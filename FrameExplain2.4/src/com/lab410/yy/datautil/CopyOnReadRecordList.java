package com.lab410.yy.datautil;

/**
 * User: jiezhen
 * Date: 3/20/13
 * Time: 10:59 PM
 */
public class CopyOnReadRecordList {   

    private static CopyOnReadList<Record> instance;

    public static CopyOnReadList<Record> getInstance () {   
        if (instance == null) {
            instance = new CopyOnReadList<Record>();
        }
        return instance;
    }

}
