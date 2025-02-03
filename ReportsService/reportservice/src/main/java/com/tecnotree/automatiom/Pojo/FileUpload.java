package com.tecnotree.automatiom.Pojo;

public class FileUpload {

	private String nodeId;
	private Integer timeout;
	private String sftpPath;

	public FileUpload(String nodeId, Integer timeout, String sftpPath) {
		super();
		this.nodeId = nodeId;
		this.timeout = timeout;
		this.sftpPath = sftpPath;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getSftpPath() {
		return sftpPath;
	}

	public void setSftpPath(String sftpPath) {
		this.sftpPath = sftpPath;
	}
}
