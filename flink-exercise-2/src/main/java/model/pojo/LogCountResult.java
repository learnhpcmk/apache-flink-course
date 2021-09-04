
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class LogCountResult implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("log_type")
    @Expose
    private String logType;
    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("start_timestamp")
    @Expose
    private Long startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private Long endTimestamp;
    private final static long serialVersionUID = -1215532553943819953L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LogCountResult() {
    }

    /**
     * 
     * @param logType
     * @param system
     * @param count
     * @param endTimestamp
     * @param startTimestamp
     */
    public LogCountResult(String system, String logType, Long count, Long startTimestamp, Long endTimestamp) {
        super();
        this.system = system;
        this.logType = logType;
        this.count = count;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
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

    @Override
    public String toString() {
        return new Gson().toJson(this, LogCountResult.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.logType == null)? 0 :this.logType.hashCode()));
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
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
        if ((other instanceof LogCountResult) == false) {
            return false;
        }
        LogCountResult rhs = ((LogCountResult) other);
        return ((((((this.logType == rhs.logType)||((this.logType!= null)&&this.logType.equals(rhs.logType)))&&((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count))))&&((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system))))&&((this.endTimestamp == rhs.endTimestamp)||((this.endTimestamp!= null)&&this.endTimestamp.equals(rhs.endTimestamp))))&&((this.startTimestamp == rhs.startTimestamp)||((this.startTimestamp!= null)&&this.startTimestamp.equals(rhs.startTimestamp))));
    }

}
