import pika

credentials = pika.PlainCredentials('guest', 'guest')
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost', credentials=credentials))
channel = connection.channel()

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body.decode("UTF-8"))


channel.basic_consume(queue='result_queue',
                      auto_ack=True,
                      on_message_callback=callback)

channel.start_consuming()
