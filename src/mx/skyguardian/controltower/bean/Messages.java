package mx.skyguardian.controltower.bean;


//@XmlRootElement(name="message", namespace="http://us.skyguardian.controltower")
public class Messages extends AbstractWialonEntity {
	private Long timeUTC;
	private Parameters parameters;
	private Position position;
	
	public Messages(){
		
	}
	
	public boolean isEmpty(){
		return (this.timeUTC==null && parameters == null && position == null)?true:false;
	}
	
	public Messages(Long timeUTC, Parameters parameters, Position position) {
		super();
		this.timeUTC = timeUTC;
		this.parameters = parameters;
		this.position = position;
	}

	public Long getTimeUTC() {
		return timeUTC;
	}
	public void setTimeUTC(Long timeUTC) {
		this.timeUTC = timeUTC;
	}
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
		
}
