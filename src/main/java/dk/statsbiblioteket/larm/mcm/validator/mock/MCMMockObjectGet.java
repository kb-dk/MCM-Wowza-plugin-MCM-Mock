package dk.statsbiblioteket.larm.mcm.validator.mock;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("PortalService.svc/Object_Get")
public class MCMMockObjectGet {

	// The Java method will process HTTP GET requests
	@GET 
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces("text/xml")
	public String getMCMRetunValue(
			@QueryParam("sessionID") String sessionID,
			@QueryParam("objectID") String objectID, 
			@QueryParam("includeFiles") boolean includeFiles) {
		System.out.println("Request received wit parameters: [/Object_Get] " + new Date());
		System.out.println(" - sessionID   : " + sessionID + " (inserted in the Filename element)");
		System.out.println(" - objectID    : " + objectID);
		System.out.println(" - includeFiles: " + includeFiles);
		if (sessionID==null||objectID==null) {
			return "<error>" +
					"<message>An error occured. Parameters sessionID and objectID must be specified</message>" +
					"<sessionID>" + sessionID + "</sessionID>" +
					"<objectID>" + objectID + "</objectID>" +
					"<includeFiles>" + includeFiles + "</includeFiles>" +
					"</error>"; 
		}
		String returnValue;
		System.out.println("Generating return value...");
		if (sessionID.equalsIgnoreCase("invalid")) {
			returnValue = "<Exception>" +
								"<Message>The SessionID is not valid</Message>" +
								"<Fullname>Geckon.Security.Web.SessionNotAuthenticatedException</Fullname>" +
								"<Stacktrace>" +
									"at Geckon.Portal.Web.Service.PortalServiceBase.GetUserInfo(UserInfo userInfo, Products productFlags) in C:\\Geckon\\Repository\\Active\\Geckon.Portal\\trunk\\src\\app\\Geckon.Portal.Web.Service\\PortalServiceBase.cs:line 107&#xD;" +
									"at Geckon.Portal.Web.Service.PortalServiceBase.GetUserInfo(String sessionID, Products productFlags) in C:\\Geckon\\Repository\\Active\\Geckon.Portal\\trunk\\src\\app\\Geckon.Portal.Web.Service\\PortalServiceBase.cs:line 126&#xD;" +
									"at Geckon.Portal.Web.Service.PortalService.Object_Get(String sessionID, String query, String sort, Nullable`1 pageSize, Nullable`1 pageIndex, Nullable`1 includeFiles, Nullable`1 includeMetadata, Nullable`1 includePublishSettings, Nullable`1 includeRelations, Int32[] channelID, Int32[] objectID, Nullable`1 objectTypeID, Nullable`1 folderID, Nullable`1 metadataSchemaID, Int32[] metadataLanguageIDs, String metadataFreeTextQuery) in C:\\Geckon\\Repository\\Active\\Geckon.Portal\\trunk\\src\\app\\Geckon.Portal.Web.Service\\PortalService.cs:line 278&#xD;" +
									"at SyncInvokeObject_Get(Object , Object[] , Object[] )&#xD;" +
									"at System.ServiceModel.Dispatcher.SyncMethodInvoker.Invoke(Object instance, Object[] inputs, Object[]&amp; outputs)&#xD;" +
									"at System.ServiceModel.Dispatcher.DispatchOperationRuntime.InvokeBegin(MessageRpc&amp; rpc)&#xD;" +
									"at System.ServiceModel.Dispatcher.ImmutableDispatchRuntime.ProcessMessage5(MessageRpc&amp; rpc)&#xD;" +
									"at System.ServiceModel.Dispatcher.ImmutableDispatchRuntime.ProcessMessage31(MessageRpc&amp; rpc)&#xD;" +
									"at System.ServiceModel.Dispatcher.MessageRpc.Process(Boolean isOperationContextSet)" +
								"</Stacktrace>" +
							"</Exception>";
		} else {
			returnValue = "<ICollection TotalObjectCount=\"1\">" +
								"<MCM.Data.DTO.ExtendedObjectInfo>" +
									"<Phrases />" +
									"<FileInfos>" +
										"<MCM.Data.DTO.FileInfo>" +
											"<ObjectID>" + objectID + "</ObjectID>" +
											"<UploadURL> \\\\130.225.231.88\\g$\\DKA\\Kulturarv_MP3\\Batch01\\Disc02\\mp3_128kbps\\P3_2000_2200_890325_001.mp3</UploadURL>" +
											"<DownloadURL>http://www.danskkulturarv.dk/PROXY/Preview01/Kulturarv_MP3/Batch01/Disc02/mp3_128kbps/P3_2000_2200_890325_001.mp3</DownloadURL>" +
											"<StreamingURL>../Batch01/Disc02/mp3_128kbps/P3_2000_2200_890325_001.mp3</StreamingURL>" +
											"<Format>MP3 128 kbit CBR</Format><MimeType>audio/mpeg</MimeType>" +
											"<MediaTypeID>15225</MediaTypeID>" +
											"<ID>" + objectID + "</ID>" +
											"<Filename>" + sessionID + "</Filename>" +
											"<OriginalFilename>P3_2000_2200_890325_001.mp3</OriginalFilename>" +
											"<FolderPath>\\Batch01\\Disc02\\mp3_128kbps\\</FolderPath>" +
											"<DateCreated>2010-07-16T17:37:12Z</DateCreated>" +
											"<FileTypeID>1</FileTypeID>" +
											"<FormatID>6</FormatID>" +
											"<ByteSize>3</ByteSize>" +
										"</MCM.Data.DTO.FileInfo>" +
									"</FileInfos>" +
									"<MetadataModifiedDate>2010-07-16T17:37:12Z</MetadataModifiedDate>" +
									"<ObjectTypeName>Asset</ObjectTypeName>" +
									"<ID>" + objectID + "</ID>" +
									"<DateCreated>2010-07-16T17:37:00Z</DateCreated>" +
									"<OwnerUserID>2</OwnerUserID>" +
									"<ObjectTypeID>1</ObjectTypeID>" +
								"</MCM.Data.DTO.ExtendedObjectInfo>" +
							"</ICollection>";
		}
		System.out.println("Return value generated: \n" + returnValue);

		/*
		String filename = "cachedReturnValue.xml";
		File file = new File(filename);
		try {
			byte[] buffer = new byte[(int) file.length()];
			BufferedInputStream f = null;
			try {
				InputStream is = new FileInputStream(file);
				f = new BufferedInputStream(is);
				f.read(buffer);
			} finally {
				if (f != null)
					f.close();
			}
		    returnValue = new String(buffer);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			returnValue = "<error>" +
							"<message>An error occured. Could not find filename: " + file.getAbsolutePath() + "</message>" +
							"<sessionID>" + sessionID + "</sessionID>" +
							"<objectID>" + objectID + "</objectID>" +
							"<includeFiles>" + includeFiles + "</includeFiles>" +
							"</error>"; 
		} 
		*/
	    return returnValue;
	 }
}
