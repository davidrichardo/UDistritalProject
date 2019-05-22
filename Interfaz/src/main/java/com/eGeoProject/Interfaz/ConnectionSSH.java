package com.eGeoProject.Interfaz;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
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


	public Session establishWithPassword(){

		Session session = null;
		JSch jsSch = new JSch();
		Properties config = new Properties();
		try {
        	config.put("StrictHostKeyChecking", "no");
			session = jsSch.getSession(getSshUser(), getSshHost(), getSshPort());
			session.setPassword(getSshPassword());
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
		} catch (JSchException e) {
			// TODO: handle exception
			e.getCause();
			e.printStackTrace();
		}
		return session;
	}
	
	public void execCommand(){
		
		try {
			Session session = establishWithPassword();
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("ls");
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            System.out.print(new String(tmp, 0, i));
	          }
	          if(channel.isClosed()){
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
			
		} catch (JSchException e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getCause();
		}
		 catch (IOException e) {
			// TODO: handle exception
			 e.printStackTrace();
			 e.getCause();
		}

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
