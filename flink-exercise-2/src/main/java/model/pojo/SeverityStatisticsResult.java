
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SeverityStatisticsResult implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("start_timestamp")
    @Expose
    private Long startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private Long endTimestamp;
    @SerializedName("severity_stats")
    @Expose
    private SeverityStats severityStats;
    private final static long serialVersionUID = 7382848841604871136L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SeverityStatisticsResult() {
    }

    /**
     * 
     * @param severityStats
     * @param system
     * @param endTimestamp
     * @param startTimestamp
     */
    public SeverityStatisticsResult(String system, Long startTimestamp, Long endTimestamp, SeverityStats severityStats) {
        super();
        this.system = system;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.severityStats = severityStats;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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

    public SeverityStats getSeverityStats() {
        return severityStats;
    }

    public void setSeverityStats(SeverityStats severityStats) {
        this.severityStats = severityStats;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, SeverityStatisticsResult.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.severityStats == null)? 0 :this.severityStats.hashCode()));
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
        if ((other instanceof SeverityStatisticsResult) == false) {
            return false;
        }
        SeverityStatisticsResult rhs = ((SeverityStatisticsResult) other);
        return (((((this.severityStats == rhs.severityStats)||((this.severityStats!= null)&&this.severityStats.equals(rhs.severityStats)))&&((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system))))&&((this.endTimestamp == rhs.endTimestamp)||((this.endTimestamp!= null)&&this.endTimestamp.equals(rhs.endTimestamp))))&&((this.startTimestamp == rhs.startTimestamp)||((this.startTimestamp!= null)&&this.startTimestamp.equals(rhs.startTimestamp))));
    }

}
