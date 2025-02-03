package com.tecnotree.automatiom.Pojo;

public class MasterFiledServiceConfigure {

	private String serviceName;
	private Integer serviceId;
	private String protocol;
	private Integer timeout;
	private FileUpload fileUpload;

	public MasterFiledServiceConfigure(String serviceName, Integer serviceId, String protocol, Integer timeout,
			FileUpload fileUpload) {
		super();
		this.serviceName = serviceName;
		this.serviceId = serviceId;
		this.protocol = protocol;
		this.timeout = timeout;
		this.fileUpload = fileUpload;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

}
