# Benz- Connected Cars Platform Developer- Online assignment 
 Submitted by Ajumon Joy (ajumon.c@gmail.com)
# Architecture
![Blank diagram](https://user-images.githubusercontent.com/63783290/131974254-7671f20a-e82b-44c4-bd81-0044f3a0fb86.jpeg)

**Process**
- **Store/update** API service communicate through AMQ(Rabbit MQ) between Service 1 and Service 2.
- **Read** API - using http protocol to read the data from files(CSV and XML)
- All communications are AES encrypted and google protos 3 bufferformat (serialisation through byte stream)

**Service 1:**
- **Store-data/ Update-data API**- send the data to AMQ and **/read-data** API to read data from service2/read-data API from service 2.
- Three API- store, update and read
- File details:
  - **DataEncrypter.java** - AES encryptor - It will do an AES encryption/decryption based on the secret Key
  - **DataProtos.java**- Google Protos 3 bufferformat -it will convert encrypted/decrypt data to byte array or vice versa.
  - **RabbitMQSender.java** - Sending byte stream to Rabbit MQ.

**Service 2:**
- Read data from MQ and store/update the data based on the format type and to read the data from files
- One API exposed to read the data from files
- File details:
   - **RabbitMQConsumer.java** - Sending byte stream to Rabbit MQ.
   - **DataProtos.java**- Google Protos 3 bufferformat -it will convert encrypted/decrypt data to byte array or vice versa.
   - **DataAES.java** - AES encryptor - It will do an AES encryption/decryption based on the secret Key
 

### Steps to configure the Micro-services - Service 1 and Service 2

**1. Install RabbitMQ:**

Go through steps in below link and setup a local RabbitMQ host.

- https://www.javainuse.com/misc/rabbitmq-hello-world
- Run RabbitMQ in local host (http://localhost:15672/#/queues/%2F/store.queue)

**2. Setup service 1 and Service 2 Micro-service**  

- A. Checkout service 1 and service 2 from the github (https://github.com/ajumonc/Benz-Connected-Cars-Assignment)

- B. Open service 1 and service 2 in any IDE(IntelliJ/Eclipse) separately 

- C. Open IDE and run the springboot application (/src/java/controller)

- D. Open service 1 project in IDE and run (service1application.java)
  - Open the service in any browser (http://localhost:8090/swagger-ui.html#!).

- E. Open service 2 project in IDE and run (service2application.java)
  - Open the service in any browser (http://localhost:9090/swagger-ui.html#!).

- F. If both the local host port are up and running :- 

  - Open **service 1** (http://localhost:8090/swagger-ui.html#!) and 
  check three API publish on the swagger (_/store-data ,/update-data, /read-data_).

G. Open any of the API in _**swaggerUI**_

 - Open _**/Store-data**_ API in swaggerUI (http://localhost:8090/swagger-ui.html#!/store-data-controller/storeDataUsingPUT) - Paste the JSON data on request body, select fileType _**(XML/CSV)**_ from dropdown and click on **_Try it out_** - It will store the data in selected file format in service 2 and get a message in response box _successfully stored data / Failed to store the data, Try after some time._

 - Open _**/update-data**_ API in swaggerUI (http://localhost:8090/swagger-ui.html#!/store-data-controller/updateDataUsingPUT) -Paste the JSON data on request body , select fileType _**(XML/CSV)**_ from dropdown and click on **_Try it out_** -It will update the data in selected file format in service 2 and get a message in response box _successfully updated data / Failed to update the data,Try after some time._.
 
 - Open _**/read-data**_ API in swaggerUI (http://localhost:8090/swagger-ui.html#!/store-data-controller/readDataUsingGET) click on **_Try it out_** -It will read the data from files stored in Service 2. The read data from service 2 file display on the response box / Failed to read data ,Try after some time.

H. Check the console logs  and proper message is logged for each operation.
 
