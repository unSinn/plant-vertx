package ch.ma3.plant.datasources

import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;
import org.vertx.java.deploy.Verticle;
import ch.ma3.plant.entities.Measurement
import ch.ma3.plant.verticles.DataCollector;
import gnu.io.SerialPort
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener
import gnu.io.CommPortIdentifier


class ArduinoDataSource extends Verticle implements DataSource{

	private DataCollector dataCollector
	private byte[] readBuffer = new byte[400]
	private InputStream input
	boolean running
	int baudRate = 57600;
	private Logger log = LoggerFactory.getLogger(ArduinoDataSource.class)

	ArduinoDataSource(){
		for (CommPortIdentifier id in CommPortIdentifier.getPortIdentifiers()){
			if(id.getPortType() == CommPortIdentifier.PORT_SERIAL){
				SerialPort p = (SerialPort) id.open("Serial: " + id.getName(), 1000)
				p.setSerialPortParams(
						baudRate,
						SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE)
				input = p.getInputStream()
				log.info("Port " + p.getName() + " open")
			}
		}
	}

	@Override
	DataSource setDataCollector(DataCollector collector) {
		dataCollector = collector
		return this
	}

	@Override
	void start() throws Exception {
		if(!dataCollector){
			throw new Exception("DataCollector not set!")
		}
		while(running){
			readSerial()
		}
	}


	private void readSerial() {
		try {
			int availableBytes = input.available()
			if (availableBytes > 0) {
				// Read the serial port
				input.read(readBuffer, 0, availableBytes)

				// Print it out
				log.info(
						new String(readBuffer, 0, availableBytes))
			}
		} catch (IOException e) {
			e.printStackTrace()
		}
	}
}
