package mx.skyguardian.controltower.json;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;
import mx.skyguardian.controltower.http.remoting.WialonSession;

import org.json.JSONObject;

public abstract class AbsctractJSONDeserializer {
	abstract void setParameters(JSONObject jsonParams, AbstractWialonEntity parameter);
	abstract void setPosition(JSONObject jsonPos, 
			AbstractWialonEntity position, WialonSession wialonSession, String unitId);
}
