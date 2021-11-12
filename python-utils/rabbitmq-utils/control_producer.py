import time

import pika

credentials = pika.PlainCredentials('guest', 'guest')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', credentials=credentials))
channel = connection.channel()

with open("/Users/stefanandonov/Documents/flink-kurs/python-utils/kafka-utils/control_messages.txt", "r") as file:
	for line in file.readlines():
		channel.basic_publish(
			exchange='statistical_analysis',
			routing_key="control",
			body=line.encode("UTF-8")
		)
		print (line)
		time.sleep(1)

# file.close()
print("DONE")
