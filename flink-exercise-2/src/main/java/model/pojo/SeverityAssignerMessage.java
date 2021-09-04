
package model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SeverityAssignerMessage implements Serializable
{

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("severities")
    @Expose
    private List<Severity> severities = new ArrayList<Severity>();
    private final static long serialVersionUID = -2215503378052445398L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SeverityAssignerMessage() {
    }

    /**
     * 
     * @param system
     * @param severities
     */
    public SeverityAssignerMessage(String system, List<Severity> severities) {
        super();
        this.system = system;
        this.severities = severities;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<Severity> getSeverities() {
        return severities;
    }

    public void setSeverities(List<Severity> severities) {
        this.severities = severities;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, SeverityAssignerMessage.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.system == null)? 0 :this.system.hashCode()));
        result = ((result* 31)+((this.severities == null)? 0 :this.severities.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SeverityAssignerMessage) == false) {
            return false;
        }
        SeverityAssignerMessage rhs = ((SeverityAssignerMessage) other);
        return (((this.system == rhs.system)||((this.system!= null)&&this.system.equals(rhs.system)))&&((this.severities == rhs.severities)||((this.severities!= null)&&this.severities.equals(rhs.severities))));
    }

}
