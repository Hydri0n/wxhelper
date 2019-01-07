package xyz.hydrion.wxhelper.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WxEvent {
    private String toUserName;
    private String fromUserName;
    private long createTime;
    private String msgType;
    private String event;
    private int msgId;
    private String status;

    public String getToUserName() {
        return toUserName;
    }

    @XmlElement(name = "ToUserName")
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    @XmlElement(name = "FromUserName")
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    @XmlElement(name = "CreateTime")
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    @XmlElement(name = "MsgType")
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    @XmlElement(name = "Event")
    public void setEvent(String event) {
        this.event = event;
    }

    public int getMsgId() {
        return msgId;
    }

    @XmlElement(name = "MsgID")
    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement(name = "Status")
    public void setStatus(String status) {
        this.status = status;
    }
}
