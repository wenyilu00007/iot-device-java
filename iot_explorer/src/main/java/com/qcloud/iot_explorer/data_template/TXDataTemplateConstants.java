package com.qcloud.iot_explorer.data_template;

import com.qcloud.iot_explorer.mqtt.TXAlarmPingSender;

public interface TXDataTemplateConstants {
    /**
     * topic
     */
    String TOPIC_PROPERTY_DOWN_PREFIX = "$thing/down/property/";
    String TOPIC_PROPERTY_UP_PREFIX = "$thing/up/property/";
    String TOPIC_EVENT_DOWN_PREFIX = "$thing/down/event/";
    String TOPIC_EVENT_UP_PREFIX = "$thing/up/event/";
    String TOPIC_ACTION_DOWN_PREFIX = "$thing/down/action/";
    String TOPIC_ACTION_UP_PREFIX = "$thing/up/action/";

    enum TemplateSubTopic{
        PROPERTY_DOWN_TOPIC,
        EVENT_DOWN_TOPIC,
        ACTION_DOWN_TOPIC
    }

    enum TemplatePubTopic{
        PROPERTY_UP_TOPIC,
        EVENT_UP_TOPIC,
        ACTION_UP_TOPIC
    }

    /**
     * property method
     */
    String METHOD_PROPERTY_REPORT = "report";
    String METHOD_PROPERTY_REPORT_REPLY = "report_replay";

    String METHOD_PROPERTY_CONTROL = "control";
    String METHOD_PROPERTY_CONTROL_REPLY = "control_replay";

    String METHOD_PROPERTY_GET_STATUS = "get_status";
    String METHOD_PROPERTY_GET_STATUS_REPLY = "get_status_replay";

    String METHOD_PROPERTY_CLEAR_CONTROL = "clear_control";
    String METHOD_PROPERTY_CLEAR_CONTROL_REPLY = "clear_control_replay";

    String METHOD_PROPERTY_REPORT_INFO = "report_info";
    String METHOD_PROPERTY_REPORT_INFO_REPLY = "report_info_replay";

    /**
     * event method
     */
    String METHOD_EVENT_POST = "event_post";
    String METHOD_EVENT_REPLY = "event_reply";

    /**
     * action method
     */
    String METHOD_ACTION = "action";
    String METHOD_ACTION_REPLY = "action_reply";

}
