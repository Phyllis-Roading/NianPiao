package mine.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatPhotoName {
	private static final String pattern = "yyyyMMdd_HHmmss";
	private SimpleDateFormat dataFormat;
	private String imgName = null;

	public FormatPhotoName() {
		super();
	}

	public String getPhotoName(Date date) {
		dataFormat = new SimpleDateFormat(pattern);
		imgName = "IMG_" + dataFormat.format(date) + ".jpg";
		return imgName;
	}
}
