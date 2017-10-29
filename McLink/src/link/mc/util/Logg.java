package link.mc.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class Logg extends SimpleFormatter {
	
	public ArrayList<String> logs = new ArrayList<String>();
	
	@Override
	public synchronized String formatMessage(LogRecord record) {
		logs.add(record.getMessage());
		
		return super.formatMessage(record);
	}
	
	@Override
	public synchronized String format(LogRecord record) {
		logs.add(record.getMessage());
		
		return super.format(record);
	}
	
}
