plant-vertx
===========

Vert.x implementation of a webserver displaying values measured with an Arduino.  
For the application running on the see https://github.com/unSinn/plant-sensor.

The Server runs on a Raspberry Pi (with Arch Linux ARM) powering the Arduino.

**Webclient** <- HTTP,SocksJS -> **Vert.x** <- USB -> **Arduino**

Database
--------
SQLite with OrmLite (http://ormlite.com/).

Web Client
--------
Measurements will be transfered to the Client (Webbrowser) via Socks.JS using Vert.x EventBus. 
And finally displayed with Chart.js (http://www.chartjs.org/).


Running
--------
Build the fat jar with.
   gradle jar
   
Transfer the jar and the webroot-folder to your Raspberry. Then on the Raspberry run:
   java -jar plant-vertx-0.1.jar
