
package com.tcs.nanodegree.myappportfolio.bean;

import java.util.ArrayList;
import java.util.List;
public class Trailer {

    private Integer id;
    private List<TrailerResult> results = new ArrayList<TrailerResult>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<TrailerResult> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<TrailerResult> results) {
        this.results = results;
    }

}
