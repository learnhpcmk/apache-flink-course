
package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class LogTypePercentagesResult implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("start_timestamp")
    @Expose
    private Long startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private Long endTimestamp;
    @SerializedName("log_type_percentages")
    @Expose
    private List<LogTypePercentage> logTypePercentages = new ArrayList<LogTypePercentage>();
    private final static long serialVersionUID = 1668201160216877692L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LogTypePercentagesResult() {
    }

    /**
     * 
     * @param logTypePercentages
     * @param system
     * @param count
     * @param endTimestamp
     * @param startTimestamp
     */
    public LogTypePercentagesResult(String system, Long count, Long startTimestamp, Long endTimestamp, List<LogTypePercentage> logTypePercentages) {
        super();
        this.system = system;
        this.count = count;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.logTypePercentages = logTypePercentages;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    public List<LogTypePercentage> getLogTypePercentages() {
        return logTypePercentages;
    }

    public void setLogTypePercentages(List<LogTypePercentage> logTypePercentages) {
        this.logTypePercentages = logTypePercentages;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this,LogTypePercentagesResult.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
        result = ((result* 31)+((this.logTypePercentages == null)? 0 :this.logTypePercentages.hashCode()));
        result = ((result* 31)+((this.system == null)? 0 :this.system.hashCode()));
        result = ((result* 31)+((this.endTimestamp == null)? 0 :this.endTimestamp.hashCode()));
        result = ((result* 31)+((this.startTimestamp == null)? 0 :this.startTimestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogTypePercentagesResult) == false) {
            return false;
        }
        LogTypePercentagesResult rhs = ((LogTypePercentagesResult) other);
        return ((((((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count)))&&((this.logTypePercentages == rhs.logTypePercentages)||((this.logTypePercentages!= null)&&this.logTypePercentages.equals(rhs.logTypePercentages))))&&((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system))))&&((this.endTimestamp == rhs.endTimestamp)||((this.endTimestamp!= null)&&this.endTimestamp.equals(rhs.endTimestamp))))&&((this.startTimestamp == rhs.startTimestamp)||((this.startTimestamp!= null)&&this.startTimestamp.equals(rhs.startTimestamp))));
    }

}
