package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.JSONUnitsReportEntity;
import mx.skyguardian.controltower.bean.Messages;
import mx.skyguardian.controltower.bean.Units;
import mx.skyguardian.controltower.exception.WialonAccessDeniedException;
import mx.skyguardian.controltower.exception.WialonBadRequestException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class SkyGuardianControlTowerManager implements IControlTowerManager {
	
	private static Logger log = Logger.getLogger(SkyGuardianControlTowerManager.class);
	private SkyGuardianControlTowerManagerHelper managerHelper = null;
	
	public AbstractWialonEntity getMessagesForAllUnitsByInterval(
			String userName, String password, String timeFrom) throws IOException {
		
		if (!managerHelper.isValidTimeFrom(StringUtils.trimToEmpty(timeFrom))) {
			throw new WialonBadRequestException();
		}
		
		WialonSession wialonSession = managerHelper.getWialonSession(userName, password);
	    int totalMsgsCount = 0;
	    JSONUnitsReportEntity units = new JSONUnitsReportEntity();
		if (!wialonSession.getEid().isEmpty()) {
			List<Units> unitsList = managerHelper.getSearchedUnitItems(wialonSession);
			log.debug("Number of Units:"+ unitsList.size());
			if (unitsList != null && unitsList.size() > 0) {
				for (int i = 0; i < unitsList.size(); i++) {
					String unitId = String.valueOf(unitsList.get(i).getUnitId());
					log.debug("Unit number: "+ unitId);
					int count = managerHelper.getCountOfLoadMsgsByInterval(wialonSession, unitId, timeFrom);
					totalMsgsCount += count;
					if (count > 0) {
						JSONObject messages = managerHelper.getJSONMessagesByUnit(wialonSession, count);
						if (!messages.isNull("jsonArray")){
							String json = String.valueOf(messages.get("jsonArray"));
							JSONArray messagesArray = new JSONArray(json);
							unitsList.get(i).setMessages(new ArrayList<Messages>());
							for (int j = 0; j < messagesArray.length(); j++) {
								JSONObject message = (JSONObject)messagesArray.get(j);
								Messages msg = managerHelper.convertJSON2Message(wialonSession, message, unitId);
								if(!msg.isEmpty()) {
									unitsList.get(i).getMessages().add(msg);
								}
							}
						} else {
							log.debug("unhandled Else"+messages);
						} // TODO Handle when the messeges are just a single JSONObject
					}
					managerHelper.clearMessages(wialonSession);
				}
				units.setUnits(unitsList);
			}
		} else {
			log.error("Authentication error...");
			throw new WialonAccessDeniedException();
		}
		log.debug("Total Count Of Msgs:"+totalMsgsCount);
		return units;
	}

	public void setManagerHelper(SkyGuardianControlTowerManagerHelper managerHelper) {
		this.managerHelper = managerHelper;
	}	
}

