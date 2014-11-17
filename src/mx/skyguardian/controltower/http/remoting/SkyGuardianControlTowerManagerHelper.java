package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.Messages;
import mx.skyguardian.controltower.bean.Parameters;
import mx.skyguardian.controltower.bean.Position;
import mx.skyguardian.controltower.bean.Units;
import mx.skyguardian.controltower.json.AbsctractJSONDeserializer;
import mx.skyguardian.controltower.json.SimpleJSONDeserializer;
import mx.skyguardian.controltower.security.JasyptEncryptor;
import mx.skyguardian.controltower.util.AppUtils;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class SkyGuardianControlTowerManagerHelper {
	private Logger log = Logger.getLogger(SkyGuardianControlTowerManager.class);
	private IWialonHTTPRequestExecutor httpReqExecutor = null;
	private AbsctractJSONDeserializer jsonDeserializer = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	private SkyGuardianControlTowerManagerHelper(){
		 
	}
	
	public List<Units> getSearchedUnitItems(WialonSession wialonSession) throws IOException {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sid", wialonSession.getEid());
		
		String unitsUrl = AppUtils.getURL(
				appProperties.getProperty("mx.skyguardian.controltower.search.units.url"), properties);
		
		log.debug("SkyGuardianControlTowerManager.getUnits()-unitsUrl="+unitsUrl);
		
		JSONObject itemObj = httpReqExecutor.getHTTPRequest(unitsUrl);
		List<Units> unitsList = new ArrayList<Units>();
		if (!itemObj.isNull("searchSpec")) {
			JSONArray jsonArray = (JSONArray) itemObj.getJSONArray("items");
			if (jsonArray.length() > 0) {
				unitsList = new ArrayList<Units>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonItem = (JSONObject) jsonArray.get(i);
					AbstractWialonEntity unit = new Units();
					((Units) unit).setUnitId(Long.parseLong((jsonItem.isNull("id")) ? "0" : String.valueOf(jsonItem.get("id"))));
					((Units) unit).setUnitName((jsonItem.isNull("nm")) ? "Invalid Unit": String.valueOf(jsonItem.get("nm")));
					unitsList.add((Units) unit);
				}
			}
		}
		return unitsList;
	}
	
	public WialonSession getWialonSession (String userName, String password) throws IOException {
		WialonSession wialonSession = new WialonSession();
		wialonSession.setUserName(userName);
		wialonSession.setPassword(JasyptEncryptor.decryptPBEText(password));
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("user", wialonSession.getUserName());
		properties.put("password", wialonSession.getPassword());
		
		String loginUrl = AppUtils.getURL(
				appProperties.getProperty("mx.skyguardian.controltower.login.url"), properties);
		
		log.debug("SkyGuardianControlTowerManager.getUnits()-loginUrl="+loginUrl);
		
		JSONObject loginJSONObj = httpReqExecutor.getHTTPRequest(loginUrl);
		
		wialonSession.setEid((String) loginJSONObj.get("eid"));
		wialonSession.setServerTime((Integer) loginJSONObj.get("tm"));
		
		return wialonSession;
	}
	
	public Integer getCountOfLoadMsgsByInterval(WialonSession wialonSession, String unitId, String timeFrom) throws IOException{
		Map<String, String> properties = new HashMap<String, String>();
		Integer serverTime = wialonSession.getServerTime();
		
		properties.put("unitId", unitId);
		properties.put("timeFrom", timeFrom);
		log.debug("TimeFrom:["+timeFrom+"]");
		properties.put("timeTo", String.valueOf(serverTime));
		log.debug("TimeTo:["+String.valueOf(serverTime)+"]");
		properties.put("sid", wialonSession.getEid());
		
		String msgsLoadInterval = AppUtils.getURL(
				appProperties.getProperty("us.skyguardian.remote.messages.load.interval"), properties);
		
		log.debug(msgsLoadInterval);
		
		JSONObject itemObj = httpReqExecutor.getHTTPRequest(msgsLoadInterval);
		Integer count = 0;
		if (!itemObj.isNull("count")) {
			count = Integer.valueOf(String.valueOf(itemObj.get("count")));
		}
		log.debug("Count of Loaded Msgs:" + count);
		
		return count;
	}
	
	public void clearMessages(WialonSession wialonSession) throws IOException {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sid", String.valueOf(wialonSession.getEid()));
		String clearMsgs = AppUtils.getURL(appProperties.getProperty("us.skyguardian.remote.clear.messages"), properties);
		log.debug(clearMsgs);
		httpReqExecutor.getHTTPRequest(clearMsgs);
	}

	public JSONObject getJSONMessagesByUnit(WialonSession wialonSession, Integer indexTo) throws IOException {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("indexTo", String.valueOf(indexTo));
		properties.put("sid", String.valueOf(wialonSession.getEid()));
		String getMessages = AppUtils.getURL(appProperties.getProperty("us.skyguardian.remote.get.messages"), properties);
		JSONObject messagesByUnit = httpReqExecutor.getHTTPRequest(getMessages);
		
		return messagesByUnit;
	}
	
	public Messages convertJSON2Message(WialonSession wialonSession, JSONObject obj, String unitId) {
		Messages msg = new Messages();
		if (!obj.isNull("f") && (Integer.valueOf(String.valueOf(obj.get("f"))) != 0)) {
			if (!obj.isNull("t")) {
				msg.setTimeUTC(Long.valueOf(String.valueOf(obj.get("t"))));
			}
			if (!obj.isNull("p")) {
				JSONObject jsonParams = obj.getJSONObject("p");
				Parameters params = new Parameters();
				((SimpleJSONDeserializer) jsonDeserializer).setParameters(jsonParams, params);
				msg.setParameters(params);
			}
			if (!obj.isNull("pos")) {
				JSONObject jsonPos = obj.getJSONObject("pos");
				Position position = new Position();
				((SimpleJSONDeserializer) jsonDeserializer).setPosition(jsonPos, position, wialonSession, unitId);
				msg.setPosition(position);
			}
		}
		
		return msg;
	}
	
	public boolean isValidTimeFrom(String timeFrom) {
		Pattern digitPattern = Pattern.compile("\\d{10}");
		
		return digitPattern.matcher(timeFrom).matches();
	}
	
	public void setHttpReqExecutor(IWialonHTTPRequestExecutor httpReqExecutor) {
		this.httpReqExecutor = httpReqExecutor;
	}
	
	public void setJsonDeserializer(AbsctractJSONDeserializer jsonDeserializer) {
		this.jsonDeserializer = jsonDeserializer;
	}

	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}
}
