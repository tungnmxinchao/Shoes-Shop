/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author TNO
 */
public class Categories {
    private String categoryId;
    private String categoryName;
    private String describle;

    public Categories() {
    }
    
    //lombok

    public Categories(String categoryId, String categoryName, String describle) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.describle = describle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    @Override
    public String toString() {
        return "Categories{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", describle=" + describle + '}';
    }
    
    
    
}
