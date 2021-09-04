
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SeverityStats implements Serializable
{

    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("average")
    @Expose
    private Double average;
    @SerializedName("max")
    @Expose
    private Double max;
    private final static long serialVersionUID = 4135472843390104579L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SeverityStats() {
    }

    /**
     * 
     * @param average
     * @param min
     * @param max
     * @param count
     */
    public SeverityStats(Long count, Double min, Double average, Double max) {
        super();
        this.count = count;
        this.min = min;
        this.average = average;
        this.max = max;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, SeverityStats.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
        result = ((result* 31)+((this.average == null)? 0 :this.average.hashCode()));
        result = ((result* 31)+((this.min == null)? 0 :this.min.hashCode()));
        result = ((result* 31)+((this.max == null)? 0 :this.max.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SeverityStats) == false) {
            return false;
        }
        SeverityStats rhs = ((SeverityStats) other);
        return (((((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count)))&&((this.average == rhs.average)||((this.average!= null)&&this.average.equals(rhs.average))))&&((this.min == rhs.min)||((this.min!= null)&&this.min.equals(rhs.min))))&&((this.max == rhs.max)||((this.max!= null)&&this.max.equals(rhs.max))));
    }

}
