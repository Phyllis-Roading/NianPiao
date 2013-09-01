package mine.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * author:zgj
 * ������Ƭ�ĸ�ʽΪIMG_yyyyMMdd_HHmmss
 */
public class FormatPhotoName {
	private static final String pattern = "yyyyMMdd_HHmmss";// ��Ƭ���ָ�ʽ
	private SimpleDateFormat dataFormat;
	private String imgName = null;

	public FormatPhotoName(){
		super();
	}
	public String getPhotoName(Date date) {
		dataFormat = new SimpleDateFormat(pattern);
		imgName = "IMG_"+dataFormat.format(date)+".jpg";//��Ƭ����
		return imgName;
	}
}
