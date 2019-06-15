package com.nadia.totoro.sample.common.app;


import com.nadia.totoro.app.NConstant;

/**
 * 常量
 * Created by Administrator on 2015/9/21.
 */
public class Constants extends NConstant {
	
	public static final String REQUEST_SERVER = ServerHost.SERVER_URL;
	public static final String UPLOAD_SERVER = ServerHost.UPLOAD_URL;
	
	public final static String FILE_SAVEPATH = SAVE_FILE_PATH.concat("com.nadia.totoro");

	
	public static final long TIME_COUNT = 1 * 60 * 1000;    //短信1分钟
	public static final long TIME_COUNT_AUTHCODE = 5 * 60 * 1000;    //验证码有效期5分钟
	
	public final static int PAGE_SIZE = 10;//列表每页条数。
	
	public static final class NetHint {
		public static final String NO_LOCAL_NET = "请确认是否已经打开WiFi或者流量网络";
	}
	
	public static final class preferencesKey {
		
		//登录
		public static final String IS_WELCOME_KEY = "is_welcome";
		public static final String IS_SELECT_COMPANY_KEY = "is_select_company";
		public static final String IS_LOGIN_KEY = "is_login";
		public static final String LOGIN_MOBILE_KEY = "login_mobile";
		public static final String LOGIN_USER_NAME_KEY = "login_name";
		public static final String LOGIN_USER_ID_KEY = "login_user_id";
		public static final String LOGIN_PASSWORD_KEY = "login_password";
		public static final String LOGIN_USER_TYPE_ID_KEY = "login_user_type_id";
		public static final String USER_DETAILS_ID_KEY = "user_details_id";
		public static final String PLATFORM_TYPE_KEY = "platform_type";
		
		//城市
		public static final String PROVINCE = "province";
		public static final String CITY = "city";
		public static final String AREA = "area";
	}
	
	/**
	 * 加网络列表数据方式
	 * EMPTY - 空加载
	 * MORE - 列表滚动到底部加载更多
	 * REFRESH -列表下拉刷新
	 */
	public static enum LoadNetListDataType {
		EMPTY, MORE, REFRESH
	}
	
	public static class net {
		public static final String NET_TYPE = "net_type";
		public static final String WIFI_NAME = "wifi_name";
	}
	
	public static class address {
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
	}
	
	public static class web {
		public static final String WEB_ID = "web_id";
		public static final String WEB_URL = "web_url";
		public static final String WEB_TITLE = "web_title";
	}
	
	public static class pdf {
		public static final String PDF_ID = "pdf_id";
		public static final String PDF_URL = "pdf_url";
		public static final String PDF_TITLE = "pdf_title";
	}
	
	public static class image {
		// 网络图片
		public static String IMG_URL = "imgUrl";
		public static String IMG_URLS = "imgUrls";
		// 本地图片
		public static String IMG_RES_IDS = "imgResIds";
		public static String IMG_FILE_PATHS = "imgFilePaths";
		public static String IMG_POSITION = "img_position";
	}
	
	
	public static class request {
		public static final int REQUEST_CODE_GET_IMAGE_BY_CAMERA = 1;//拍照
		public static final int REQUEST_CODE_GET_IMAGE_BY_SDCARD = 2;//相册
		public static final int REQUEST_CODE_GET_IMAGE_BY_PREVIEW = 3;//预览
	}
}
