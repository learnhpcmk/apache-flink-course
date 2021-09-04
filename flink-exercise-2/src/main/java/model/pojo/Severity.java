
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Severity implements Serializable
{

    @SerializedName("log_type")
    @Expose
    private String logType;
    @SerializedName("severity")
    @Expose
    private Long severity;
    private final static long serialVersionUID = 6150979377021034818L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Severity() {
    }

    /**
     * 
     * @param logType
     * @param severity
     */
    public Severity(String logType, Long severity) {
        super();
        this.logType = logType;
        this.severity = severity;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getSeverity() {
        return severity;
    }

    public void setSeverity(Long severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Severity.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.logType == null)? 0 :this.logType.hashCode()));
        result = ((result* 31)+((this.severity == null)? 0 :this.severity.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Severity) == false) {
            return false;
        }
        Severity rhs = ((Severity) other);
        return (((this.logType == rhs.logType)||((this.logType!= null)&&this.logType.equals(rhs.logType)))&&((this.severity == rhs.severity)||((this.severity!= null)&&this.severity.equals(rhs.severity))));
    }

}
