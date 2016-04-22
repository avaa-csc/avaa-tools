/**
 * 
 */
package fi.csc.avaa.tools.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * @author jmlehtin
 *
 */
public class SuperCSVDataReader {

	private CsvPreference csvPreference;
	private static AvaaLogger log = new AvaaLogger(SuperCSVDataReader.class.getName());
	
	public enum Separator {
		SEMICOLON,
		COMMA,
		TAB
	}
	
	public static CellProcessor[] getSuperCsvProcessorWithOptionalColumns(int colAmount) {
		CellProcessor[] processors=new CellProcessor[colAmount];
		for(int i=0; i< colAmount; i++){
			processors[i]=new Optional();
		}
		return  processors;
	}
	
	public SuperCSVDataReader(Separator separator) {
		switch (separator) {
		case COMMA:
			this.csvPreference = CsvPreference.EXCEL_PREFERENCE;
			break;
		case SEMICOLON:
			this.csvPreference = CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;
			break;
		case TAB:
			this.csvPreference = CsvPreference.TAB_PREFERENCE;
			break;
		default:
			this.csvPreference = CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;
			break;
		}
	}
	
	public List<Map<String, Object>> readCSVData(InputStream istream, List<String> headers) {
		CellProcessor[] processors = SuperCSVDataReader.getSuperCsvProcessorWithOptionalColumns(headers.size());
		List<Map<String, Object>> dataRows = new ArrayList<>();
		try(ICsvMapReader mapReader = new CsvMapReader(new BufferedReader(new InputStreamReader(istream, StandardCharsets.UTF_8)), csvPreference)) {
            // the header columns are used as the keys to the Map
            final String[] header = headers.toArray(new String[headers.size()]);
            mapReader.getHeader(false);
            Map<String, Object> dataRow;
            log.info("Starting to read CSV file contents...");
            while( (dataRow = mapReader.read(header, processors)) != null ) {
            	dataRows.add(dataRow);
            }
            log.info("Done reading!");
        } catch (Exception e1) {
			log.error("Failed to read CSV file");
			e1.printStackTrace();
			return null;
		}
		return dataRows;
	}
}
