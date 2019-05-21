package com.eGeoProject.Interfaz;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        ConnectionSSH connectionSSH = new ConnectionSSH("omega-B6FF.local",22,"root","ufY645%XE*tHHsB");
        try {
        	connectionSSH.establishWithPassword();
        	System.out.println("Me conecte correctamente");

		} catch (JSchException e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
			e.getCause();
		}
        
    }
}
