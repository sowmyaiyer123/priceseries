# Price series service

* This service uses a producer-consumer pattern using RabbitMQ.
* PriceProducer publishes data to the queue every 5 seconds.
* PriceConsumer receives this data from the queue and stores it in DataStoreService that maintains these values in a list. 
* The logic to obtain average of last N entries is implemented using Java8 streams (using skip and average methods of DoubleStream)
* This service exposes a GET API which is consumed by the front end. This API obtains the average of last N entries from the
DataStoreService and responds to the front-end 
* Service hosted on http://localhost:8080/
