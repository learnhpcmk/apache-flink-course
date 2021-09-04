
package model.pojo.control;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ControlMessage implements Serializable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("rule_id")
    @Expose
    private String ruleId;
    @SerializedName("rule")
    @Expose
    private Rule rule;
    @SerializedName("statistics_property")
    @Expose
    private String statisticsProperty;
    @SerializedName("window_configuration")
    @Expose
    private WindowConfiguration windowConfiguration;
    private final static long serialVersionUID = 21189359773983570L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ControlMessage() {
    }

    /**
     * 
     * @param statisticsProperty
     * @param rule
     * @param windowConfiguration
     * @param ruleId
     * @param userId
     */
    public ControlMessage(String userId, String ruleId, Rule rule, String statisticsProperty, WindowConfiguration windowConfiguration) {
        super();
        this.userId = userId;
        this.ruleId = ruleId;
        this.rule = rule;
        this.statisticsProperty = statisticsProperty;
        this.windowConfiguration = windowConfiguration;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getStatisticsProperty() {
        return statisticsProperty;
    }

    public void setStatisticsProperty(String statisticsProperty) {
        this.statisticsProperty = statisticsProperty;
    }

    public WindowConfiguration getWindowConfiguration() {
        return windowConfiguration;
    }

    public void setWindowConfiguration(WindowConfiguration windowConfiguration) {
        this.windowConfiguration = windowConfiguration;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, ControlMessage.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.rule == null)? 0 :this.rule.hashCode()));
        result = ((result* 31)+((this.windowConfiguration == null)? 0 :this.windowConfiguration.hashCode()));
        result = ((result* 31)+((this.ruleId == null)? 0 :this.ruleId.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        result = ((result* 31)+((this.statisticsProperty == null)? 0 :this.statisticsProperty.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ControlMessage) == false) {
            return false;
        }
        ControlMessage rhs = ((ControlMessage) other);
        return ((((((this.rule == rhs.rule)||((this.rule!= null)&&this.rule.equals(rhs.rule)))&&((this.windowConfiguration == rhs.windowConfiguration)||((this.windowConfiguration!= null)&&this.windowConfiguration.equals(rhs.windowConfiguration))))&&((this.ruleId == rhs.ruleId)||((this.ruleId!= null)&&this.ruleId.equals(rhs.ruleId))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))))&&((this.statisticsProperty == rhs.statisticsProperty)||((this.statisticsProperty!= null)&&this.statisticsProperty.equals(rhs.statisticsProperty))));
    }

}
