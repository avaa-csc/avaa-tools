package fi.csc.avaa.liferay.ipc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import fi.csc.avaa.tools.logging.AvaaLogger;


/**
 * @author jmlehtin
 *
 * Class for encoding and decoding Java objects to be used in Liferay IPC messages.
 */
public final class IPCDataXCoder {

	private static AvaaLogger LOG = new AvaaLogger(IPCDataXCoder.class.getName());
	
	private IPCDataXCoder() {};
	
	public static String encode(Object obj) {
		ByteArrayOutputStream baostr = new ByteArrayOutputStream();
		try(ObjectOutputStream oostr = new ObjectOutputStream(baostr)) {
		    oostr.writeObject(obj); // Serialize the object
		    return Base64.getEncoder().encodeToString(baostr.toByteArray());
		} catch (IOException ex) {
			LOG.error("Unable to serialize ipc data");
		}
		return null;
	}
	
	public static Object decode(String str) {
		byte[] data = Base64.getDecoder().decode(str);
	    try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
	        return ois.readObject();
	    } catch (IOException | ClassNotFoundException ex) {
	    	LOG.error("Unable to deserialize object: ");
	    	ex.printStackTrace();
	    }
	    return null;
	}
}
