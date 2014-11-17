package mx.skyguardian.controltower.rest;

import java.io.IOException;

import mx.skyguardian.controltower.bean.JSONUnitsReportEntity;
import mx.skyguardian.controltower.http.remoting.IControlTowerManager;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WialonUnitController {
	private static Logger log = Logger.getLogger(WialonUnitController.class);
	
	
	private IControlTowerManager controlTowerManager = null;

	// //////////////////////// @ResponseBody ////////////////////////

	
	@RequestMapping(method = RequestMethod.GET, value = "/units/messages", headers = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	public @ResponseBody JSONUnitsReportEntity getUnits(
			@RequestParam("userName") String userName, 
			@RequestParam("password") String password,
			@RequestParam("timeFrom") String timeFrom) {
		try {
			return (JSONUnitsReportEntity) controlTowerManager.getMessagesForAllUnitsByInterval(userName, password, timeFrom);
		} catch (JSONException je) {
			log.error("JSONException Catch");
			je.printStackTrace();
		} catch (IOException ioe) {
			log.error("IOException");
			ioe.printStackTrace();
		}
		
		return new JSONUnitsReportEntity();
	}
	
	public void setControlTowerManager(IControlTowerManager controlTowerManager) {
		this.controlTowerManager = controlTowerManager;
	}

}
