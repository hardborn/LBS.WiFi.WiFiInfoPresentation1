package com.example.lbs.wifi.wifiinfopresentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class WiFiListActivity extends ListActivity{
	
	private WifiManager _wifiManager;	
	private List<ScanResult> _wifiList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		ScanWifiList();
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.wifilist,
				new String[]{"title","info","info1","img","frequency","level"},
				new int[] {R.id.title,R.id.info,R.id.info1,R.id.img,R.id.frequency,R.id.level});
		setListAdapter(adapter);
	}
	
	private void ScanWifiList(){
		_wifiManager  = (WifiManager)getSystemService(WIFI_SERVICE);
		_wifiList = _wifiManager.getScanResults();
	}
	
	
	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i=0; i<_wifiList.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
        	map.put("title", _wifiList.get(i).SSID);
            map.put("info", _wifiList.get(i).capabilities);
            map.put("img", R.drawable.ic_launcher);
            map.put("info1", _wifiList.get(i).BSSID);
            map.put("level", _wifiList.get(i).level);
            map.put("frequency", _wifiList.get(i).frequency);
            list.add(map);
		}
        return list;
    }
}