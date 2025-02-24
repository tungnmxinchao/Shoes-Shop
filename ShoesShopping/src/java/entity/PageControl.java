/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author TNO
 */
public class PageControl {

    private String urlPattern;
    private int totalPage;
    private int totalRecord;
    private int page;

    public PageControl() {
    }

    public PageControl(String urlPattern, int totalPage, int totalRecord, int page) {
        this.urlPattern = urlPattern;
        this.totalPage = totalPage;
        this.totalRecord = totalRecord;
        this.page = page;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "PageControl{" + "urlPattern=" + urlPattern + ", totalPage=" + totalPage + ", totalRecord=" + totalRecord + ", page=" + page + '}';
    }

}
