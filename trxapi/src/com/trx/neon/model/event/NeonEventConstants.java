package com.trx.neon.model.event;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Constants used internally by the NeonAPI library
 * @author Dan
 */
public class NeonEventConstants
{
	/** Default format for displaying date/time strings */
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
}
