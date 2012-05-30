package dk.statsbiblioteket.larm.mcm.validator.mock;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("PortalService.svc")
public class MCMStatisticsMock {

	private static final NumberFormat formatter = new DecimalFormat("###0.000");
	
	private static int sessionsID = 0; 
	private static final List<List<String>> streamLogs = new ArrayList<List<String>>();
	
	// The Java method will process HTTP GET requests
	@GET @Path("/Session_Start")
	@Produces("text/xml")
	public String createSession(
			@QueryParam("clientSettingID") int clientSettingID,
			@QueryParam("repositoryID") int repositoryID) {
		System.out.println("Request received wit parameters: [/Session_Start] " + new Date());
		System.out.println(" - clientSettingID : " + clientSettingID);
		System.out.println(" - repositoryID    : " + repositoryID);
		Date now = new Date();
		String returnValue = 
	    	"<Geckon.Portal.Data.Dto.Session>" +
	    		"<SessionID>" + sessionsID++ + "</SessionID>" +
	    		"<ClientSettingID>" + clientSettingID + "</ClientSettingID>" +
	    		"<DateCreated>" + now + "</DateCreated>" +
	    		"<DateModified>" + now + "</DateModified>" +
	    	"</Geckon.Portal.Data.Dto.Session>";
		System.out.println("Returning: " + returnValue);
		return returnValue;
	 }

	// The Java method will process HTTP GET requests
	@GET @Path("/StatsObject_SetStats")
	@Produces("text/plain")
	public String createStatusForObjectID(
			@QueryParam("objectTypeID") int objectTypeID,
			@QueryParam("channelTypeID") int channelTypeID,
			@QueryParam("channelIdentifier") int channelIdentifier,
			@QueryParam("objectTitle") String objectTitle,
			@QueryParam("eventTypeID") int eventTypeID,
			@QueryParam("objectCollectionID") int objectCollectionID,
			@QueryParam("objectIdentifier") int objectIdentifier,
			@QueryParam("sessionID") String sessionID) {
		System.out.println("Request received wit parameters: [/StatsObject_SetStats] " + new Date());
		System.out.println(" - objectTypeID   : " + objectTypeID);
		System.out.println(" - channelTypeID   : " + channelTypeID);
		System.out.println(" - channelIdentifier   : " + channelIdentifier);
		System.out.println(" - objectTitle   : " + objectTitle);
		System.out.println(" - eventTypeID   : " + eventTypeID);
		System.out.println(" - objectCollectionID   : " + objectCollectionID);
		System.out.println(" - objectIdentifier   : " + objectIdentifier);
		System.out.println(" - sessionID: " + sessionID);
		// create new stream log
		List<String> streamLog = new ArrayList<String>();
		int nextElementIndex = streamLogs.size();
		streamLogs.add(nextElementIndex, streamLog);
		streamLog.add("StatsObject added with id: "+nextElementIndex);
		int id = nextElementIndex;
	    String returnValue = id+"";
		System.out.println("Returning: " + returnValue);
	    return returnValue;
	 }

	// The Java method will process HTTP GET requests
	@GET @Path("/DurationSession_Create")
	@Produces("text/xml")
	public String setDurationSession( // endedAt=3917&startedAt=0
			@QueryParam("objectSessionID") String objectSessionID,
			@QueryParam("sessionID") String sessionID, 
			@QueryParam("endedAt") int endedAt, 
			@QueryParam("startedAt") int startedAt) {
		System.out.println("Request received wit parameters: [/DurationSession_Create] " + new Date());
		System.out.println(" - objectSessionID   : " + objectSessionID);
		System.out.println(" - sessionID    : " + sessionID);
		System.out.println(" - startedAt: " + startedAt);
		System.out.println(" - endedAt    : " + endedAt);
		if (sessionID==null||objectSessionID==null) {
			return "<error>" +
					"<message>An error occured. Parameters sessionID and objectID must be specified</message>" +
					"<objectSessionID>" + objectSessionID + "</objectSessionID>" +
					"<sessionID>" + sessionID + "</sessionID>" +
					"<startedAt>" + startedAt + "</startedAt>" +
					"<endedAt>" + endedAt + "</endedAt>" +
					"</error>"; 
		}
		int id = Integer.parseInt(sessionID);
		List<String> streamLog = null;
		try {
			streamLog = streamLogs.get(id);
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException " + id + " - " + streamLogs);
		}
		streamLog.add(id + " - " + sessionID + "/" + objectSessionID + " [" + formatter.format(startedAt/1000.0) + "; " + formatter.format(endedAt/1000.0) + "]");
		System.out.println("Current session state:");
		for (String logEntry : streamLog) {
			System.out.println(" - " + logEntry);
		}
		String returnValue = ""; // Everyting ok
		System.out.println("Returning: " + returnValue);
	    return returnValue;
	 }

	// The Java method will process HTTP GET requests
	@GET @Path("/output_internal_status")
	@Produces("text/xml")
	public String outputInternalStatus() {
		System.out.println("Current state - " + new Date());
		StringBuffer sb = new StringBuffer();
		sb.append("<result>\n");
		sb.append("  <headline>Internal mock state</headline>\n");
		for (List<String> streamLog : streamLogs) {
			sb.append("  <session>\n");
			for (String logEntry : streamLog) {
				sb.append("    <log_entry>" + logEntry + "</log_entry>\n");
			}
			sb.append("  </session>\n");
		}
		sb.append("</result>\n");
		System.out.println(sb);
	    return sb.toString();
	 }

}
