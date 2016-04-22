/**
 * 
 */
package fi.csc.avaa.tools.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import fi.csc.avaa.tools.csv.SuperCSVDataReader.Separator;
import fi.csc.avaa.tools.logging.AvaaLogger;
import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public class SuperCSVDataWriter {

	private CsvPreference csvPreference;
	private static AvaaLogger log = new AvaaLogger(SuperCSVDataWriter.class.getName());
	
	public SuperCSVDataWriter(Separator separator) {
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
	
	public boolean writeCSVData(Writer writer, List<Map<String, Object>> dataToWrite, List<String> headers, Translator translator) {
		CellProcessor[] processors = SuperCSVDataReader.getSuperCsvProcessorWithOptionalColumns(headers.size());
		try(ICsvMapWriter mapWriter = new CsvMapWriter(writer, csvPreference)) {
	        
			if(translator != null) {
				String[] translatedHeaders = headers.stream().map(field -> translator.localize(field)).collect(Collectors.toList()).toArray(new String[headers.size()]);
				mapWriter.writeHeader(translatedHeaders);
			} else {
				mapWriter.writeHeader(headers.toArray(new String[headers.size()]));
			}
            
            for(Map<String, Object> dataRow : dataToWrite) {
                mapWriter.write(dataRow, headers.toArray(new String[headers.size()]), processors);
            }
	    } catch (IOException e) {
			log.error("Unable to write data to CSV");
	    	e.printStackTrace();
			return false;
		}
		return true;
	}
}