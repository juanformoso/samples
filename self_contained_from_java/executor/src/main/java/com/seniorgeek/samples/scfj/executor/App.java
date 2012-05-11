package com.seniorgeek.samples.scfj.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Application entry point
 * 
 * @author jformoso
 */
public class App {
	public static void main(String[] args) {
		try {
			run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void run() throws Exception {
		String[] command = { "./run.sh", "test1" };
		ProcessBuilder probuilder = new ProcessBuilder(command);
		// You can set up your work directory
		probuilder.directory(new File(System.getProperty("user.dir") + "/workspace/"));
		probuilder.redirectErrorStream(true);

		Process process = probuilder.start();

		// Read out dir output
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.printf("Output of running %s is:\n",
				Arrays.toString(command));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		// Wait to get exit value
		int exitValue = process.waitFor();
		System.out.println("\n\nExit Value is " + exitValue);
	}
}
