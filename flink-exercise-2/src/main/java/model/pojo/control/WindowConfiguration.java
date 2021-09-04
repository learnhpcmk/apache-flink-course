
package model.pojo.control;

import java.io.Serializable;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class WindowConfiguration implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("size")
    @Expose
    private Long size;
    @SerializedName("slide")
    @Expose
    private Long slide;
    private final static long serialVersionUID = -3478611076473221082L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WindowConfiguration() {
    }

    /**
     * 
     * @param size
     * @param slide
     * @param type
     */
    public WindowConfiguration(String type, Long size, Long slide) {
        super();
        this.type = type;
        this.size = size;
        this.slide = slide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSlide() {
        return slide;
    }

    public void setSlide(Long slide) {
        this.slide = slide;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, WindowConfiguration.class);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.slide == null)? 0 :this.slide.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WindowConfiguration) == false) {
            return false;
        }
        WindowConfiguration rhs = ((WindowConfiguration) other);
        return ((((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type)))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.slide == rhs.slide)||((this.slide!= null)&&this.slide.equals(rhs.slide))));
    }

}
