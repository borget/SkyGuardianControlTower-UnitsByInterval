package mx.skyguardian.controltower.bean;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

//@XmlRootElement(name="unit", namespace="http://us.skyguardian.controltower")
public class Units extends AbstractWialonEntity {
	private String unitName = StringUtils.EMPTY;
	private Long unitId = 0L;
	private List<Messages> messages = new ArrayList<Messages>();
	
	public Units() {
		
	}
	
	public Units(String unitName, Long unitId, List<Messages> messages) {
		super();
		this.unitName = unitName;
		this.unitId = unitId;
		this.messages = messages;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}
}