
package model.pojo;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Log implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    //This field is necessary and used in exercise #4!! Ignore it in the first 3 exercises.
    @SerializedName("severity")
    @Expose
    private Long severity;
    private final static long serialVersionUID = 4961563406871631892L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Log() {
    }

    /**
     * 
     * @param system
     * @param type
     * @param message
     * @param timestamp
     */
    public Log(String system, String type, String message, Long timestamp) {
        super();
        this.system = system;
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSeverity() {
        return severity;
    }

    public void setSeverity(Long severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Log.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return system.equals(log.system) &&
                type.equals(log.type) &&
                message.equals(log.message) &&
                timestamp.equals(log.timestamp) &&
                Objects.equals(severity, log.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(system, type, message, timestamp, severity);
    }
}
