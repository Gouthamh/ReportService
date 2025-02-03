package test;

import java.io.ByteArrayInputStream;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tecnotree.automatiom.utilities.SFTP;

public class Sftpchechjsch {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String sftpHost = "10.4.3.94";
		int sftpPort = 22;
		String sftpUserName = "tecnotree";
		String sftpPassword = "tecnotree";
		//String sshKey = "172.20.21.57 ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCfeK+wXukXepUaNZmSTGEjDeUTmJdJlMepkUZA6m5p6tzJa77dIAEx1DOdS+uYCOQWJeguACk204p8Qg83w2Is/YtfzoprK9suIBuZLgDoFT+rSXWptVR3weHQqmwh9aN3AwcRN2ql+czgT3SjIMOahz9peLeRKe+20TiOLv8+c0h8+IQxkqX7qrY0sYrrZyJsQ9HQVrLGEb9RLmOgyKPnqCDIRFH72+nYSpcN+k7Yxc8GiL+qUtKs/GZwBYW2oQMVJR/7XeFVfMYgKBbh/R4L9hYztJX5jCn6iovAwhMB9bd0KqDHhJsiU39R8JoUeGXkW83j3/dZdO1QvTY+AQHmZRW7mZ5MInWeT6BwAJqYICfibSfD/0FYXG7fonPAlzAl6lDiMyBpmZnZOMn0vF8JgHPu1AUxgPrcbNbLhwl19qfz2GnIHoDfg2h8IPXhASh56oIGFSbREIeTYjx0JkJgkZsbGKMYKnEjbSApMUNfhUu16/khm+/tptDqmMDnYmU2E4SQ+Jici9FD1bGO2pDUS2Z6NQz5HyzQyhHPnSH/eQnowRc+YnXa15npey/EEsKE/NGEtS/xk1DXsqEk30wMZYEfwEga5YDCCwIEgzYpHIgF2MmCGYnb/AgtjPRQEYvAWz42S2yD1FNnmsxGxTvOfg60+PuzJW5l2l6zrzvPxw==";
		String sftppath = "/bulk-store/FailedFileTransfer/";
		String file = "[.gz]";
		JSch jsch = new JSch();
		//jsch.setKnownHosts(new ByteArrayInputStream(sshKey.getBytes()));
		Session session;
		try {
			session = jsch.getSession(sftpUserName, sftpHost, sftpPort);
			session.setPassword(sftpPassword);
			java.util.Properties config = new java.util.Properties();
	       // config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	        JSch.setConfig("server_host_key", JSch.getConfig("server_host_key") + ",ssh-rsa") ;
	        JSch.setConfig("PubkeyAcceptedAlgorithms", JSch.getConfig("PubkeyAcceptedAlgorithms") + ",ssh-rsa");
			session.connect();
			System.out.println("SFTP connected....!");	
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		

		

	}

}
