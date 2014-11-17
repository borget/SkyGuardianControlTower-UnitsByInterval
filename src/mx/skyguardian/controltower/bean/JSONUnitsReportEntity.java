package mx.skyguardian.controltower.bean;

import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name="units", namespace="http://us.skyguardian.controltower")
public class JSONUnitsReportEntity extends AbstractWialonEntity {
	
	private List <Units> units;
	private Long serverTime;
	
	public JSONUnitsReportEntity() {
		this.units = new ArrayList<Units>();
	}
	
	//@XmlElement
	public List<Units> getUnits() {
		return units;
	}

	public void setUnits(List<Units> units) {
		this.units = units;
	}
}
