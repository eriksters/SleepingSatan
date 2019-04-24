package model;

/**
 * Class for storing all timing settings 
 * (Probably not the correct to do this but it works for now)
 * 
 * @author eriks
 *
 */
public class Settings {
	
	private static double multiplier = 1;
	
	private static long sinnerRate = 3000;
	private static long horsemenRate = 4000;
	
	private static long sinnerTime = 400;
	private static long apocalyposeLength = 5000;
	
	public static long getSinnerRate() {
		return (long) (sinnerRate * multiplier);
	}
	
	public static void setSinnerRate(long sinnerRate) {
		Settings.sinnerRate = sinnerRate;
	}
	
	public static long getSinnerTime() {
		return (long) (sinnerTime * multiplier);
	}
	
	public static void setSinnerTime(long sinnerTime) {
		Settings.sinnerTime = sinnerTime;
	}
	
	public static long getHorsemenRate() {
		return (long) (horsemenRate * multiplier);
	}
	
	public static void setHorsemenRate(long horsemenRate) {
		Settings.horsemenRate = horsemenRate;
	}
	
	public static long getApocalyposeLength() {
		return (long) (apocalyposeLength * multiplier);
	}
	
	public static void setApocalyposeLength(long apocalyposeLength) {
		Settings.apocalyposeLength = apocalyposeLength;
	}
}
