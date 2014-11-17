package mx.skyguardian.controltower.http.remoting;

import java.io.IOException;

import mx.skyguardian.controltower.bean.AbstractWialonEntity;

public interface IControlTowerManager {
	AbstractWialonEntity getMessagesForAllUnitsByInterval(String userName, String password, String timeFrom) throws IOException;
}
