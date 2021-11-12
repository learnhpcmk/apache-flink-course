import math
from json import dumps
import time
from datetime import datetime

import numpy as np
from kafka import KafkaProducer

categories = ['A', 'B', 'C', 'D']
bootstrap_servers = ['localhost:9092']

producer = KafkaProducer(bootstrap_servers=bootstrap_servers,
												 key_serializer=lambda x: x.encode("UTF-8"),
												 value_serializer=lambda x: x.encode("UTF-8"))


def create_data_sample():
	category = categories[np.random.randint(0, len(categories))]
	result = str({"category": category,
								"timestamp": math.trunc(datetime.now().timestamp() * 1000),
								"value": np.random.randint(10, 100)})
	print(result)
	return category, result.replace("'", "\"")


for i in range(1, 10000):
	category, value = create_data_sample()
	producer.send("data_topic", key=category, value=value)

	time.sleep(0.1)

print("DONE")
