
package pe.com.condominioandroidapi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Base64;
import java.util.List;

import pe.com.condominioandroidapi.entities.GaleriaResponse;


@RequiresApi(api = Build.VERSION_CODES.O)
public class Constant {

    public static final String KEY_USER_DATA = "current_user";
    public static final String KEY_PROYECTO_DATA = "KEY_PROYECTO_DATA";
    public static final String ID_INMUEBLE = "ID_INMUEBLE";
    public static final String ID_PROYECTO = "ID_PROYECTO";
    public static final String ID_CATALOGO = "ID_CATALOGO";
    public static final String ID_LOGOPROYECTO = "ID_LOGOPROYECTO";
    public static String ID_LOGOEMPRESA = "ID_LOGOEMPRESA";

    public static final String ID_PERFILEMPRESA = "ID_PERFILEMPRESA";
    public static final String  id_Inmueble  = "id_Inmueble";
    public static final String  CODIGO_INMUEBLE  = "CODIGO_INMUEBLE";
    public static final String CODIGO_COLOR = "CODIGO_COLOR";
    public static final String  ID_LUGAR  = "ID_LUGAR";
    public static  List<GaleriaResponse> GALERIA = null;
    public static  String departamento = "";
    public static  String provincia = "";
    public static String FOTO_RED_SOCIAL = "";
    public static String NOMBRE_RED_SOCIAL = "";

    public static final String KEY_MESSAGE = "KEY_MESSAGE";
    public static final String ID_OPCION = "ID_OPCION";//c0nd0m1n10saPPdE
    public static final String  URI  = "URI";
    public static final String  PDF_TITLE  = "PDF_TITLE";

    public static  String base64EncodedSecret  = "c0nd0m1n10saPPdE";
    public static byte[] decodedSecret = Base64.getDecoder().decode(base64EncodedSecret);


    private static SharedPreferences mPreferences;

    // static final String API_BASE_URL = "http://192.168.250.13/SiteQA/Condominio/ws/api/";
    //privada
    //static final String API_BASE_URL ="https://condominiosservices-cert.azurewebsites.net/";\Condominios-QA\Services-Cert

  //  static final String API_BASE_URL ="http://143.208.132.83:81/Condominios-Dev/Services/api/";
    static final String API_BASE_URL ="http://143.208.132.83:81/Condominios-QA/Services/api/";

   // static final String API_BASE_URL_LOGIN ="http://143.208.132.83:81/Condominios-Dev/Web/";
    static final String API_BASE_URL_LOGIN ="http://143.208.132.83:81/Condominios-QA/Web/";




    private static final String PREF_FILE_NAME = "pe.com.condominioandroidapi.preferences";
    public static final String KEY_ACCESS_TOKEN = "Authorization";
    public static final String EMAIL = "EMAIL";
    public static final String ID_PERSONA = "ID_PERSONA";
    public static final String NOMBRE = "NOMBRE";
    public static final String TELEFONO = "TELEFONO";
    public static final String ID_USER = "ID_USER";
    public static final String COORDENADA_X = "COORDENADA_X";
    public static final String COORDENADA_Y = "COORDENADA_Y";




    public static void initSharedPref(Context context) {
        mPreferences = context.getSharedPreferences(Constant.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPreferences() {
        return mPreferences;
    }

    private static SharedPreferences.Editor editSharedPrefs() {
        return getSharedPreferences().edit();
    }

    @Nullable
    public static String get(String key) {
        return mPreferences.getString(key, "");
    }

    public static void set(String key, String value) {
        editSharedPrefs().putString(key, value).apply();
    }

    public static Boolean contains(String key) {
        return mPreferences.contains(key);
    }

    public static void setupAuthentication(String accessKey) {
        Constant.set(Constant.KEY_ACCESS_TOKEN, accessKey);
    }
}
