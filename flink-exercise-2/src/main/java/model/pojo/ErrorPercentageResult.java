
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ErrorPercentageResult implements Serializable
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
    private final static long serialVersionUID = -2887943938376976853L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ErrorPercentageResult() {
    }

    /**
     * 
     * @param system
     * @param percentage
     */
    public ErrorPercentageResult(String system, Double percentage) {
        super();
        this.system = system;
        this.percentage = percentage;
    }

    public ErrorPercentageResult(String system, Double percentage, Long startTimestamp, Long endTimestamp) {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, ErrorPercentageResult.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.system == null)? 0 :this.system.hashCode()));
        result = ((result* 31)+((this.percentage == null)? 0 :this.percentage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorPercentageResult) == false) {
            return false;
        }
        ErrorPercentageResult rhs = ((ErrorPercentageResult) other);
        return (((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system)))&&((this.percentage == rhs.percentage)||((this.percentage!= null)&&this.percentage.equals(rhs.percentage))));
    }

}
