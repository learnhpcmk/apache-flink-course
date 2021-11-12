from json import loads

from kafka import KafkaConsumer

# Creation of consumer in Apache Kafka
consumer = KafkaConsumer(
    'data_topic',
    bootstrap_servers=['localhost:9092'],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='my-group',
    value_deserializer=lambda x: loads(x.decode('utf-8'))
)

topics = consumer.topics()
print (topics)


for message in consumer:
    print (message.key, message.value)

