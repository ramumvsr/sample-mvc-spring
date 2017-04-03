package com.qv2mobileweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/*
 * This class will check whether android device is connected to the system or not
 * by executing the adb commands. If device is connected it will get the connected device information. 
 */
public class AutoDetect {

	/*
	 * String platformName; String deviceName; String deviceOSVersion;
	 * 
	 * capabilities[0]--> platformName capabilities[1]--> deviceName
	 * capabilities[2]--> deviceOSVersion
	 */
	String[] capabilities = new String[3];

	/**
	 * This method checks whether the device is connected or not
	 * 
	 * @return
	 */
	public boolean checkDevice() {
		System.out.println("Finding active device");
		boolean isDeviceConnected = false;
		Process process = null;
		try {

			process = Runtime.getRuntime().exec("adbsoft/adb.exe devices");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			Stream<String> stream = in.lines();

			if (stream.count() > 2) {

				System.out.println("Active device found");
				isDeviceConnected = true;

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			process.destroy();
		}
		return isDeviceConnected;
	}

	/**
	 * This method gets the Device properties
	 */
	public String[] getDeviceProperties() {
		Process processList = null;
		Process processName = null;

		try {

			processList = Runtime.getRuntime().exec(
					"adbsoft/adb.exe shell  getprop");

			processName = Runtime.getRuntime().exec("adbsoft/adb.exe devices");

			BufferedReader inputs = new BufferedReader(new InputStreamReader(
					processList.getInputStream()));
			Stream<String> stream = inputs.lines();

			Stream<String> s2 = stream.filter((String s) -> {

				boolean props = false;
				if (s.contains("[net.bt.name]")
						|| s.contains("[ro.boot.serialno]")
						|| s.contains("[ro.build.version.release]")) {
					props = true;
				}

				return props;

			});

			Object[] array = s2.toArray();

			for (Object element : array) {
				String s = (String) element;
				if (s.contains("[net.bt.name]")) {
					capabilities[0] = s.substring(s.lastIndexOf("[") + 1,
							s.lastIndexOf("]"));
				} else if (s.contains("[ro.build.version.release]")) {

					capabilities[2] = s.substring(s.lastIndexOf("[") + 1,
							s.lastIndexOf("]"));

				}

			}

			BufferedReader devicNameInput = new BufferedReader(
					new InputStreamReader(processName.getInputStream()));
			Stream<String> streamName = devicNameInput.lines();

			Object[] arrayName = streamName.toArray();

			String deviceNameFull = (String) arrayName[1];

			capabilities[1] = deviceNameFull.substring(0,deviceNameFull.indexOf("device")).trim();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			processList.destroy();
			processName.destroy();
		}

		return capabilities;

	}

}
