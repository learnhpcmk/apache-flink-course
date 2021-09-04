
package model.pojo;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class RuleStatisticsResult implements Serializable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("rule_id")
    @Expose
    private String ruleId;
    @SerializedName("filters_description")
    @Expose
    private String filtersDescription;
    @SerializedName("start_timestamp")
    @Expose
    private Long startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private Long endTimestamp;
    @SerializedName("statistics")
    @Expose
    private Statistics statistics;
    private final static long serialVersionUID = -2997480825522378435L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RuleStatisticsResult() {
    }

    /**
     * 
     * @param filtersDescription
     * @param ruleId
     * @param userId
     * @param endTimestamp
     * @param startTimestamp
     * @param statistics
     */
    public RuleStatisticsResult(String userId, String ruleId, String filtersDescription, Long startTimestamp, Long endTimestamp, Statistics statistics) {
        super();
        this.userId = userId;
        this.ruleId = ruleId;
        this.filtersDescription = filtersDescription;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.statistics = statistics;
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

    public String getFiltersDescription() {
        return filtersDescription;
    }

    public void setFiltersDescription(String filtersDescription) {
        this.filtersDescription = filtersDescription;
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

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, RuleStatisticsResult.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.filtersDescription == null)? 0 :this.filtersDescription.hashCode()));
        result = ((result* 31)+((this.ruleId == null)? 0 :this.ruleId.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        result = ((result* 31)+((this.endTimestamp == null)? 0 :this.endTimestamp.hashCode()));
        result = ((result* 31)+((this.startTimestamp == null)? 0 :this.startTimestamp.hashCode()));
        result = ((result* 31)+((this.statistics == null)? 0 :this.statistics.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RuleStatisticsResult) == false) {
            return false;
        }
        RuleStatisticsResult rhs = ((RuleStatisticsResult) other);
        return (((((((this.filtersDescription == rhs.filtersDescription)||((this.filtersDescription!= null)&&this.filtersDescription.equals(rhs.filtersDescription)))&&((this.ruleId == rhs.ruleId)||((this.ruleId!= null)&&this.ruleId.equals(rhs.ruleId))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))))&&((this.endTimestamp == rhs.endTimestamp)||((this.endTimestamp!= null)&&this.endTimestamp.equals(rhs.endTimestamp))))&&((this.startTimestamp == rhs.startTimestamp)||((this.startTimestamp!= null)&&this.startTimestamp.equals(rhs.startTimestamp))))&&((this.statistics == rhs.statistics)||((this.statistics!= null)&&this.statistics.equals(rhs.statistics))));
    }

}
