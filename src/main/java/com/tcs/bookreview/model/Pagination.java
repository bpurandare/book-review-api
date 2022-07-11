package com.tcs.bookreview.model;

public class Pagination {

    private int page;
    private int totalPages;
    private int totalRrecords;

    public Pagination() {
    }

    public Pagination(int page, int totalPages) {
        this.page = page;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRrecords() {
        return totalRrecords;
    }

    public void setTotalRrecords(int totalRrecords) {
        this.totalRrecords = totalRrecords;
    }
}
