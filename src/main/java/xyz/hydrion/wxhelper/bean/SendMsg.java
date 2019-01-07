package xyz.hydrion.wxhelper.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SendMsg {

    @JsonProperty("touser")
    private String toUser;
    @JsonProperty("template_id")
    private String templateId;
    private String url;
    @JsonProperty("topcolor")
    private String topColor;
    private Map<String, DataValue> data;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public Map<String, DataValue> getData() {
        return data;
    }

    public void setData(Map<String, DataValue> data) {
        this.data = data;
    }


    public static class DataValue{
        String value;
        String color;

        public DataValue(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
