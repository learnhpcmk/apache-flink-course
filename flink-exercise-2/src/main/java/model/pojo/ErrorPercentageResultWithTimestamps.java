
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ErrorPercentageResultWithTimestamps implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("percentage")
    @Expose
    private Double percentage;
    @SerializedName("start_timestamp")
    @Expose
    private Long startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private Long endTimestamp;
    private final static long serialVersionUID = -5760405585897121816L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ErrorPercentageResultWithTimestamps() {
    }

    /**
     * 
     * @param system
     * @param percentage
     * @param endTimestamp
     * @param startTimestamp
     */
    public ErrorPercentageResultWithTimestamps(String system, Double percentage, Long startTimestamp, Long endTimestamp) {
        super();
        this.system = system;
        this.percentage = percentage;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, ErrorPercentageResultWithTimestamps.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.system == null)? 0 :this.system.hashCode()));
        result = ((result* 31)+((this.endTimestamp == null)? 0 :this.endTimestamp.hashCode()));
        result = ((result* 31)+((this.startTimestamp == null)? 0 :this.startTimestamp.hashCode()));
        result = ((result* 31)+((this.percentage == null)? 0 :this.percentage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorPercentageResultWithTimestamps) == false) {
            return false;
        }
        ErrorPercentageResultWithTimestamps rhs = ((ErrorPercentageResultWithTimestamps) other);
        return (((((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system)))&&((this.endTimestamp == rhs.endTimestamp)||((this.endTimestamp!= null)&&this.endTimestamp.equals(rhs.endTimestamp))))&&((this.startTimestamp == rhs.startTimestamp)||((this.startTimestamp!= null)&&this.startTimestamp.equals(rhs.startTimestamp))))&&((this.percentage == rhs.percentage)||((this.percentage!= null)&&this.percentage.equals(rhs.percentage))));
    }

}
