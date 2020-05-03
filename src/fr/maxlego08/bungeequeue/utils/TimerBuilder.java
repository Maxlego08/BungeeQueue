package fr.maxlego08.bungeequeue.utils;

public class TimerBuilder {

	public static String getFormatLongDays(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_DAY.msg(),
				new Object[] { Long.valueOf(totalSecs / 86400l), Long.valueOf(totalSecs % 86400l / 3600l),
						Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L) });
	}
	
	public static String simpleDay(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_DAY_DOUBLE.msg(),
				new Object[] { Long.valueOf(totalSecs / 86400l), Long.valueOf(totalSecs % 86400l / 3600l),
						Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L) });
	}

	public static String getFormatLongDaySimple(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_DAY_SIMPLE.msg(), new Object[] { Long.valueOf(totalSecs / 86400l) });
	}

	public static String getFormatLongHours(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_HOUR.msg(), new Object[] { Long.valueOf(totalSecs / 3600L),
				Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L) });
	}

	public static String getFormatLongHoursSimple(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_HOUR_SIMPLE.msg(), new Object[] { Long.valueOf(totalSecs / 3600L),
				Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L) });
	}

	public static String getFormatLongMinutes(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_MINUTE.msg(),
				new Object[] { Long.valueOf(totalSecs % 3600L / 60L), Long.valueOf(totalSecs % 60L) });
	}

	public static String getFormatLongSecondes(long temps) {
		long totalSecs = temps / 1000L;
		return String.format(Message.TIME_SECOND.msg(), new Object[] { Long.valueOf(totalSecs % 60L) });
	}

	public static String getStringTime(long second) {
		if (second < 60)
			return (TimerBuilder.getFormatLongSecondes(second * 1000l));
		else if (second >= 60 && second < 3600)
			return (TimerBuilder.getFormatLongMinutes(second * 1000l));
		else if (second >= 3600 && second < 86400)
			return (TimerBuilder.getFormatLongHours(second * 1000l));
		else
			return (TimerBuilder.getFormatLongDaySimple(second * 1000l));
	}
}
