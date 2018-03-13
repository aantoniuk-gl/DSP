Dsp
======

# DSP simulation system:
* create OpenRtb Bid object 
* receive Win and Billing notifications
* when system receive Win and Billing notification generate Payment event 
* log all actions to monitoring system

# How to build 
`mvn clean install -DskipTests=true`

# How to run 
`bash run.sh N`, where N is number of DSP instances

All instances will be running on ports from 8081 to 808N  

Example `bash run.sh 5`. Five instances will be running:
* java -jar target/crypto.poc.dsp-1.0-SNAPSHOT.jar --server.port=8081 
* java -jar target/crypto.poc.dsp-1.0-SNAPSHOT.jar --server.port=8082 
* java -jar target/crypto.poc.dsp-1.0-SNAPSHOT.jar --server.port=8083 
* java -jar target/crypto.poc.dsp-1.0-SNAPSHOT.jar --server.port=8084 
* java -jar target/crypto.poc.dsp-1.0-SNAPSHOT.jar --server.port=8085 

# Endpoints:

## Create Bid:
* Method: GET
* Endpoint: \bid\{impId}, where {impId} - impression id
* Example: `\bid\1`
* Response: OpenRtb Bid object 
`id: "4b08b09d-236d-4921-ad39-220dae3a67d9",
 impid: "1",
 price: 91.0,
 nurl: "http://127.0.1.1:8082/win/1",
 iurl: "https://www.google.com.ua/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
 burl: "http://127.0.1.1:8082/billing/1"`

## Receive Win Notification:
* Method: GET
* Endpoint: \win\{impId}, where {impId} - impression id
* Example: `\win\1`

## Receive Billing Notification:
* Method: GET
* Endpoint: \billing\{impId}, where {impId} - impression id
* Example: `\billing\1`