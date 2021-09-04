
package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SystemsListByLogTypeResult implements Serializable
{

    @SerializedName("log_type")
    @Expose
    private String logType;
    @SerializedName("systems")
    @Expose
    private List<String> systems = new ArrayList<String>();
    @SerializedName("timespan")
    @Expose
    private Long timespan;
    private final static long serialVersionUID = 2537904645549044789L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SystemsListByLogTypeResult() {
    }

    /**
     * 
     * @param logType
     * @param systems
     * @param timespan
     */
    public SystemsListByLogTypeResult(String logType, List<String> systems, Long timespan) {
        super();
        this.logType = logType;
        this.systems = systems;
        this.timespan = timespan;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public List<String> getSystems() {
        return systems;
    }

    public void setSystems(List<String> systems) {
        this.systems = systems;
    }

    public Long getTimespan() {
        return timespan;
    }

    public void setTimespan(Long timespan) {
        this.timespan = timespan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SystemsListByLogTypeResult.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("logType");
        sb.append('=');
        sb.append(((this.logType == null)?"<null>":this.logType));
        sb.append(',');
        sb.append("systems");
        sb.append('=');
        sb.append(((this.systems == null)?"<null>":this.systems));
        sb.append(',');
        sb.append("timespan");
        sb.append('=');
        sb.append(((this.timespan == null)?"<null>":this.timespan));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.logType == null)? 0 :this.logType.hashCode()));
        result = ((result* 31)+((this.timespan == null)? 0 :this.timespan.hashCode()));
        result = ((result* 31)+((this.systems == null)? 0 :this.systems.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SystemsListByLogTypeResult) == false) {
            return false;
        }
        SystemsListByLogTypeResult rhs = ((SystemsListByLogTypeResult) other);
        return ((((this.logType == rhs.logType)||((this.logType!= null)&&this.logType.equals(rhs.logType)))&&((this.timespan == rhs.timespan)||((this.timespan!= null)&&this.timespan.equals(rhs.timespan))))&&((this.systems == rhs.systems)||((this.systems!= null)&&this.systems.equals(rhs.systems))));
    }

}
