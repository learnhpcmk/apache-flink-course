
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import sun.jvm.hotspot.gc_implementation.g1.G1MonitoringSupport;

@Generated("jsonschema2pojo")
public class Statistics implements Serializable
{

    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("q1")
    @Expose
    private Double q1;
    @SerializedName("median")
    @Expose
    private Double median;
    @SerializedName("average")
    @Expose
    private Double average;
    @SerializedName("q3")
    @Expose
    private Double q3;
    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("count")
    @Expose
    private Long count;
    private final static long serialVersionUID = 8173597924561258501L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Statistics() {
    }

    /**
     * 
     * @param q1
     * @param average
     * @param q3
     * @param min
     * @param median
     * @param max
     * @param count
     */
    public Statistics(Double min, Double q1, Double median, Double average, Double q3, Double max, Long count) {
        super();
        this.min = min;
        this.q1 = q1;
        this.median = median;
        this.average = average;
        this.q3 = q3;
        this.max = max;
        this.count = count;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getQ1() {
        return q1;
    }

    public void setQ1(Double q1) {
        this.q1 = q1;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getQ3() {
        return q3;
    }

    public void setQ3(Double q3) {
        this.q3 = q3;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Statistics.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.q1 == null)? 0 :this.q1 .hashCode()));
        result = ((result* 31)+((this.average == null)? 0 :this.average.hashCode()));
        result = ((result* 31)+((this.q3 == null)? 0 :this.q3 .hashCode()));
        result = ((result* 31)+((this.min == null)? 0 :this.min.hashCode()));
        result = ((result* 31)+((this.median == null)? 0 :this.median.hashCode()));
        result = ((result* 31)+((this.max == null)? 0 :this.max.hashCode()));
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Statistics) == false) {
            return false;
        }
        Statistics rhs = ((Statistics) other);
        return ((((((((this.q1 == rhs.q1)||((this.q1 != null)&&this.q1 .equals(rhs.q1)))&&((this.average == rhs.average)||((this.average!= null)&&this.average.equals(rhs.average))))&&((this.q3 == rhs.q3)||((this.q3 != null)&&this.q3 .equals(rhs.q3))))&&((this.min == rhs.min)||((this.min!= null)&&this.min.equals(rhs.min))))&&((this.median == rhs.median)||((this.median!= null)&&this.median.equals(rhs.median))))&&((this.max == rhs.max)||((this.max!= null)&&this.max.equals(rhs.max))))&&((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count))));
    }

}
