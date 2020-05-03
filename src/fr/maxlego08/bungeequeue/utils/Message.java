package fr.maxlego08.bungeequeue.utils;

public enum Message {

	TIME_DAY("%02d jour(s) %02d heure(s) %02d minute(s) %02d seconde(s)"),
	TIME_DAY_DOUBLE("%02d:%02d:%02d:%02d"),
	TIME_DAY_SIMPLE("%02d jour(s)"),
	TIME_HOUR("%02d heure(s) %02d minute(s) %02d seconde(s)"),
	TIME_HOUR_SIMPLE("%02d:%02d:%02d"),
	TIME_MINUTE("%02d minute(s) %02d seconde(s)"),
	TIME_SECOND("%02d seconde(s)"),
	;

	private String message;

	private Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String toMsg() {
		return message;
	}

	public String msg() {
		return message;
	}

}

