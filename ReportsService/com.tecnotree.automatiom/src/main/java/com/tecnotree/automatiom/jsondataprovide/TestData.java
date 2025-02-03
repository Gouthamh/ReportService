package com.tecnotree.automatiom.jsondataprovide;

public class TestData {
    private Boolean zip;
    private int password;
    private String templateName;
    private String attachmentType;
	public Boolean getZip() {
		return zip;
	}
	public void setZip(Boolean zip) {
		this.zip = zip;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	@Override
	public String toString() {
		return "TestData [zip=" + zip + ", password=" + password + ", templateName=" + templateName
				+ ", attachmentType=" + attachmentType + "]";
	}

    
}
