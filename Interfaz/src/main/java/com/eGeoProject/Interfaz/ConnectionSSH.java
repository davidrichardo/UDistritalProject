package com.eGeoProject.Interfaz;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class ConnectionSSH {
	
	private String sshPassword;
	private String sshHost;
	private Integer sshPort;
	private String sshUser;
	
	public ConnectionSSH() {
		
	}
	
	public ConnectionSSH(String sshHost,int sshPort,String sshUser,String sshPassword) {
		this.sshHost = sshHost;
		this.sshPassword = sshPassword;
		this.sshPort = sshPort;
		this.sshUser = sshUser;
	}


	public Session establishWithPassword() throws JSchException {

		Session session;
		JSch jsSch = new JSch();
		Properties config = new Properties();
		try {
        	config.put("StrictHostKeyChecking", "no");
			session = jsSch.getSession(getSshUser(), getSshHost(), getSshPort());
			session.setPassword(getSshPassword());
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
			session.disconnect();
		} catch (JSchException e) {
			// TODO: handle exception
			e.getCause();
			throw e;
		}
		return session;
	}

	public String getSshPassword() {
		return sshPassword;
	}

	public void setSshPassword(String sshPassword) {
		this.sshPassword = sshPassword;
	}

	public String getSshHost() {
		return sshHost;
	}

	public void setSshHost(String sshHost) {
		this.sshHost = sshHost;
	}
	public Integer getSshPort() {
		return sshPort;
	}

	public void setSshPort(Integer sshPort) {
		this.sshPort = sshPort;
	}

	public String getSshUser() {
		return sshUser;
	}

	public void setSshUser(String sshUser) {
		this.sshUser = sshUser;
	}
	

}
