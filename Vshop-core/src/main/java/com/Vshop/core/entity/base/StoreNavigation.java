package com.Vshop.core.entity.base;



import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreNavigation {
    private Integer snId;

    private String snTitle;

    private Integer snStoreId;

    private Byte snSort;

    private Boolean snIfShow;

    private Integer snAddTime;

    private String snUrl;

    private Boolean snNewOpen;

    private String snContent;

    public Integer getSnId() {
        return snId;
    }

    public void setSnId(Integer snId) {
        this.snId = snId;
    }

    public String getSnTitle() {
        return snTitle;
    }

    public void setSnTitle(String snTitle) {
        this.snTitle = snTitle;
    }

    public Integer getSnStoreId() {
        return snStoreId;
    }

    public void setSnStoreId(Integer snStoreId) {
        this.snStoreId = snStoreId;
    }

    public Byte getSnSort() {
        return snSort;
    }

    public void setSnSort(Byte snSort) {
        this.snSort = snSort;
    }

    public Boolean getSnIfShow() {
        return snIfShow;
    }

    public void setSnIfShow(Boolean snIfShow) {
        this.snIfShow = snIfShow;
    }

    public Integer getSnAddTime() {
        return snAddTime;
    }

    public void setSnAddTime(Integer snAddTime) {
        this.snAddTime = snAddTime;
    }

    public String getSnUrl() {
        return snUrl;
    }

    public void setSnUrl(String snUrl) {
        this.snUrl = snUrl;
    }

    public Boolean getSnNewOpen() {
        return snNewOpen;
    }

    public void setSnNewOpen(Boolean snNewOpen) {
        this.snNewOpen = snNewOpen;
    }

    public String getSnContent() {
        return snContent;
    }

    public void setSnContent(String snContent) {
        this.snContent = snContent;
    }
}