# Процесирање со ограничено доцнење на проточни податоци со Apache Flink 

### Автори:

Стефан Андонов (stefan.andonov@finki.ukim.mk)

Ѓорѓи Маџаров (gjorgji.madjarov@finki.ukim.mk)

### Oпис на курсот

Целта на овој курс е слушателите постепено да се запознаат со начинот на процесирање на потоци од податоци со ограничено доцнење. Воведување во концептот на MapReduce преку реални примери со имплементација во Apache Flink. Примарен фокус се става на процесирање на неограничени (бесконечни) потоци од податоци во различни модови на обработка (настанско време и процесирачко време) преку концептот на прозорци. Заради поголема флексибилност на решенијата, дополнително направен е вовед и во динамичко менаџирање на состојбите кои го дефинираат начинот на процесирање на податоците. 

### Опис на репозиториумот

Во директориумот flink-docker-installation се наоѓаат Dockerfile-ови и docker-compose датотеки кои се слушат за локално поставување на Flink, Kafka и RabbitMQ. Содржината на овој директориум се користи во вежбите 1 и 5. 

Во директориумот flink-hello-word се наоѓа кодот од задачите првата вежба.

Во директориумот flink-exercise-2 се наоѓа кодот за задачите од 2-6та вежба. 

Во директориумот python-utils се наоѓаат python скипти кои се користат во 5та вежба за симулирање на real-time data stream.



