
package model.pojo.control;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import model.InputMessage;

@Generated("jsonschema2pojo")
public class Filter implements Serializable {

    private static final String EQUAL = "EQ";
    private static final String CONTAINS = "CONTAINS";
    private static final String NOT_EQUAL = "NOT_EQ";
    private static final String LESS_THAN = "LT";
    private static final String GREATER_THAN = "GT";
    private static final String LESS_THAN_OR_EQUAL = "LTEQ";
    private static final String GREATER_THAN_OR_EQUAL = "GTEQ";

    private static final String NUMERIC = "number";
    private static final String TEXT = "string";

    @SerializedName("property")
    @Expose
    private String property;
    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("value_type")
    @Expose
    private String valueType;
    private final static long serialVersionUID = 4773771678497022923L;

    /**
     * No args constructor for use in serialization
     */
    public Filter() {
    }

    /**
     * @param valueType
     * @param property
     * @param value
     * @param operator
     */
    public Filter(String property, String operator, String value, String valueType) {
        super();
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.valueType = valueType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, Filter.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.property == null) ? 0 : this.property.hashCode()));
        result = ((result * 31) + ((this.value == null) ? 0 : this.value.hashCode()));
        result = ((result * 31) + ((this.operator == null) ? 0 : this.operator.hashCode()));
        result = ((result * 31) + ((this.valueType == null) ? 0 : this.valueType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Filter) == false) {
            return false;
        }
        Filter rhs = ((Filter) other);
        return (((((this.property == rhs.property) || ((this.property != null) && this.property.equals(rhs.property))) && ((this.value == rhs.value) || ((this.value != null) && this.value.equals(rhs.value)))) && ((this.operator == rhs.operator) || ((this.operator != null) && this.operator.equals(rhs.operator)))) && ((this.valueType == rhs.valueType) || ((this.valueType != null) && this.valueType.equals(rhs.valueType))));
    }

    public boolean check(InputMessage inputMessage) {

        if (!inputMessage.hasField(property))
            return true;

        if (valueType.equals(TEXT)) {
            String inputMessageValue = inputMessage.getFieldValue(property);
            if (operator.equals(EQUAL)) {
                return inputMessageValue.equalsIgnoreCase(value);
            }
            if (operator.equals(CONTAINS)) {
                return inputMessageValue.toLowerCase().contains(value.toLowerCase());
            }
            if (operator.equals(NOT_EQUAL)){
                return !inputMessageValue.equalsIgnoreCase(value);
            }
        } else { //numbers
            Double inputMessageValue = Double.parseDouble(inputMessage.getFieldValue(property));
            Double filterValue = Double.parseDouble(value);
            if (operator.equals(LESS_THAN_OR_EQUAL))
                return inputMessageValue <= filterValue;
            else if (operator.equals(LESS_THAN))
                return inputMessageValue < filterValue;
            else if (operator.equals(GREATER_THAN_OR_EQUAL))
                return inputMessageValue >= filterValue;
            else if (operator.equals(GREATER_THAN))
                return inputMessageValue > filterValue;
            else if (operator.equals(EQUAL))
                return inputMessageValue.doubleValue() == filterValue.doubleValue();
            else if (operator.equalsIgnoreCase(NOT_EQUAL))
                return inputMessageValue.doubleValue() != filterValue.doubleValue();
        }

        return false;
    }
}
