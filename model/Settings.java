package model;

public class Settings {
	
	private static long sinnerRate = 2000;
	private static long horsemenRate = 4000;
	
	private static long sinnerTime = 300;
	private static long apocalyposeLength = 5000;
	
	public static long getSinnerRate() {
		return sinnerRate;
	}
	
	public static void setSinnerRate(long sinnerRate) {
		Settings.sinnerRate = sinnerRate;
	}
	
	public static long getSinnerTime() {
		return sinnerTime;
	}
	
	public static void setSinnerTime(long sinnerTime) {
		Settings.sinnerTime = sinnerTime;
	}
	
	public static long getHorsemenRate() {
		return horsemenRate;
	}
	
	public static void setHorsemenRate(long horsemenRate) {
		Settings.horsemenRate = horsemenRate;
	}
	
	public static long getApocalyposeLength() {
		return apocalyposeLength;
	}
	
	public static void setApocalyposeLength(long apocalyposeLength) {
		Settings.apocalyposeLength = apocalyposeLength;
	}

	
	
	
	
}
