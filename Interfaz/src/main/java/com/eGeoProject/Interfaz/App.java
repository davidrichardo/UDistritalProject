package com.eGeoProject.Interfaz;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
	{
//        System.out.println( "Hello World!" );
		ConnectionSSH connectionSSH = new ConnectionSSH("omega-B6FF.local", 22, "root", "ufY645%XE*tHHsB");

		connectionSSH.establishWithPassword();
		connectionSSH.execCommand();
		System.out.println("Me conecte correctamente");
	}

}
