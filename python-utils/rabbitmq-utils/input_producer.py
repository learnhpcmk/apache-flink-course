import math
import time
from datetime import datetime

import numpy as np
import pika

categories = ['A', 'B', 'C', 'D']

credentials = pika.PlainCredentials('guest', 'guest')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', credentials=credentials))
channel = connection.channel()

def create_data_sample():
	category = categories[np.random.randint(0, len(categories))]
	result = str({"category": category,
								"timestamp": math.trunc(datetime.now().timestamp() * 1000),
								"value": np.random.randint(10, 100)})
	print(result)
	return category, result.replace("'", "\"")


for i in range(1, 10000):
	category, value = create_data_sample()
	channel.basic_publish(
		exchange='statistical_analysis',
		routing_key="data",
		body=value.encode("UTF-8")
	)

	time.sleep(0.1)

print("DONE")
