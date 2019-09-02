package pe.jaav.sistemas.messageFlowApi.model.general;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import pe.jaav.sistemas.messageFlowApi.model.EntidadJson;

@Getter
@Setter
public class GenMensajeAccionJson  extends EntidadJson<GenMensajeAccionJson> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer msgaMensajeAccionId;
	private String msgAsunto;
	private String msgCuerpomensajeText;
	private String msgCuerpomensajeHtml;
	private String msgEstadoAccion;
	private Date msgFechaRegistro;
	private String msgFlagCloud;
	private String msgProcesoCode;
	private String msgTipoCode;
	private String msgTopicoCode;
	private String msgUsuariomodif;
	
/*
	public Integer getMsgaMensajeAccionId() {
		return msgaMensajeAccionId;
	}
	public void setMsgaMensajeAccionId(Integer msgaMensajeAccionId) {
		this.msgaMensajeAccionId = msgaMensajeAccionId;
	}
	public String getMsgAsunto() {
		return msgAsunto;
	}
	public void setMsgAsunto(String msgAsunto) {
		this.msgAsunto = msgAsunto;
	}

	public String getMsgCuerpomensajeHtml() {
		return msgCuerpomensajeHtml;
	}
	public void setMsgCuerpomensajeHtml(String msgCuerpomensajeHtml) {
		this.msgCuerpomensajeHtml = msgCuerpomensajeHtml;
	}
	public String getMsgEstadoAccion() {
		return msgEstadoAccion;
	}
	public void setMsgEstadoAccion(String msgEstadoAccion) {
		this.msgEstadoAccion = msgEstadoAccion;
	}
	public Date getMsgFechaRegistro() {
		return msgFechaRegistro;
	}
	public void setMsgFechaRegistro(Date msgFechaRegistro) {
		this.msgFechaRegistro = msgFechaRegistro;
	}
	public String getMsgFlagCloud() {
		return msgFlagCloud;
	}
	public void setMsgFlagCloud(String msgFlagCloud) {
		this.msgFlagCloud = msgFlagCloud;
	}
	public String getMsgProcesoCode() {
		return msgProcesoCode;
	}
	public void setMsgProcesoCode(String msgProcesoCode) {
		this.msgProcesoCode = msgProcesoCode;
	}
	public String getMsgTipoCode() {
		return msgTipoCode;
	}
	public void setMsgTipoCode(String msgTipoCode) {
		this.msgTipoCode = msgTipoCode;
	}
	public String getMsgTopicoCode() {
		return msgTopicoCode;
	}
	public void setMsgTopicoCode(String msgTopicoCode) {
		this.msgTopicoCode = msgTopicoCode;
	}
	public String getMsgUsuariomodif() {
		return msgUsuariomodif;
	}
	public void setMsgUsuariomodif(String msgUsuariomodif) {
		this.msgUsuariomodif = msgUsuariomodif;
	}
	public String getMsgCuerpomensajeText() {
		return msgCuerpomensajeText;
	}
	public void setMsgCuerpomensajeText(String msgCuerpomensajeText) {
		this.msgCuerpomensajeText = msgCuerpomensajeText;
	}
*/
	
}
