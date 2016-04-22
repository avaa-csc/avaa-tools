package fi.csc.avaa.tools.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * Class for reading CSV data from input reader stream.
 * 
 * @author jmlehtin
 *
 */
public class OpenCSVDataReader {
	
	private char separator = CSVParser.DEFAULT_SEPARATOR;
	private char quoteChar = CSVParser.DEFAULT_QUOTE_CHARACTER;
	private char escapeChar = CSVParser.DEFAULT_ESCAPE_CHARACTER;
	private int beginLine = 0;
	private int expItemsInRow;
	private BufferedReader inputReader;
	private AvaaLogger log = new AvaaLogger(OpenCSVDataReader.class.getName());
	
    public OpenCSVDataReader(char separator, char quoteChar, int beginLine, int itemsInRow, BufferedReader inputReader) {
    	this.separator = separator;
    	this.quoteChar = quoteChar;
    	this.beginLine = beginLine;
    	this.expItemsInRow = itemsInRow;
    	this.inputReader = inputReader;
    }

    public List<Map<String, String>> readCSVData(String[] csvHeaders) {
    	List<Map<String, String>> csvRows = new ArrayList<Map<String, String>>();

    	if(inputReader == null) {
    		log.error("Input reader for reading CSV data is null");
    		return null;
    	}

    	CSVReader csvReader = new CSVReader(inputReader, separator, quoteChar, escapeChar, beginLine);
    	try {
    		String [] nextLine = null;
    		int skippedRows = 0;
			while((nextLine = csvReader.readNext()) != null) {
				Map<String, String> csvRow = new HashMap<String,String>();
				if(nextLine.length == expItemsInRow) {
					for(int i=0; i < csvHeaders.length; i++) {
						csvRow.put(csvHeaders[i], nextLine[i]);
					}
					csvRows.add(csvRow);
				} else {
					skippedRows++;
				}
			}
			if(skippedRows > 0) {
				log.warn("Skipped " + skippedRows + " rows when reading CSV file due to wrong number of items in row");
			}
		} catch (IOException e) {
			log.warn("Error reading CSV file");
			return null;
		} finally {
			closeInputReader(csvReader);
		}
    	
    	return csvRows;
    }
    
	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public void setQuoteChar(char quoteChar) {
		this.quoteChar = quoteChar;
	}
	
	public void setEscapeChar(char escapeChar) {
		this.escapeChar = escapeChar;
	}

	public void setBeginLine(int beginLine) {
		this.beginLine = beginLine;
	}

	public void setInputReader(BufferedReader inputReader) {
		this.inputReader = inputReader;
	}
	
	private void closeInputReader(CSVReader csvReader) {
		if(inputReader != null) {
			try {
				inputReader.close();
			} catch (IOException e) {
				log.warn("Unable to close CSV input reader");
			}
		}
		if(csvReader != null) {
			try {
				csvReader.close();
			} catch (IOException e) {
				log.warn("Unable to close CSV reader");
			}
		}
	}

}
