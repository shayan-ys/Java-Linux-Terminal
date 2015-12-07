package Java-Linux-Terminal;

import java.lang.Runtime;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LinuxTerminal {
	private String pass;
	
	public LinuxTerminal(String pass) {
		this.pass = pass;
	}
	public String executeSuCommand(String command) {
		String sucommand = "echo "+ this.pass +" | sudo -S "+ command;
		String[] commands = {"sh", "-c", sucommand};
		String output = executeCommand(commands);
		System.out.println("done, output is: "+output);
		return output;
	}
	public String executeCommand(String command) {
		String[] commands = new String[1];
		commands[0] = command;
		return executeCommand(commands);
	}
	public String executeCommand(String[] commands) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(commands);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}
}
