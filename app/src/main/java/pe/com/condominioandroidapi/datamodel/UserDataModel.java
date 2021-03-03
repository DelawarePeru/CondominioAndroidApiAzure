package pe.com.condominioandroidapi.datamodel;


import android.os.Build;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import okhttp3.ResponseBody;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.service.UsuarioService;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import pe.com.condominioandroidapi.util.basecomponent.BaseDataModel;
import pe.com.condominioandroidapi.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataModel extends BaseDataModel {
    private MutableLiveData<Integer> progressLiveData;
    private MutableLiveData<Integer> totalSizeLiveData;
    //  private MutableLiveData<DocumentCertero> fileLiveData;
    private MutableLiveData<String> userResult;
    private MutableLiveData<String> userListResult;
    private static SecretKeySpec secretKey;
    private static byte[] key;


    public UserDataModel() {
        progressLiveData = new MutableLiveData<>();
        totalSizeLiveData = new MutableLiveData<>();
        //fileLiveData = new MutableLiveData<>();
        userResult = new MutableLiveData<>();
        userListResult = new MutableLiveData<>();
    }

    public MutableLiveData<String> getUserResult() {
        return userResult;
    }

    public MutableLiveData<String> getUserListResult() {
        return userListResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loginUser(String username, String password, final Boolean isLoggedIn) {
        try {
            final String base64;
           /* if (isLoggedIn) {
                base64 = Constant.get(Constant.KEY_ACCESS_TOKEN);
            } else
            {*/
           /*SecureRandom random = new SecureRandom();
                byte[] salt = new byte[16];
                random.nextBytes(salt);
             jwtEncode(username,password);
               base64 = "Basic " + Base64.encodeToString(
                       (username + ":" + password).getBytes(), Base64.NO_WRAP);
            */
            String passEncrypt = encrypt(password,Constant.base64EncodedSecret);
            // String passDecrypt = decrypt(passEncrypt,Constant.base64EncodedSecret);
            Log.d("passEncrypt",passEncrypt);
            // Log.d("passEncrypt",passDecrypt);
            base64 = "Basic " + Base64.encodeToString(
                    (username + ":" + password).getBytes(), Base64.NO_WRAP);
            UsuarioService loginService = ServiceGenerator
                    .createServiceLogin(UsuarioService.class, Constant.KEY_ACCESS_TOKEN);

            //  Call<List<UserResponse>> call = loginService.basicLogin();
            Call<String> call = loginService.basicLogin(builJson(username, passEncrypt));

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call,
                                       @NonNull Response<String> response) {
                    if(response.isSuccessful()) {

                        if (response.body() != null && !response.body().isEmpty()) {
                            if (response.body().equals("true")) {
                                Constant.setupAuthentication(base64);
                                Constant.set(Constant.EMAIL,username);

                                userResult.setValue(response.body());
                            } else {
                                errorMessageLiveData.setValue("Datos ingresado incorrectos.");

                                Constant.setupAuthentication("");
                            }
                        } else {
                            errorMessageLiveData.setValue("No se pudo obtener los datos correctamente");
                            Constant.setupAuthentication("");
                        }
                    }
                    else{
                        Constant.setupAuthentication("");
                    }
                   /*  if (response.isSuccessful()) {
                        if (response.body() != null && !response.body().isEmpty()) {
                           if (response.body().get(0).getError().equals("")) {
                                //userResult.setValue(response.body().get(0));
                                Constant.setupAuthentication(base64);
                            } else {
                                errorMessageLiveData.setValue(response.body().get(0).getError());
                                Constant.setupAuthentication("");
                            }
                            Log.i("","");
                        } else {
                            errorMessageLiveData.setValue("No se pudo obtener los datos correctamente");
                            Constant.setupAuthentication("");
                        }
                    } else {
                        errorCodeLiveData.setValue(response.code());
                        Constant.setupAuthentication("");
                    }*/
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                }
            });
        }
        catch (Exception ex) {
            errorCodeLiveData.setValue(-1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void registrarUsuario(String username, String password, String  passRepetir ,
                                 String nombre, String apellido,String telefono , Boolean isLoggedIn) {
        try {
            UsuarioService loginService = ServiceGenerator
                    .createServiceLogin(UsuarioService.class, Constant.KEY_ACCESS_TOKEN);

            Call<ResponseBody> call = loginService.registro(builJsonRegistro(username, password, passRepetir));

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call,
                                       @NonNull Response<ResponseBody> response) {

                    if(response.isSuccessful()) {

                        if (response.body() != null) {
                                try {
                                    Constant.set(Constant.ID_USER,response.body().string());
                                    userListResult.setValue(Constant.get(Constant.ID_USER));

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                        } else {
                            errorMessageLiveData.setValue("El usuario ya se encuentra registrado");
                            Constant.setupAuthentication("");
                        }
                    }
                    else{

                    }

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                }
            });
        }
        catch (Exception ex) {
            errorCodeLiveData.setValue(-1);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void registrarPersona(String id, String nombre, String  apellido , String telefono )
    {

        try {
            UsuarioService loginService = ServiceGenerator
                    .createService(UsuarioService.class, Constant.KEY_ACCESS_TOKEN);

            Call<PostResponse> call = loginService.AgregarPersona(builJsonPersona(id,nombre,apellido , telefono));

            call.enqueue(new Callback<PostResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(@NonNull Call<PostResponse> call,
                                       @NonNull Response<PostResponse> response) {
                    if(response.isSuccessful()) {

                        userResult.setValue(response.body().getMensaje());


                        } else {
                            errorMessageLiveData.setValue("No se pudo obtener los datos correctamente");
                            Constant.setupAuthentication("");

                     }

                }

                @Override
                public void onFailure(@NonNull Call<PostResponse> call, @NonNull Throwable t) {
                }
            });
        }
        catch (Exception ex) {
            errorCodeLiveData.setValue(-1);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void recuperarContrasena(String username) {
        try {
            UsuarioService loginService = ServiceGenerator
                    .createServiceLogin(UsuarioService.class, Constant.KEY_ACCESS_TOKEN);

            Call<ResponseBody> call = loginService.recuperar(builJson(username));

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call,
                                       @NonNull Response<ResponseBody> response) {

                    if(response.isSuccessful()) {

                        if (response.body() != null) {
                            try {
                                Constant.set(Constant.ID_USER,response.body().string());
                                userResult.setValue(response.body().string());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            errorMessageLiveData.setValue("El usuario ya se encuentra registrado");
                            Constant.setupAuthentication("");

                        }
                    }
                    else{

                    }

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                }
            });
        }
        catch (Exception ex) {
            errorCodeLiveData.setValue(-1);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void jwtEncode(String username , String password) {
        String jwt = Jwts.builder()
                .claim("email",username)
                .claim("password", password)
                .signWith(SignatureAlgorithm.HS256, Constant.decodedSecret)
                .compact();
    }


    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) throws Exception
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] keyBytes = new byte[16];
            byte[] b = secret.getBytes("UTF-8");
            int len = b.length;
            if (len > keyBytes.length)
                len = keyBytes.length;
            System.arraycopy(b, 0, keyBytes, 0, len);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] results = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            //Base64 encoder = new Base64();
            return new String(Base64.encode(results,0)); // it returns the result as a String
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decode(strToDecrypt,0)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private JsonObject builJson(String username) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("Email", username);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    private JsonObject builJson(String username, String encrypt) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("Password", encrypt);
            jo.addProperty("Email", username);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
    private JsonObject builJsonRegistro(String username, String password, String
                                        passwordRepetir) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("Confirm password", passwordRepetir);
            jo.addProperty("Password", password);
            jo.addProperty("Email", username);


        } catch (Exception ex) {

        }
        return jo;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private JsonObject builJsonPersona(String id, String nombre, String
            apellido , String telefono)
    {
        String words[] = apellido.split("\\s+");
        JsonObject jo = new JsonObject();
        try {
            if (id.length() > 1) {
                jo.addProperty("idUser", Constant.get(Constant.ID_USER));

            }else
            {
                jo.addProperty("idUser", id);

            }
            jo.addProperty("nombre", nombre);
            jo.addProperty("apellidoPaterno",words[0]);
            jo.addProperty("apellidoMaterno",words[1]);
            jo.addProperty("telefono", telefono);

        Log.d("loginjo",jo.toString());
        } catch (Exception ex) {

        }
        return jo;
    }

    public String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
  /*  public void requestSearchUser(String palabraClave) {
        Call<List<UserResponse>> call = null;
        UsuarioService service = ServiceGenerator.createService(
                UsuarioService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));

        call = service.searchUser(palabraClave);
        Log.i("text palabra clave:" , palabraClave);
        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserResponse>> call,
                                   @NonNull Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    Log.i("url" ,call.request().url().toString());
                    if (response.body() != null && response.body().get(0).error.length()==0) {
                        if (response.body().get(0).getError().equals(""))
                            userListResult.setValue(response.body());
                        else {
                            errorMessageLiveData.setValue(response.body().get(0).getError());
                        }
                    } else {
                        errorMessageLiveData.setValue("No se pudo obtener los datos correctamente");
                        userListResult.setValue(response.body());
                    }

                } else {
                    errorCodeLiveData.setValue(response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserResponse>> call, @NonNull Throwable t) {
                errorCodeLiveData.setValue(-1);
            }
        });
    }

  /*  public void downloadFile(DocumentCertero documento) {
        try {
            AccessToken accessToken = new AccessToken(Constant.get(Constant.KEY_ACCESS_TOKEN));
            URL url = new URL(documento.getDocument().getUrl());
            String downloadPath = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS) + "/" + URLUtil.guessFileName(url.getPath(), null, null);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty(Constant.KEY_ACCESS_TOKEN, accessToken.getApiAccessToken());
            urlConnection.setReadTimeout(60 * 1000);
            urlConnection.setConnectTimeout(60 * 1000);
            urlConnection.setDoOutput(true);

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                urlConnection.connect();
                final File downloadFile = new File(downloadPath);

                FileOutputStream outputStream = new FileOutputStream(downloadFile, false);
                InputStream inputStream = urlConnection.getInputStream();
                totalSizeLiveData.setValue(urlConnection.getContentLength());
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;

                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    progressLiveData.setValue(downloadedSize);
                }
                documento.setFile(downloadFile);
                fileLiveData.setValue(documento);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            errorCodeLiveData.setValue(-1);
        }
    }*/

    public MutableLiveData<Integer> getProgressLiveData() {
        return progressLiveData;
    }


    public MutableLiveData<Integer> getTotalSizeLiveData() {
        return totalSizeLiveData;
    }

   /* public MutableLiveData<DocumentCertero> getFileLiveData() {
        return fileLiveData;
    }*/
}

