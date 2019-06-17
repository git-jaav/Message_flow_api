package pe.jaav.sistemas.messageFlowApi.model.general;

import java.util.List;

public class GenMensajeAccionContextJson {

	private GenMensajeAccionJson objMensajeAccion;
	private List<GenMensajeAccionDestinoJson> listMensajeAccionDestino;
	
	//
	public GenMensajeAccionJson getObjMensajeAccion() {
		return objMensajeAccion;
	}
	public void setObjMensajeAccion(GenMensajeAccionJson objMensajeAccion) {
		this.objMensajeAccion = objMensajeAccion;
	}
	public List<GenMensajeAccionDestinoJson> getListMensajeAccionDestino() {
		return listMensajeAccionDestino;
	}
	public void setListMensajeAccionDestino(List<GenMensajeAccionDestinoJson> listMensajeAccionDestino) {
		this.listMensajeAccionDestino = listMensajeAccionDestino;
	}
		
}
