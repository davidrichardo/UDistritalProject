package com.eGeoProject.Interfaz;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionSSH {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static Session establishWithPassword(String sshHost, int port, String user, String password)
			throws JSchException {

		Session session;
		JSch jsSch = new JSch();
		try {
			session = jsSch.getSession(user, sshHost, port);
			session.setPassword(password);
		} catch (JSchException e) {
			// TODO: handle exception
			LOGGER.error("SSH atempt to host" + sshHost + ":" + port + "failed");
			throw e;
		}
		return connect(session, sshHost, port);
	}
	
	public static Session connect(Session session, String sshHost, int sshPort) throws JSchException {

		session.setConfig("StringHostKeyChecking", "no");
		session.setConfig("ConnectionAttemps", "3");
		try {
			session.connect();
		} catch (JSchException e) {
			// TODO: handle exception
			LOGGER.error("SSH atempt to host" + sshHost + ":" + sshPort + "failed");
			throw e;
		}
		LOGGER.info("Connected to: " + sshHost + ":" + sshPort + " via SSH");
		return session;
	}

}
