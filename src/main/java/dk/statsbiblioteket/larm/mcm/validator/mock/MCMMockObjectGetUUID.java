package dk.statsbiblioteket.larm.mcm.validator.mock;

import java.io.StringWriter;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dk.statsbiblioteket.larm.mcm.validator.mock.xml.GetObjectIDReturnValue;

@Path("PortalService.svc/Object_Get_UUID")
public class MCMMockObjectGetUUID {

	private static final String UUID_OF_NON_EXISTING_OBJECT = "uuid:XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
	
	// The Java method will process HTTP GET requests
	@GET 
	@Produces("text/xml")
	public GetObjectIDReturnValue getStatusForObjectID(
			@QueryParam("domsUUID") String domsUUID,
			@QueryParam("sessionID") String sessionID) throws JAXBException {
		System.out.println("Request received wit parameters: [/Object_Get_UUID] " + new Date());
		System.out.println(" - domsUUID   : " + domsUUID);
		System.out.println(" - sessionID: " + sessionID);
		// create new stream log
		Date now = new Date();
	    int objectID;
	    if (UUID_OF_NON_EXISTING_OBJECT.equals(domsUUID)) {
	    	objectID = -1;
	    } else {
	    	objectID = 927;
	    }
	    int clientSettingID = 1;
		GetObjectIDReturnValue objectReturnValue = new GetObjectIDReturnValue(sessionID, clientSettingID , now, now, objectID);
		System.out.println("Returning: " + asString(objectReturnValue));
	    return objectReturnValue;
	 }

	public String asString(Object pObject) throws JAXBException {
		java.io.StringWriter sw = new StringWriter();
		JAXBContext aJAXBContext = JAXBContext.newInstance(GetObjectIDReturnValue.class);
		Marshaller marshaller = aJAXBContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(pObject, sw);
		return sw.toString();
	}

}
