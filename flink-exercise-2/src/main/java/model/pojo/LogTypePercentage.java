
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class LogTypePercentage implements Serializable
{

    @SerializedName("log_type")
    @Expose
    private String logType;
    @SerializedName("percentage")
    @Expose
    private Double percentage;
    private final static long serialVersionUID = 2385221183279820441L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LogTypePercentage() {
    }

    /**
     * 
     * @param logType
     * @param percentage
     */
    public LogTypePercentage(String logType, Double percentage) {
        super();
        this.logType = logType;
        this.percentage = percentage;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this,LogTypePercentage.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.logType == null)? 0 :this.logType.hashCode()));
        result = ((result* 31)+((this.percentage == null)? 0 :this.percentage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogTypePercentage) == false) {
            return false;
        }
        LogTypePercentage rhs = ((LogTypePercentage) other);
        return (((this.logType == rhs.logType)||((this.logType!= null)&&this.logType.equals(rhs.logType)))&&((this.percentage == rhs.percentage)||((this.percentage!= null)&&this.percentage.equals(rhs.percentage))));
    }

}
