package pe.jaav.sistemas.messageFlowApi.model.general;

import pe.jaav.sistemas.messageFlowApi.model.EntidadJson;

public class GenMensajeAccionDestinoJson extends EntidadJson<GenMensajeAccionJson> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer msgaMensajeAccionId;
	private Integer msgadSecuencia;
	
	private String msgadDestinoValor;
	private String msgadFlagCc;
	private String msgadFlagCco;
	private String msgadFlagTo;
	
	public Integer getMsgaMensajeAccionId() {
		return msgaMensajeAccionId;
	}

	public void setMsgaMensajeAccionId(Integer msgaMensajeAccionId) {
		this.msgaMensajeAccionId = msgaMensajeAccionId;
	}

	public Integer getMsgadSecuencia() {
		return msgadSecuencia;
	}

	public void setMsgadSecuencia(Integer msgadSecuencia) {
		this.msgadSecuencia = msgadSecuencia;
	}

	public String getMsgadDestinoValor() {
		return msgadDestinoValor;
	}

	public void setMsgadDestinoValor(String msgadDestinoValor) {
		this.msgadDestinoValor = msgadDestinoValor;
	}

	public String getMsgadFlagCc() {
		return msgadFlagCc;
	}

	public void setMsgadFlagCc(String msgadFlagCc) {
		this.msgadFlagCc = msgadFlagCc;
	}

	public String getMsgadFlagCco() {
		return msgadFlagCco;
	}

	public void setMsgadFlagCco(String msgadFlagCco) {
		this.msgadFlagCco = msgadFlagCco;
	}

	public String getMsgadFlagTo() {
		return msgadFlagTo;
	}

	public void setMsgadFlagTo(String msgadFlagTo) {
		this.msgadFlagTo = msgadFlagTo;
	}	
	
	
}
