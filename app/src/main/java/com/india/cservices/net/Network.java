package com.india.cservices.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.india.cservices.utils.AppLogger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;


public class Network {

	private static final String TAG = "Network";
	private static final int TIMEOUT = 15 * 1000;

	public static boolean isNetworkAvailable(Context context) {
		AppLogger.d(TAG, "isNetworkAvailable: checking network connectivity");

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

		public static boolean isConnectionTypeWiFi(Context context) {
				AppLogger.d(TAG, "checking if on WiFi network");

				ConnectivityManager connectivityManager = (ConnectivityManager) context
								.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo activeNetworkInfo = connectivityManager
								.getActiveNetworkInfo();
				return activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
		}


		public static InputStream getFeedInputStream(String url){
		return getResponseAsInputStream(url);
	}

	public static InputStream getResponseAsInputStream(String url) {

		AppLogger.d(TAG, "getResponseAsInputStream : " + url);

		try {
			return getInputStream(url, false, null);
		} catch (Exception e) {
			AppLogger.e(TAG, "getResponseAsInputStream : " + e.getMessage());
		}

		return null;
	}


	public static OutputResponseString getResponseAsString(String url) {
		String response = null;
		BufferedReader in = null;
			OutputResponseString outputResponseString = new OutputResponseString();
		try {
			in = new BufferedReader(new InputStreamReader(getInputStream(url,false, null)));
			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			response = html.toString();
			outputResponseString.setData(response);
		} catch (Exception e) {
			AppLogger.e(TAG, "getResponseAsString : " + e.getMessage());
			outputResponseString.setException(e);
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (Exception e){
					outputResponseString.setException(e);
					AppLogger.e(TAG, "getResponseAsString : " + e.getMessage());
				}
			}
		}

		return outputResponseString;
	}

	public static String post(String url) {

		AppLogger.d(TAG, "post " + url);
		InputStream in = null;

		try {
			in = getInputStream(url, true, null);

			return convertToString(in);
		} catch (Exception e) {
			AppLogger.e(TAG, "post : " + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					AppLogger.e(TAG, "post : " + e.getMessage());
				}
			}
		}

		return null;
	}
	public static String convertToString(InputStream inputStream)
			throws IOException {
		String retVal = null;
		ByteArrayOutputStream out = null;
		try {
			int readBytes = 0;
			byte[] sBuffer = new byte[512];
			out = new ByteArrayOutputStream();
			while ((readBytes = inputStream.read(sBuffer)) != -1) {
				out.write(sBuffer, 0, readBytes);
			}
			retVal = new String(out.toByteArray());
		} finally {
			if(out != null){
				out.close();
			}
		}
		return retVal;
	}

	static private InputStream getInputStream(String url, Boolean isPost, Map<String,String> params) throws IOException {
		InputStream retVal=null;
		String callingURL = url;
		boolean redirect = false;
		int redirectCount = 0;
		HttpURLConnection conn = null;
		do {
			redirect = false;
			URL urlObj = new URL(callingURL);
			conn = (HttpURLConnection) urlObj.openConnection();
			if(isPost){
				conn.setRequestMethod("POST");
				if(params != null){
					// to be implimented. As Currently not used.
					// Hint : http://stackoverflow.com/questions/2809877/how-to-convert-map-to-url-query-string
					// Hint : http://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
				}
			}
			conn.setInstanceFollowRedirects(true);  // does only same protocall redirect. for http to https redirect need loop as in following code.
			conn.setReadTimeout(TIMEOUT);
			conn.setConnectTimeout(TIMEOUT);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
//			conn.addRequestProperty("User-Agent", "Mozilla");
//			conn.addRequestProperty("Referer", "google.com");
			conn.addRequestProperty("Accept-Encoding", "gzip");


			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER){

					redirect = true;
					redirectCount++;
					callingURL = conn.getHeaderField("Location");
				}
			}
			AppLogger.d(TAG, "Response Code ... " + status);
		} while(redirect && redirectCount < 20);

		String contentEncoding = conn.getHeaderField("Content-Encoding");
		retVal = conn.getInputStream();
		if("gzip".equalsIgnoreCase(contentEncoding)) {
			retVal = new GZIPInputStream(retVal);
		}
		return retVal;
	}
}
