
package ch.ma3.plant.datasources;


import groovy.json.JsonSlurper
import jssc.*

import java.sql.Date;

import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger
import org.vertx.java.core.logging.impl.LoggerFactory
import org.vertx.java.deploy.Verticle

import ch.ma3.plant.entities.Measurement
import ch.ma3.plant.entities.Sensor
import ch.ma3.plant.verticles.DataCollector

class ArduinoDataSource extends Verticle implements DataSource{

	private DataCollector dataCollector
	private int baudRate = 57600
	private Logger log = LoggerFactory.getLogger(ArduinoDataSource.class)
	private SerialPort serialPort
	private def sensorMap = [:]

	ArduinoDataSource(){
		printSerialPortNames()
		openPort("/dev/ttyUSB0")
	}

	@Override
	DataSource setDataCollector(DataCollector collector) {
		dataCollector = collector
		sensorMap = collector.getSensors();
		return this
	}

	@Override
	void start() throws Exception {
		if(!dataCollector){
			throw new Exception("DataCollector not set!")
		}
	}

	@Override
	void stop() throws Exception {
		serialPort.closePort()
	};

	void printSerialPortNames(){
		SerialPortList.getPortNames().each { port ->
			log.info(port)
		}
	}

	void openPort(name){
		serialPort = new SerialPort(name);
		try {
			serialPort.openPort()
			serialPort.setParams(baudRate, 8, 1, 0)
			int mask = SerialPort.MASK_RXCHAR
			serialPort.setEventsMask(mask)
			serialPort.addEventListener(new MySerialPortEventListener());
			log.info ("Port " + serialPort.portName + " open!")
		}
		catch (SerialPortException ex) {
			log.error(ex)
		}
	}

	void parseAndSendData(String line){
		try{
			def json = new JsonSlurper().parseText(line)
			Measurement m = new Measurement()
			m.setDate(new Date(System.currentTimeMillis()));
			m.setSensor(getSensor(json.name))
			m.setValue(json.value.toFloat())
			dataCollector.saveData(m)
		}
		catch(Exception ex){
			log.error(ex)
		}
	}

	private Sensor getSensor(String sensorString){
		if(sensorMap[sensorString] == null){
			Sensor newlyDiscoveredSensor = new Sensor()
			newlyDiscoveredSensor.setName(sensorString);
			sensorMap.put(sensorString, newlyDiscoveredSensor)
			dataCollector.addSensor(newlyDiscoveredSensor)
			return newlyDiscoveredSensor;
		}
		return sensorMap[sensorString];
	}

	private class MySerialPortEventListener implements SerialPortEventListener{
		String buffer;
		void serialEvent(SerialPortEvent event) {
			if(event.getEventValue() > 0){
				buffer += new String(serialPort.readBytes())
				if(buffer.contains('\n')){
					def list = buffer.tokenize('\n');
					String line;
					line = list.remove(0)
					if(line != null){
						parseAndSendData(line);
					}
					def residue = ""
					list.each { s -> residue += s }
					buffer = residue;
				}
			}
		}
	}
}
