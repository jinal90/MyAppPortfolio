
package com.tcs.nanodegree.myappportfolio.bean;

import java.util.ArrayList;
import java.util.List;

public class Review {

    private Integer id;
    private Integer page;
    private List<ReviewResult> results = new ArrayList<ReviewResult>();
    private Integer total_pages;
    private Integer total_results;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return The results
     */
    public List<ReviewResult> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<ReviewResult> results) {
        this.results = results;
    }

    /**
     * @return The total_pages
     */
    public Integer getTotal_pages() {
        return total_pages;
    }

    /**
     * @param total_pages The total_pages
     */
    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    /**
     * @return The total_results
     */
    public Integer getTotal_results() {
        return total_results;
    }

    /**
     * @param total_results The total_results
     */
    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

}
