package com.example.ansopedia.models;

public class TabsModel {
    String tabName;
    int tabId;

    public TabsModel(){

    }
    public TabsModel(String tabName, int tabId) {
        this.tabName = tabName;
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }
}
