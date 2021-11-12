
package model.pojo.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import model.InputMessage;

@Generated("jsonschema2pojo")
public class Rule implements Serializable
{

    @SerializedName("filters")
    @Expose
    private List<Filter> filters = new ArrayList<Filter>();
    private final static long serialVersionUID = 2027952668375367421L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rule() {
    }

    /**
     * 
     * @param filters
     */
    public Rule(List<Filter> filters) {
        super();
        this.filters = filters;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Rule.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.filters == null)? 0 :this.filters.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rule) == false) {
            return false;
        }
        Rule rhs = ((Rule) other);
        return ((this.filters == rhs.filters)||((this.filters!= null)&&this.filters.equals(rhs.filters)));
    }

    public boolean checkRule(InputMessage inputMessage) {
        return filters.stream().allMatch(filter -> filter.check(inputMessage));
    }
}
