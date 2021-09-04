
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ErrorDeltaTimeResult implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("first_error_timestamp")
    @Expose
    private Long firstErrorTimestamp;
    @SerializedName("second_error_timestamp")
    @Expose
    private Long secondErrorTimestamp;
    @SerializedName("delta_time")
    @Expose
    private Long deltaTime;
    private final static long serialVersionUID = -617900148876449071L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ErrorDeltaTimeResult() {
    }

    /**
     * 
     * @param system
     * @param deltaTime
     * @param secondErrorTimestamp
     * @param firstErrorTimestamp
     */
    public ErrorDeltaTimeResult(String system, Long firstErrorTimestamp, Long secondErrorTimestamp, Long deltaTime) {
        super();
        this.system = system;
        this.firstErrorTimestamp = firstErrorTimestamp;
        this.secondErrorTimestamp = secondErrorTimestamp;
        this.deltaTime = deltaTime;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Long getFirstErrorTimestamp() {
        return firstErrorTimestamp;
    }

    public void setFirstErrorTimestamp(Long firstErrorTimestamp) {
        this.firstErrorTimestamp = firstErrorTimestamp;
    }

    public Long getSecondErrorTimestamp() {
        return secondErrorTimestamp;
    }

    public void setSecondErrorTimestamp(Long secondErrorTimestamp) {
        this.secondErrorTimestamp = secondErrorTimestamp;
    }

    public Long getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(Long deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, ErrorDeltaTimeResult.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.system == null)? 0 :this.system.hashCode()));
        result = ((result* 31)+((this.firstErrorTimestamp == null)? 0 :this.firstErrorTimestamp.hashCode()));
        result = ((result* 31)+((this.deltaTime == null)? 0 :this.deltaTime.hashCode()));
        result = ((result* 31)+((this.secondErrorTimestamp == null)? 0 :this.secondErrorTimestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorDeltaTimeResult) == false) {
            return false;
        }
        ErrorDeltaTimeResult rhs = ((ErrorDeltaTimeResult) other);
        return (((((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system)))&&((this.firstErrorTimestamp == rhs.firstErrorTimestamp)||((this.firstErrorTimestamp!= null)&&this.firstErrorTimestamp.equals(rhs.firstErrorTimestamp))))&&((this.deltaTime == rhs.deltaTime)||((this.deltaTime!= null)&&this.deltaTime.equals(rhs.deltaTime))))&&((this.secondErrorTimestamp == rhs.secondErrorTimestamp)||((this.secondErrorTimestamp!= null)&&this.secondErrorTimestamp.equals(rhs.secondErrorTimestamp))));
    }

}
