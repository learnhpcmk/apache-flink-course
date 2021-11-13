package model;

import org.json.JSONObject;

public class InputMessage {
    JSONObject jsonObject;

    public InputMessage(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public InputMessage(String jsonString) {
        this.jsonObject = new JSONObject(jsonString);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean hasField(String field) {
        return jsonObject.has(field);
    }

    public String getFieldValue(String field) {
        if (hasField(field)) {
            return jsonObject.get(field).toString();
        } else {
            return null;
        }
    }

    public Integer getFieldAsInteger(String field){
        if (hasField(field)){
            return Integer.parseInt(jsonObject.get(field).toString());
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
