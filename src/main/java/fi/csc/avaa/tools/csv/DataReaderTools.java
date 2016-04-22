/**
 * 
 */
package fi.csc.avaa.tools.csv;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * Utility class for getting input data stream from various data sources
 * 
 * @author jmlehtin
 *
 */
public final class DataReaderTools {
	
	private static AvaaLogger log = new AvaaLogger(DataReaderTools.class.getName());
	private static HttpURLConnection httpConn;
	
	public static BufferedReader GetURLReader(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			return new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static BufferedReader GetHttpFileReader(String urlStr) {
		try {
			URL url = new URL(urlStr);
			if(openHttpURLConnection(url)) {			
		        int responseCode = httpConn.getResponseCode();
		 
		        if (responseCode == HttpURLConnection.HTTP_OK) {
		            // opens input stream from the HTTP connection
		            InputStream inputStream = httpConn.getInputStream();
		            DataInputStream dis = new DataInputStream(inputStream);
		            return new BufferedReader(new InputStreamReader(dis));
		        } else {
		        	log.error("Error fetching CSV file. Server replied HTTP code: " + responseCode);
		        }
			} else {
				log.error("Error fetching CSV file. Unable to open connection to " + urlStr);
			}
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}			
		return null;
	}
	
	private static boolean openHttpURLConnection(URL url) {
		try {
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			httpConn = (HttpURLConnection) urlConn;
			httpConn.connect();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static void closeHttpURLConnection() {
		httpConn.disconnect();
	}
}
