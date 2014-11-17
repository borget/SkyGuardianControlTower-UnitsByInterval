package mx.skyguardian.controltower.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.bean.Parameters;
import mx.skyguardian.controltower.bean.Position;
import mx.skyguardian.controltower.http.remoting.IWialonHTTPRequestExecutor;
import mx.skyguardian.controltower.http.remoting.WialonSession;
import mx.skyguardian.controltower.util.AppUtils;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class SimpleJSONDeserializer extends AbsctractJSONDeserializer {
	private IWialonHTTPRequestExecutor httpReqExecutor = null;
	
	@Resource(name = "appProperties")
	private Properties appProperties;
	
	private static Logger log = Logger.getLogger(SimpleJSONDeserializer.class);
		
	@Override
	public void setParameters(JSONObject jsonParams, AbstractWialonEntity parameter) {
		int reportID = Integer.parseInt((jsonParams.isNull("report_id"))?"0":String.valueOf(jsonParams.get("report_id"))); 
		switch (reportID) {
			case 2:
				setParametersReportId2(parameter, jsonParams);
				break;
			default:
				setParametersReportId2(parameter, jsonParams);
				break;
		}
	}
	
	@Override
	public void setPosition(JSONObject jsonPos, AbstractWialonEntity position, WialonSession wialonSession, String unitId) {
		((Position)position).setLatitud(Double.valueOf(jsonPos.isNull("y")?"0":String.valueOf(jsonPos.get("y"))));
		((Position)position).setLongitud(Double.valueOf(jsonPos.isNull("x")?"0":String.valueOf(jsonPos.get("x"))));
		((Position)position).setAltitud(Double.valueOf(jsonPos.isNull("z")?"0":String.valueOf(jsonPos.get("z"))));
		((Position)position).setSpeed(Integer.valueOf(jsonPos.isNull("s")?"0":String.valueOf(jsonPos.get("s"))));
		((Position)position).setCourse(Integer.valueOf(jsonPos.isNull("c")?"0":String.valueOf(jsonPos.get("c"))));
		((Position)position).setSateliteCount(Integer.valueOf(jsonPos.isNull("sc")?"0":String.valueOf(jsonPos.get("sc"))));
		
		try {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("longitud", String.valueOf(((Position) position).getLongitud()));
			properties.put("latitud", String.valueOf((((Position) position).getLatitud())));
			properties.put("unitId", String.valueOf(unitId));
			properties.put("user", ((WialonSession)wialonSession).getUserName());
			properties.put("password",((WialonSession)wialonSession).getPassword());
			
			String geoPosUrl = AppUtils.getURL(
					appProperties.getProperty("us.skyguardian.controltower.position.url"), properties);
			
			JSONObject geoPosDesc = httpReqExecutor.getHTTPRequest(geoPosUrl);
	
			if(geoPosDesc.length()!=0){
				String geoPosDescString = (geoPosDesc.isNull("jsonArray"))?"No description available":String.valueOf(geoPosDesc.get("jsonArray"));
				//byte[] latin1 = geoPosDescString.getBytes("ISO-8859-1");
				//((GeoPosition)geoPosition).setPositionDesc(new String (latin1, "UTF-8"));
				((Position)position).setPositionDesc(geoPosDescString);
			}

		} catch (IOException e) {
			log.error("There was an error retriving the Position Description.");
			e.printStackTrace();
		} 
		
	}

	private void setParametersReportId2(AbstractWialonEntity params, JSONObject jsonParams) {
		((Parameters) params).setAdc1(Double.parseDouble((jsonParams.isNull("adc1"))?"0":String.valueOf(jsonParams.get("adc1")))); 
		((Parameters) params).setBl(Integer.parseInt((jsonParams.isNull("bl"))?"0":String.valueOf(jsonParams.get("bl"))));
		((Parameters) params).setDl(Integer.parseInt((jsonParams.isNull("dl"))?"0":String.valueOf(jsonParams.get("dl"))));
		((Parameters) params).setEngine(Integer.parseInt((jsonParams.isNull("engine"))?"0":String.valueOf(jsonParams.get("engine"))));
		((Parameters) params).setGpsTm(Long.parseLong((jsonParams.isNull("gps_tm"))?"0":String.valueOf(jsonParams.get("gps_tm"))));
		((Parameters) params).setGsm(Integer.parseInt((jsonParams.isNull("gsm"))?"0":String.valueOf(jsonParams.get("gsm"))));
		((Parameters) params).setGsmStatus(Integer.parseInt((jsonParams.isNull("gsm_status"))?"0":String.valueOf(jsonParams.get("gsm_status"))));
		((Parameters) params).setHa(Integer.parseInt((jsonParams.isNull("ha"))?"0":String.valueOf(jsonParams.get("ha"))));
		((Parameters) params).setHb(Integer.parseInt((jsonParams.isNull("hb"))?"0":String.valueOf(jsonParams.get("hb"))));
		((Parameters) params).setHc(Integer.parseInt((jsonParams.isNull("hc"))?"0":String.valueOf(jsonParams.get("hc"))));
		((Parameters) params).setHdop(Double.parseDouble((jsonParams.isNull("hdop"))?"0":String.valueOf(jsonParams.get("hdop"))));
		((Parameters) params).setIn0(Integer.parseInt((jsonParams.isNull("in0"))?"0":String.valueOf(jsonParams.get("in0"))));
		((Parameters) params).setIn1(Integer.parseInt((jsonParams.isNull("in1"))?"0":String.valueOf(jsonParams.get("in1"))));
		((Parameters) params).setIn2(Integer.parseInt((jsonParams.isNull("in2"))?"0":String.valueOf(jsonParams.get("in2"))));
		((Parameters) params).setIp(Integer.parseInt((jsonParams.isNull("ip"))?"0":String.valueOf(jsonParams.get("ip"))));
		((Parameters) params).setJd(Integer.parseInt((jsonParams.isNull("jd"))?"0":String.valueOf(jsonParams.get("jd"))));
		((Parameters) params).setMotion(Integer.parseInt((jsonParams.isNull("motion"))?"0":String.valueOf(jsonParams.get("motion"))));
		((Parameters) params).setOd((jsonParams.isNull("od"))?"0":String.valueOf(jsonParams.get("od")));
		((Parameters) params).setOdometer(Double.parseDouble((jsonParams.isNull("odometer"))?"0":String.valueOf(jsonParams.get("odometer"))));
		((Parameters) params).setOp(Integer.parseInt((jsonParams.isNull("op"))?"0":String.valueOf(jsonParams.get("op"))));
		((Parameters) params).setPs(Integer.parseInt((jsonParams.isNull("ps"))?"0":String.valueOf(jsonParams.get("ps"))));
		((Parameters) params).setPwrExt(Double.parseDouble((jsonParams.isNull("pwr_ext"))?"0":String.valueOf(jsonParams.get("pwr_ext"))));
		((Parameters) params).setPwrInt(Double.parseDouble((jsonParams.isNull("pwr_int"))?"0":String.valueOf(jsonParams.get("pwr_int"))));
		((Parameters) params).setRd((jsonParams.isNull("rd"))?"0":String.valueOf(jsonParams.get("rd")));
		((Parameters) params).setReportId(Integer.parseInt((jsonParams.isNull("report_id"))?"0":String.valueOf(jsonParams.get("report_id"))));
		((Parameters) params).setRtcTm(Long.parseLong((jsonParams.isNull("rtc_tm"))?"0":String.valueOf(jsonParams.get("rtc_tm"))));
		((Parameters) params).setSndTm(Long.parseLong((jsonParams.isNull("snd_tm"))?"0":String.valueOf(jsonParams.get("snd_tm"))));
		((Parameters) params).setSs(Integer.parseInt((jsonParams.isNull("ss"))?"0":String.valueOf(jsonParams.get("ss"))));
		((Parameters) params).setTemp1(Double.parseDouble((jsonParams.isNull("temp1"))?"0":String.valueOf(jsonParams.get("temp1"))));
		((Parameters) params).setTemp2(Double.parseDouble((jsonParams.isNull("temp2"))?"0":String.valueOf(jsonParams.get("temp2"))));
		((Parameters) params).setTw(Integer.parseInt((jsonParams.isNull("tw"))?"0":String.valueOf(jsonParams.get("tw"))));
	}
	
	public void setHttpReqExecutor(IWialonHTTPRequestExecutor httpReqExecutor) {
		this.httpReqExecutor = httpReqExecutor;
	}

	public void setAppProperties(Properties appProperties) {
		this.appProperties = appProperties;
	}

}
