package mx.skyguardian.controltower.bean;


//@XmlRootElement(name="parameters", namespace="http://us.skyguardian.controltower")
public class Parameters extends AbstractWialonEntity {

	private Double adc1 = 0D;
	private Integer bl = 0;
	private Integer dl = 0;
	private Integer engine = 0;
	private Long gpsTm = 0L;
	private Integer gsm = 0;
	private Integer gsmStatus = 0;
	private Integer ha = 0;
	private Integer hb = 0;
	private Integer hc = 0;
	private Double hdop = 0D;
	private Integer in0 = 0;
	private Integer in1 = 0;
	private Integer in2 = 0;
	private Integer ip = 0;
	private Integer jd = 0;
	private Integer motion = 0;
	private String od = "0";
	private Double odometer = 0D;
	private Integer op = 0;
	private Integer ps = 0;
	private Double pwrExt = 0D;
	private Double pwrInt = 0D;
	private String rd = "0";
	private Integer reportId = 0;
	private Long rtcTm = 0L;
	private Long sndTm = 0L;
	private Integer ss = 0;
	private Double temp1 = 0D;
	private Double temp2 = 0D;
	private Integer tw = 0;
	
	public Parameters(){
		
	}
	
	public Parameters(Double adc1, Integer bl, Integer dl, Integer engine,
			Long gpsTm, Integer gsm, Integer gsmStatus, Integer ha, Integer hb,
			Integer hc, Double hdop, Integer in0, Integer in1, Integer in2,
			Integer ip, Integer jd, Integer motion, String od, Double odometer,
			Integer op, Integer ps, Double pwrExt, Double pwrInt, String rd,
			Integer reportId, Long rtcTm, Long sndTm, Integer ss, Double temp1,
			Double temp2, Integer tw) {
		super();
		this.adc1 = adc1;
		this.bl = bl;
		this.dl = dl;
		this.engine = engine;
		this.gpsTm = gpsTm;
		this.gsm = gsm;
		this.gsmStatus = gsmStatus;
		this.ha = ha;
		this.hb = hb;
		this.hc = hc;
		this.hdop = hdop;
		this.in0 = in0;
		this.in1 = in1;
		this.in2 = in2;
		this.ip = ip;
		this.jd = jd;
		this.motion = motion;
		this.od = od;
		this.odometer = odometer;
		this.op = op;
		this.ps = ps;
		this.pwrExt = pwrExt;
		this.pwrInt = pwrInt;
		this.rd = rd;
		this.reportId = reportId;
		this.rtcTm = rtcTm;
		this.sndTm = sndTm;
		this.ss = ss;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.tw = tw;
	}
	public Double getAdc1() {
		return adc1;
	}
	public void setAdc1(Double adc1) {
		this.adc1 = adc1;
	}
	public Integer getBl() {
		return bl;
	}
	public void setBl(Integer bl) {
		this.bl = bl;
	}
	public Integer getDl() {
		return dl;
	}
	public void setDl(Integer dl) {
		this.dl = dl;
	}
	public Integer getEngine() {
		return engine;
	}
	public void setEngine(Integer engine) {
		this.engine = engine;
	}
	public Long getGpsTm() {
		return gpsTm;
	}
	public void setGpsTm(Long gpsTm) {
		this.gpsTm = gpsTm;
	}
	public Integer getGsm() {
		return gsm;
	}
	public void setGsm(Integer gsm) {
		this.gsm = gsm;
	}
	public Integer getGsmStatus() {
		return gsmStatus;
	}
	public void setGsmStatus(Integer gsmStatus) {
		this.gsmStatus = gsmStatus;
	}
	public Integer getHa() {
		return ha;
	}
	public void setHa(Integer ha) {
		this.ha = ha;
	}
	public Integer getHb() {
		return hb;
	}
	public void setHb(Integer hb) {
		this.hb = hb;
	}
	public Integer getHc() {
		return hc;
	}
	public void setHc(Integer hc) {
		this.hc = hc;
	}
	public Double getHdop() {
		return hdop;
	}
	public void setHdop(Double hdop) {
		this.hdop = hdop;
	}
	public Integer getIn0() {
		return in0;
	}
	public void setIn0(Integer in0) {
		this.in0 = in0;
	}
	public Integer getIn1() {
		return in1;
	}
	public void setIn1(Integer in1) {
		this.in1 = in1;
	}
	public Integer getIn2() {
		return in2;
	}
	public void setIn2(Integer in2) {
		this.in2 = in2;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public Integer getJd() {
		return jd;
	}
	public void setJd(Integer jd) {
		this.jd = jd;
	}
	public Integer getMotion() {
		return motion;
	}
	public void setMotion(Integer motion) {
		this.motion = motion;
	}
	public String getOd() {
		return od;
	}
	public void setOd(String od) {
		this.od = od;
	}
	public Double getOdometer() {
		return odometer;
	}
	public void setOdometer(Double odometer) {
		this.odometer = odometer;
	}
	public Integer getOp() {
		return op;
	}
	public void setOp(Integer op) {
		this.op = op;
	}
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}
	public Double getPwrExt() {
		return pwrExt;
	}
	public void setPwrExt(Double pwrExt) {
		this.pwrExt = pwrExt;
	}
	public Double getPwrInt() {
		return pwrInt;
	}
	public void setPwrInt(Double pwrInt) {
		this.pwrInt = pwrInt;
	}
	public String getRd() {
		return rd;
	}
	public void setRd(String rd) {
		this.rd = rd;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Long getRtcTm() {
		return rtcTm;
	}
	public void setRtcTm(Long rtcTm) {
		this.rtcTm = rtcTm;
	}
	public Long getSndTm() {
		return sndTm;
	}
	public void setSndTm(Long sndTm) {
		this.sndTm = sndTm;
	}
	public Integer getSs() {
		return ss;
	}
	public void setSs(Integer ss) {
		this.ss = ss;
	}
	public Double getTemp1() {
		return temp1;
	}
	public void setTemp1(Double temp1) {
		this.temp1 = temp1;
	}
	public Double getTemp2() {
		return temp2;
	}
	public void setTemp2(Double temp2) {
		this.temp2 = temp2;
	}
	public Integer getTw() {
		return tw;
	}
	public void setTw(Integer tw) {
		this.tw = tw;
	}
}
