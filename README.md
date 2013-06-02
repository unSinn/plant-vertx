plant-vertx
===========

Vert.x (http://vertx.io/) implementation of a webserver displaying values measured with an Arduino.  
For the application running on the Arduino see https://github.com/unSinn/plant-sensor.

The Server runs on a Raspberry Pi (with Arch Linux ARM) powering the Arduino.

**Webclient** <- HTTP,SockJS -> **Vert.x** <- USB -> **Arduino**

Database
--------
SQLite with OrmLite (http://ormlite.com/).

Web Client
--------
Measurements will be transfered to the Client (Webbrowser) via SockJS (http://sockjs.org/) using Vert.x EventBus. 
And finally displayed with Chart.js (http://www.chartjs.org/).


Running
--------
Build the fat jar with.

`gradle jar`
   
Transfer the jar and the webroot-folder to your Raspberry. Then on the Raspberry run:

`java -jar plant-vertx-0.1.jar`
