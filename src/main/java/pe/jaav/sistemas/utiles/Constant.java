package pe.jaav.sistemas.utiles;

public class Constant {

	
	/** GENERALES **/
	public static final String SI_db = "S";
	public static final String NO_db = "N";
	public static final int INT_SI = 1;
	public static final int INT_NO = 2;
	
	public static final String DELETE_db = "D";
	public static final String UPDATE_db = "U";
	public static final String INSERT_db = "I";
	
	public static final String ACTIVO_db = "A";
	public static final String INACTIVO_db = "I";
	
	public static final String RESULT_OK = "OK";
	public static final String RESULT_ERROR = "ERROR";
	
	public static final String MONEDA_LOCAL_DEF = "PEN"; //NUEVO SOL
	public static final String MONEDA_EXT_DEF = "USD";  //DOLAR
	
	/** TIPOS DE ORDEN PERSISTENCIA */
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";

	
	public static final String PATTERN_NUM_2DEC="##,###,##0.00;-##,###,##0.00";
	public static final String PATTERN_NUM_3DEC="##,###,##0.000;-##,###,##0.000";
	public static final String PATTERN_NUM_4DEC="##,###,##0.0000;-##,###,##0.0000";
	public static final String PATTERN_NUM_6DEC="##,###,##0.000000;-##,###,##0.000000";
	
	/**APP MODULO*/
	public static final String APP_CODE = "YIMPU_GEN";
	
	/**CONSTANTES JWT*/
	public static final String JWT_TOKEN_HEADER_PARAM = "Authorization";
	public static final String JWT_TOKEN_PREFIX = "Bearer";
	
	/**SEGURIDAD*/
	public static final String SEGURIDAD_TIPOUSER_ADMIN = "ADMIN";
	public static final String SEGURIDAD_TIPOUSER_DELIVERY = "DELIV";
	public static final String SEGURIDAD_TIPOUSER_CLIENTE = "CLIENT";
	public static final String SEGURIDAD_TIPOUSER_PROPIET_LAV = "LAVOWNER";
		
	/** SEGURIDAD - ROLES  **/
	public static final String SEGURIDAD_ROLE_CODE= "ROLE_";
	public static final String SEGURIDAD_ROLE_ADMIN = SEGURIDAD_ROLE_CODE+SEGURIDAD_TIPOUSER_ADMIN;
	public static final String SEGURIDAD_ROLE_DELIVERY = SEGURIDAD_ROLE_CODE+SEGURIDAD_TIPOUSER_DELIVERY;
	public static final String SEGURIDAD_ROLE_CLIENTE = SEGURIDAD_ROLE_CODE+SEGURIDAD_TIPOUSER_CLIENTE;
	public static final String SEGURIDAD_ROLE_PROPIET_LAV = SEGURIDAD_ROLE_CODE+SEGURIDAD_TIPOUSER_PROPIET_LAV;
	
	public static final String SEGURIDAD_ORGAN_MODULO_DEFAULT = "ORGAN_GEN";

	/** PERSONA DEFAULT  **/
	public static final String PERSONA_TIPO_JURIDICA = "J";
	public static final String PERSONA_TIPO_NATURAL = "N";

	
	/**TIPOS DE ACCION MSG*/
	public static final String MSG_ACCION_TIPO_CODE_MAIL = "MAIL";
	public static final String MSG_ACCION_TIPO_CODE_PUSH_NOTIF = "PUSH";
	
	
	/** CODIGOS TABLAS VARIOS */
	public static final String TABLA_CODE_PARAMCORREO = "PARAMEMAIL";
		
	/** CODIGOS PARAMETROS*/
	public static final String PARAM_CODE_EMAIL_TEMPLATE_REGISTROUSUARIO = "TPL_EMAIL_REG_USER";
	public static final String PARAM_CODE_EMAIL_TEMPLATE_ORDENLAVADO = "TPL_EMAIL_GEN_OA";
	public static final String PARAM_CODE_EMAIL_TEMPLATE_PEDIDOLAVADO = "TPL_EMAIL_GEN_PED";
	
	/** ESTADOS MENSAJES ACCION**/
	public static final String MSG_ESTADO_PENDIENTE = "P";
	public static final String MSG_ESTADO_EN_ESPERA = "E";
	public static final String MSG_ESTADO_FINALIZADO = "F";
	public static final String MSG_ESTADO_DESCARTADO = "X";
	
	/** VARIABLES DE ENTORNO  CONFIG EMAIL**/
	public static final String ENV_VARIABLE_PAR_CORREO_ENVIA = "PAR_VAL_CORREO_ENVIA";
	public static final String ENV_VARIABLE_PAR_CLAVE_CORREO_ENVIA = "PAR_VAL_CLAVE_CORREO_ENVIA";
	public static final String ENV_VARIABLE_PAR_CORREO_HOST = "PAR_VAL_CORREO_HOST";
	public static final String ENV_VARIABLE_PAR_CORREO_PORT = "PAR_VAL_CORREO_PORT";
	public static final String ENV_VARIABLE_PAR_CORREO_SPORT = "PAR_VAL_CORREO_SPORT";
	public static final String ENV_VARIABLE_PAR_CORREO_TTL = "PAR_VAL_CORREO_TTL";
	public static final String ENV_VARIABLE_PAR_CORREO_ACCOUNT = "PAR_VAL_CORREO_ACCOUNT";
		
}
