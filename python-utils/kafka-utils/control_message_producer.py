import math
from json import dumps
import time
from datetime import datetime

import numpy as np
from kafka import KafkaProducer

bootstrap_servers = ['localhost:9092']

producer = KafkaProducer(bootstrap_servers=bootstrap_servers,
												 value_serializer=lambda x: x.encode("UTF-8"))

with open("/Users/stefanandonov/Documents/flink-kurs/python-utils/kafka-utils/control_messages.txt", "r") as file:
	for line in file.readlines():
		producer.send("control_topic", value=line)
		print (line)
		time.sleep(1)

# file.close()
print("DONE")
