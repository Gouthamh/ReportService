package com.tecnotree.automatiom.routers;




public class Routers {

	public static final String base_url = "https://dap.pe-lab3.bdc-rancher.tecnotree.com";
	public static final String dap_reports = "/dap-reports";
	public static final String template_to_report="/template-to-report";
	public static final String dapBulkProcess = "/dapBulkProcess";
	public static final String triggerBulkService = "triggerBulkService";
	public static final String fileUploadToProcessBulkData = "fileUploadToProcessBulkData";
	public static final String fetchAllMasterConfiguratoins = "fetchAllMasterConfiguratoins";
	public static final String UpdateBulkMasterConfig = "/UpdateBulkMasterConfig";
	public static final String configure = "/configure";
	//Bulk Services
	public static final String notification_bulk_service = "/notification-bulk-service";
	//rest notifications
	public static final String notification="/notification";
	public static final String triggerNotification = "/triggerNotification";
	//fileuploadservice
	public static final String  dapFileUploadServiceUploadFiles = "/dapFileUploadService/uploadFiles/";
	public static final String  dapFileUploadService = "/dapFileUploadService/";
	public static final String  updateConfiguration = "updateConfiguration";
	public static final String  saveConfiguration = "saveConfiguration";
	
	//Status Code
	public static final int code_200 = 200;
	// mongoDB
	public static final String mongoURL = "mongodb://DAP_IAT:DAP_IAT@172.20.21.212:27017,172.20.21.216:27017/DAP_IAT?replicaSet=repl";
	public static final String databasename = "DAP_IAT";
	// mysql
    public static final String jdbcUrl = "jdbc:mysql://172.20.21.213:3306/smdb";
	public static final String username = "smdb";
	public static final String password = "Smdb$123";
	// table name
	public static final String UploadTest = "UploadTest";
	//SFTP INFO
	public static final String sftpHost_172_20_21_57 = "172.20.21.57";
	public static final String sftpUserName = "admin";
	public static final String sftpPassword = "admin";
	public static final String sshKey__172_20_21_57 = "172.20.21.57 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCfeK+wXukXepUaNZmSTGEjDeUTmJdJlMepkUZA6m5p6tzJa77dIAEx1DOdS+uYCOQWJeguACk204p8Qg83w2Is/YtfzoprK9suIBuZLgDoFT+rSXWptVR3weHQqmwh9aN3AwcRN2ql+czgT3SjIMOahz9peLeRKe+20TiOLv8+c0h8+IQxkqX7qrY0sYrrZyJsQ9HQVrLGEb9RLmOgyKPnqCDIRFH72+nYSpcN+k7Yxc8GiL+qUtKs/GZwBYW2oQMVJR/7XeFVfMYgKBbh/R4L9hYztJX5jCn6iovAwhMB9bd0KqDHhJsiU39R8JoUeGXkW83j3/dZdO1QvTY+AQHmZRW7mZ5MInWeT6BwAJqYICfibSfD/0FYXG7fonPAlzAl6lDiMyBpmZnZOMn0vF8JgHPu1AUxgPrcbNbLhwl19qfz2GnIHoDfg2h8IPXhASh56oIGFSbREIeTYjx0JkJgkZsbGKMYKnEjbSApMUNfhUu16/khm+/tptDqmMDnYmU2E4SQ+Jici9FD1bGO2pDUS2Z6NQz5HyzQyhHPnSH/eQnowRc+YnXa15npey/EEsKE/NGEtS/xk1DXsqEk30wMZYEfwEga5YDCCwIEgzYpHIgF2MmCGYnb/AgtjPRQEYvAWz42S2yD1FNnmsxGxTvOfg60+PuzJW5l2l6zrzvPxw==";
	public static final String sftpBasePath = "/bulk-store/";
	public static final String sftpProccessedFilesPath = "/bulk-store/ProccessedFiles/";
	public static final String sftpErrorFilessPath = "/bulk-store/ErrorFiles/";
	public static final String sftpDuplicateFilesPath = "/bulk-store/DuplicateFiles/";
	public static final String file = "[.gz]";
	//ports
	public static final int sftpPort_31702 = 31702;
	public static final int sftpPort_31704=31704;

}
