package com.louisgeek.louisnextnext;

import java.util.List;
import java.util.Map;

/**
 * Created by louisgeek on 2016/5/3.
 */
public class TextoreInfo {
    public static  final int TYPE_CODE_EditText=1;
    public static final int TYPE_CODE_TextView=2;
    public static final int TYPE_CODE_Switch=3;
    public static final int TYPE_CODE_CheckBox=4;
    public static final int TYPE_CODE_RadioButton=5;
    public static final int TYPE_CODE_Spinner=6;
    public static final int TYPE_CODE_CheckedTextView=7;
    public static final int TYPE_CODE_SeekBar=8;
    public static final int TYPE_CODE_NumberPicker=9;
    public static final int TYPE_CODE_DatePicker=10;
    public static final int TYPE_CODE_TimePicker =11;
    //public static final int TYPE_CODE_CalendarView =12;
    //......

    private int id;
    private String tiid;
    private int sortNum;//排序


    private int typeCode;//标记类型
    private String Text;//说明
    private String key;//key
    private List<Map<String,Object>> mapListValue;//备选值
    private String value;//值
    private int pageId;//所属页面id


    public TextoreInfo(int id, String tiid, int sortNum, int typeCode, String text, String key, List<Map<String, Object>> mapListValue, String value, int pageId) {
        this.id = id;
        this.tiid = tiid;
        this.sortNum = sortNum;
        this.typeCode = typeCode;
        Text = text;
        this.key = key;
        this.mapListValue = mapListValue;
        this.value = value;
        this.pageId = pageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiid() {
        return tiid;
    }

    public void setTiid(String tiid) {
        this.tiid = tiid;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Map<String, Object>> getMapListValue() {
        return mapListValue;
    }

    public void setMapListValue(List<Map<String, Object>> mapListValue) {
        this.mapListValue = mapListValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
