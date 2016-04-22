package fi.csc.avaa.tools.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVWriter;
import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * Class for writing CSV data to output writer.
 * 
 * @author jmlehtin
 *
 */
public class OpenCSVDataWriter {
	
	private char separator = CSVParser.DEFAULT_SEPARATOR;
	private char quoteChar = CSVParser.DEFAULT_QUOTE_CHARACTER;
	private char escapeChar = CSVParser.DEFAULT_ESCAPE_CHARACTER;
	private Writer writer;
	private AvaaLogger log = new AvaaLogger(OpenCSVDataWriter.class.getName());
	
	public OpenCSVDataWriter(Writer outputWriter) {
    	this.writer = outputWriter;
    }
	
    public OpenCSVDataWriter(char separator, char quoteChar, Writer outputWriter) {
    	this.separator = separator;
    	this.quoteChar = quoteChar;
    	this.writer = outputWriter;
    }

    public boolean writeCSVData(List<String> headers, List<List<String>> data) {
    	List<String[]> rowsToWrite = new ArrayList<>();
    	
    	if(writer == null) {
    		log.error("Output writer for writing CSV data is null");
    		return false;
    	}
    	
    	if(headers != null && headers.size() > 0) {
    		rowsToWrite.add(headers.toArray(new String[headers.size()]));
    	}
    	
    	if(data != null && data.size() > 0) {
    		int headerAmt = 0;
    		if(headers != null && headers.size() > 0) {
    			headerAmt = headers.size();
    		}
    		for(List<String> dataRow : data) {
    			if(headerAmt > 0) {
    				if(dataRow.size() != headerAmt) {
    					log.warn("Skipping row because header amount is different than data item amount");
    					continue;
    				}
    			}
    			rowsToWrite.add(dataRow.toArray(new String[dataRow.size()]));
    		}
    	} else {
    		log.warn("Nothing to write to CSV file");
    		return false;
    	}
    	

    	try(CSVWriter csvWriter = new CSVWriter(writer, separator, quoteChar, escapeChar)) {
    		csvWriter.writeAll(rowsToWrite);
    		csvWriter.flush();
		} catch (IOException e) {
			log.error("Error writing to CSV file");
			return false;
		}
    	
    	closeOutputWriter();
    	return true;
    }
    
	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public void setQuoteChar(char quoteChar) {
		this.quoteChar = quoteChar;
	}

	public void setOutputWriter(Writer writer) {
		this.writer = writer;
	}
	
	public void setEscapeChar(char escapeChar) {
		this.escapeChar = escapeChar;
	}
	
	private void closeOutputWriter() {
		if(writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				log.warn("Unable to close writer");
			}
		}
	}

}
