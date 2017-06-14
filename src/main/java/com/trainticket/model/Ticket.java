package com.trainticket.model;

//车票信息
public class Ticket {
	private String sw="";//商务票数
	private	String yd="";//一等票数
	private	String ed="";//二等票数
	private	String td="";//特等座票数
	private	String gw="";//高级软卧票数
	private	String rw="";//软卧票数
	private	String yw="";//硬卧票数
	private	String rz="";//软座票数
	private	String yz="";//硬座票数
	private	String wz="";//无座票数
	private	String qt="";//其它票数
	
	private String swpj="";//商务座票价
	private	String ydpj="";//一等票价
	private	String edpj="";//二等票价
	private	String tdpj="";//特等票价
	private	String gwpj="";//高级软卧票价
	private	String rwpj="";//软卧票价
	private	String ywpj="";//硬卧票价
	private	String rzpj="";//软座票价
	private	String yzpj="";//硬座票价
	private	String wzpj="";//无座票价
	private	String qtpj="";//其它票价
	
	private String tcode="";//列车查询码
	private	String code="";//列车代号
	private	String start="";//出发站
	private	String end="";//到达站
	private	String stime="";//出发时间
	private	String etime="";//到达时间
	private	String lishi="";//历时
	private	String you="";//是否有票
	private	String riqi="";//日期
	private	String sno="";//出发站编号
	private	String eno="";//到达站编号
	private String seat="";//座位类型
	
	//根据请求返回信息
	public String getInf(String content){
		if(content.equals("sw"))
			return sw;
		else if(content.equals("swpj"))
			return swpj;
		else if(content.equals("yd"))
			return yd;
		else if(content.equals("ydpj"))
			return ydpj;
		else if(content.equals("ed"))
			return ed;
		else if(content.equals("edpj"))
			return edpj;
		else if(content.equals("td"))
			return td;
		else if(content.equals("tdpj"))
			return tdpj;
		else if(content.equals("gw"))
			return gw;
		else if(content.equals("gwpj"))
			return gwpj;
		else if(content.equals("rw"))
			return rw;
		else if(content.equals("rwpj"))
			return rwpj; 
		else if(content.equals("yw"))
			return yw;
		else if(content.equals("ywpj"))
			return ywpj;
		else if(content.equals("rz"))
			return rz;
		else if(content.equals("rzpj"))
			return rzpj;
		else if(content.equals("yz"))
			return yz; 
		else if(content.equals("yzpj"))
			return yzpj;
		else if(content.equals("wz"))
			return wz;
		else if(content.equals("wzpj"))
			return wzpj;
		else if(content.equals("qt"))
			return qt;
		else if(content.equals("qtpj"))
			return qtpj;
		else
			return ""; 
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getSw() {
		return sw;
	}
	public void setSw(String sw) {
		this.sw = sw;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getTd() {
		return td;
	}
	public void setTd(String td) {
		this.td = td;
	}
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	public String getRw() {
		return rw;
	}
	public void setRw(String rw) {
		this.rw = rw;
	}
	public String getYw() {
		return yw;
	}
	public void setYw(String yw) {
		this.yw = yw;
	}
	public String getRz() {
		return rz;
	}
	public void setRz(String rz) {
		this.rz = rz;
	}
	public String getYz() {
		return yz;
	}
	public void setYz(String yz) {
		this.yz = yz;
	}
	public String getWz() {
		return wz;
	}
	public void setWz(String wz) {
		this.wz = wz;
	}
	public String getQt() {
		return qt;
	}
	public void setQt(String qt) {
		this.qt = qt;
	}
	public String getSwpj() {
		return swpj;
	}
	public void setSwpj(String swpj) {
		this.swpj = swpj;
	}
	public String getYdpj() {
		return ydpj;
	}
	public void setYdpj(String ydpj) {
		this.ydpj = ydpj;
	}
	public String getEdpj() {
		return edpj;
	}
	public void setEdpj(String edpj) {
		this.edpj = edpj;
	}
	public String getTdpj() {
		return tdpj;
	}
	public void setTdpj(String tdpj) {
		this.tdpj = tdpj;
	}
	public String getGwpj() {
		return gwpj;
	}
	public void setGwpj(String gwpj) {
		this.gwpj = gwpj;
	}
	public String getRwpj() {
		return rwpj;
	}
	public void setRwpj(String rwpj) {
		this.rwpj = rwpj;
	}
	public String getYwpj() {
		return ywpj;
	}
	public void setYwpj(String ywpj) {
		this.ywpj = ywpj;
	}
	public String getRzpj() {
		return rzpj;
	}
	public void setRzpj(String rzpj) {
		this.rzpj = rzpj;
	}
	public String getYzpj() {
		return yzpj;
	}
	public void setYzpj(String yzpj) {
		this.yzpj = yzpj;
	}
	public String getWzpj() {
		return wzpj;
	}
	public void setWzpj(String wzpj) {
		this.wzpj = wzpj;
	}
	public String getQtpj() {
		return qtpj;
	}
	public void setQtpj(String qtpj) {
		this.qtpj = qtpj;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getLishi() {
		return lishi;
	}
	public void setLishi(String lishi) {
		this.lishi = lishi;
	}
	public String getYou() {
		return you;
	}
	public void setYou(String you) {
		this.you = you;
	}
	public String getRiqi() {
		return riqi;
	}
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}

}
