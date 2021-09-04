package settings;

public class ProjectSettings {
    public static final String DEFAULT_PROCESS_PARALLELISM_NAME = "process_parallelism";
    public static final int DEFAULT_PROCESS_PARALLELISM =1 ;

    public static final String EVENT_TIME_PARAMETER = "event_time";
    public static final Boolean DEFAULT_EVENT_TIME = Boolean.TRUE;

    public static final String TIMESTAMP_PROPERTY_NAME = "timestamp_property";
    public static final String DEFAULT_TIMESTAMP_PROPERTY = "timestamp";

    public static String TEST_MODE = "test";
    public static final boolean DEFAULT_TEST_MODE = true;

    public static final String DEFAULT_CONTROL_TOPIC = "control_topic";
    public static final String DEFAULT_CONTROL_TOPIC_NAME = "control_topic";

    public static final String DEFAULT_MESSAGE_TOPIC = "data_topic";
    public static final String DEFAULT_MESSAGE_TOPIC_NAME = "data_topic";

    public static final String DEFAULT_KAFKA_DATA_PARALLELISM_NAME = "kafka_data_stream_parallelism";
    public static final int DEFAULT_KAFKA_DATA_PARALLELISM = 1;

    public static final String DEFAULT_KAFKA_CONTROL_PARALLELISM_NAME = "kafka_control_stream_parallelism";
    public static final int DEFAULT_KAFKA_CONTROL_PARALLELISM = 1;

    public static final String DEFAULT_RESULT_SINK_TOPIC = "result_topic";
    public static final String DEFAULT_RESULT_SINK_TOPIC_NAME = "result_topic";
}
