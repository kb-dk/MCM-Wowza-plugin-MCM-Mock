package dk.statsbiblioteket.larm.mcm.validator.mock.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Geckon.Portal.Data.Dto.Session")
public class GetObjectIDReturnValue {
	
	private String sessionID;
	private int clientSettingID;
	private Date dateCreated;
	private Date dateModified;
	private int objectID;

	public GetObjectIDReturnValue() {
		super();
	}
	
	public GetObjectIDReturnValue(String sessionID, int clientSettingID, Date dateCreated, Date dateModified, int objectID) {
		super();
		this.sessionID = sessionID;
		this.clientSettingID = clientSettingID;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.objectID = objectID;
	}
	
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public int getClientSettingID() {
		return clientSettingID;
	}
	public void setClientSettingID(int clientSettingID) {
		this.clientSettingID = clientSettingID;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public int getObjectID() {
		return objectID;
	}
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}

}
