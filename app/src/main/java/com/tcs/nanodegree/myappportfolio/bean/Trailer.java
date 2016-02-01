
package com.tcs.nanodegree.myappportfolio.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Trailer implements Parcelable {

    private Integer id;
    private List<TrailerResult> results = new ArrayList<TrailerResult>();

    protected Trailer(Parcel in) {

        id = in.readInt();
        results = in.createTypedArrayList(TrailerResult.CREATOR);
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

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
     * @return The results
     */
    public List<TrailerResult> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<TrailerResult> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeTypedList(results);

    }
}
