package fr.utbm.carpooling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Build;

public class HttpConnection extends AsyncTask<String, Void, String> {
	private final String BASE_URL = "http://api.carpooling.fr.nf";
	private final String API_VERSION = "1.0";
	
	private HttpURLConnection con = null;
	private HttpTaskHandler mHandler = null;
	private ArrayList<BasicNameValuePair> mParamList = null;
	private REQUEST_TYPE mRType = null;
	private String mUrl = null;
	
	public enum REQUEST_TYPE {
		GET,
		POST;
	};
	
	public HttpConnection(String sUrl, ArrayList<BasicNameValuePair> paramList, REQUEST_TYPE type, HttpTaskHandler handler) {
		disableConnectionReuseIfNecessary();
		
		mHandler = handler;
		mParamList = paramList;
		mRType = type;
		mUrl = sUrl;
	}
	
	private String readInput(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        
        try {
			while ((line = br.readLine()) != null) {
			    sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return sb.toString();
	}

	protected void initCon() {
		con.setReadTimeout(10000);
		con.setConnectTimeout(15000);
		con.setDoInput(true);
		con.setRequestProperty("Charset", "UTF-8");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	}
	
	private void disableConnectionReuseIfNecessary() {
		// Work around pre-Froyo bugs in HTTP connection reuse.
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
			System.setProperty("http.keepAlive", "false");
		}
	}

	@Override
	protected String doInBackground(String... params) {
		URL url = null;
		String result = null;
		
		try {
			url = new URL(BASE_URL + "/V" + API_VERSION + mUrl);
			
			try {
				con = (HttpURLConnection) url.openConnection();
				
				initCon();
				
				try {
					if (mRType == REQUEST_TYPE.GET) {
						con.setRequestMethod("GET");
					} else {
						con.setRequestMethod("POST");
						con.setDoOutput(true);
					}
					
					if (mParamList != null) {
						String query = "";
						
						for(BasicNameValuePair p : mParamList) {
							query += ((query == "") ? "" : "&") + p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
						}
						
						PrintWriter pw = new PrintWriter(con.getOutputStream());
						pw.write(query);
						pw.close();
					}
					
					try {
						con.connect();
						
						if (!url.getHost().equals(con.getURL().getHost())) {
							return null;
						}
						
						result = readInput(con.getInputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (ProtocolException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		con.disconnect();
		
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		if(result != null) {
        	mHandler.taskSuccessful(result);
        } else {
        	mHandler.taskFailed();
        }
	}
	
	public interface HttpTaskHandler extends TaskHandler<String> {}
}