
package com.bigbang.booksearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResult {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("items")
    @Expose
    private List<BookItem> items = null;
    @SerializedName("totalItems")
    @Expose
    int totalItems;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<BookItem> getItems() {
        return items;
    }

    public void setItems(List<BookItem> items) {
        this.items = items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
