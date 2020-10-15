package pe.com.condominioandroidapi.activity.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.*;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.principal.PrincipalFragment;
import pe.com.condominioandroidapi.activity.principal.PrincipalViewModel;
import pe.com.condominioandroidapi.activity.principal.adapter.PrincipalAdapter;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.entities.UsuarioResponse;
import pe.com.condominioandroidapi.entities.switchResponse;
import pe.com.condominioandroidapi.util.CircleTransform;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class SettingFragment extends BaseFragment implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etTelefono)
    EditText etTelefono;
    @BindView(R.id.etNuevaContrasena)
    EditText etNuevaContrasena;
    @BindView(R.id.etConfirmarContrasena)
    EditText etConfirmarContrasena;
    @BindView(R.id.btnGuardar)
    Button btnGuardar;
    @BindView(R.id.pgbSetting)
    ProgressBar pgbSetting;
    @BindView(R.id.swPush)
    Switch swPush;
    @BindView(R.id.swSMS)
    Switch swSMS;
    @BindView(R.id.swCorreo)
    Switch swCorreo;
    @BindView(R.id.login_button)
    LoginButton login_button;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvRedes)
    TextView tvRedes;
    @BindView(R.id.ivFacebook)
    ImageView ivFacebook;
    @BindView(R.id.ivGmail)
    ImageView ivGmail;
    @BindView(R.id.googlesing)
    SignInButton googlesing;
    GoogleApiClient googleApiClient;
    private static final int SING_IN = 1;
    public boolean esCorrecto = true;
    private CallbackManager callbackManager ;
    ImageView dialog_imageview;

    private SettingViewModel viewModel;
    //private   loginResponse = new UserResponse();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_setting, container, false);
        ButterKnife.bind(this, view);
        FacebookSdk.sdkInitialize(this.getContext());

        callbackManager = CallbackManager.Factory.create();
        login_button.setReadPermissions("email");
        login_button.setFragment(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient =new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity() ,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        //checkLoginStatus();
        viewModel= ViewModelProviders.of(this).get(SettingViewModel.class);
        initializeObjects();
        initializeObservers();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loadUserProfile(loginResult);
            }

            @Override
            public void onCancel() {
                Log.d("onSuccess","onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onSuccess",error.toString());

            }
        });
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(SettingFragment.this, Arrays.asList("public_profile", "user_friends"));
            }
        });
        ivGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SING_IN);            }
        });
        googlesing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SING_IN);
            }
        });
        etTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length()==9)
                {
                    etTelefono.setBackgroundResource(R.drawable.edittext_border);
                    esCorrecto = true;


                }else {
                    etTelefono.setBackgroundResource(R.drawable.edittext_border_error);
                    esCorrecto = false;
                }

            }
        });

        etNuevaContrasena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length()==0 || s.length() < 8)
                {
                    etNuevaContrasena.setBackgroundResource(R.drawable.edittext_border_error);
                    esCorrecto = false;

                }else {
                    etNuevaContrasena.setBackgroundResource(R.drawable.edittext_border);
                    esCorrecto = true;

                }

                if(etConfirmarContrasena.getText().toString().equals(etNuevaContrasena.getText().toString()) && s.length()>=8)
                {
                    etConfirmarContrasena.setBackgroundResource(R.drawable.edittext_border);
                    etNuevaContrasena.setBackgroundResource(R.drawable.edittext_border);
                    esCorrecto = true;


                }else
                {
                    etConfirmarContrasena.setBackgroundResource(R.drawable.edittext_border_error);
                    etNuevaContrasena.setBackgroundResource(R.drawable.edittext_border_error);
                    esCorrecto = false;


                }

            }
        });

        etConfirmarContrasena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length()==0 || s.length() < 8 )
                {
                    etConfirmarContrasena.setBackgroundResource(R.drawable.edittext_border_error);
                    esCorrecto = false;

                }else {
                    etConfirmarContrasena.setBackgroundResource(R.drawable.edittext_border);
                    esCorrecto = true;

                }

                if(etConfirmarContrasena.getText().toString().equals(etNuevaContrasena.getText().toString()) && s.length()>=8)
                {
                    etConfirmarContrasena.setBackgroundResource(R.drawable.edittext_border);
                    etNuevaContrasena.setBackgroundResource(R.drawable.edittext_border);
                    esCorrecto = true;


                }else
                {
                    etConfirmarContrasena.setBackgroundResource(R.drawable.edittext_border_error);
                    etNuevaContrasena.setBackgroundResource(R.drawable.edittext_border_error);
                    esCorrecto = false;


                }

            }
        });

        swCorreo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Switch btn = (Switch) v;
                final boolean switchChecked = btn.isChecked();

                if (btn.isChecked()) {
                    btn.setChecked(false);
                } else {
                    btn.setChecked(true);
                }

                String message = "¿Seguro que quieres deshabilitar esta notificación?";
                if (!btn.isChecked()) {
                    message = "¿Seguro que quieres habilitar esta notificación?";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Change "this" to `getActivity()` if you're using this on a fragment
                builder.setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // "Yes" button was clicked
                                int activo = 0;
                                if (switchChecked) {
                                    activo = 1;
                                    btn.setChecked(true);
                                    viewModel.updateSwitchUpdate(Integer.parseInt(Constant.get(Constant.ID_PERSONA)) ,3,1);

                                } else {
                                    btn.setChecked(false);
                                    viewModel.updateSwitchUpdate(Integer.parseInt(Constant.get(Constant.ID_PERSONA)) ,3,0);

                                }
                                // viewModel.updateAvatar(2 , etTelefono.getText().toString());


                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
        swPush.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Switch btn = (Switch) v;
                final boolean switchChecked = btn.isChecked();

                if (btn.isChecked()) {
                    btn.setChecked(false);
                } else {
                    btn.setChecked(true);
                }

                String message = "¿Seguro que quieres deshabilitar esta notificación?";
                if (!btn.isChecked()) {
                    message = "¿Seguro que quieres habilitar esta notificación?";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Change "this" to `getActivity()` if you're using this on a fragment
                builder.setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // "Yes" button was clicked
                                if (switchChecked) {
                                    btn.setChecked(true);
                                    viewModel.updateSwitchUpdate(Integer.parseInt(Constant.get(Constant.ID_PERSONA))  ,2,1);

                                } else {
                                    btn.setChecked(false);
                                    viewModel.updateSwitchUpdate(Integer.parseInt(Constant.get(Constant.ID_PERSONA))  ,2,0);

                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
        swSMS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Switch btn = (Switch) v;
                final boolean switchChecked = btn.isChecked();

                if (btn.isChecked()) {
                    btn.setChecked(false);
                } else {
                    btn.setChecked(true);
                }

                String message = "¿Seguro que quieres deshabilitar esta notificación?";
                if (!btn.isChecked()) {
                    message = "¿Seguro que quieres habilitar esta notificación?";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Change "this" to `getActivity()` if you're using this on a fragment
                builder.setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // "Yes" button was clicked
                                if (switchChecked) {
                                    btn.setChecked(true);
                                    viewModel.updateSwitchUpdate(Integer.parseInt(Constant.get(Constant.ID_PERSONA))  ,1,1);

                                } else {
                                    btn.setChecked(false);
                                    viewModel.updateSwitchUpdate(Integer.parseInt(Constant.get(Constant.ID_PERSONA))  ,1,0);

                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }
    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }
*/
    @Override
    public void initializeObjects() {
        /*loginResponse = new UserResponse();
        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);
        if (getArguments() != null) {
            action = getArguments().getString("action");
            loginResponse = getArguments().getParcelable(Constant.KEY_USER_DATA);

        }*/

    }
    @Override
    public void initializeObservers() {
        viewModel.getUsuarioMutableLiveData().observeForever(new Observer<UsuarioResponse>() {
            @Override
            public void onChanged(UsuarioResponse usuario) {
                etEmail.setText(usuario.getCorreo());
                etTelefono.setText(usuario.getTelefono());
                viewModel.requestSwitch(Integer.parseInt(Constant.get(Constant.ID_PERSONA)));

            }
        });

        viewModel.getSwitchMutableLiveData().observeForever(new Observer<List<switchResponse>>() {
            @Override
            public void onChanged(List<switchResponse>  usuario) {

                for (int i=0 ;i<usuario.size();i++)
                {
                    if(usuario.get(i).getIdTipoNotificacion()==1)
                    {
                        if(usuario.get(i).getEstado()==1)
                        {
                            swSMS.setChecked(true);

                        }else if(usuario.get(i).getEstado()==0)
                        {
                            swSMS.setChecked(false);

                        }

                    }else if(usuario.get(i).getIdTipoNotificacion()==2)
                    {
                        if(usuario.get(i).getEstado()==1)
                        {
                            swPush.setChecked(true);

                        }else if(usuario.get(i).getEstado()==0)
                        {
                            swPush.setChecked(false);

                        }
                    }else if(usuario.get(i).getIdTipoNotificacion()==3)
                    {
                        if(usuario.get(i).getEstado()==1)
                        {
                            swCorreo.setChecked(true);

                        }else if(usuario.get(i).getEstado()==0)
                        {
                            swCorreo.setChecked(false);

                        }
                    }
                }


            }
        });

        viewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                if (value != null)
                    pgbSetting.setVisibility(value);
            }
        });

    }

    public void setupViews() {
        ButterKnife.bind(this.getActivity());

    }
    @OnClick(R.id.btnGuardar)
    void onClickBtnbtnGuardar() {

        if(esCorrecto == false)
        {
            Toast.makeText(getActivity(),"Los datos ingresados no son correctos.",Toast.LENGTH_SHORT).show();
        }else
        {
            viewModel.updateAvatar(Integer.parseInt(Constant.get(Constant.ID_PERSONA)) , etTelefono.getText().toString());
            Toast.makeText(getActivity(),"Los datos ingresados fueron actualizados.",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SING_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String message = "¿Desea usar la foto de la red social vinculada?";

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Change "this" to `getActivity()` if you're using this on a fragment
            LayoutInflater factory = LayoutInflater.from(getActivity());
            final View view = factory.inflate(R.layout.sample, null);
            dialog_imageview = view.findViewById(R.id.dialog_imageview);
            Uri uri =  account.getPhotoUrl();
            if( uri !=null) {
                Picasso.with(getActivity()).load(account.getPhotoUrl()).transform(new CircleTransform()).into(dialog_imageview);
            }
            builder.setView(view);
            builder.setMessage(message)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Uri uri =  account.getPhotoUrl();
                            if( uri !=null) {
                                Constant.FOTO_RED_SOCIAL = account.getPhotoUrl().toString();
                                Constant.NOMBRE_RED_SOCIAL = account.getDisplayName().toString();
                                Picasso.with(getActivity()).load(account.getPhotoUrl()).transform(new CircleTransform()).into(logo);
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();

            //gotoProfile();
        }else{
            Toast.makeText(getActivity(),"No se logro vincular.",Toast.LENGTH_SHORT).show();
        }
    }
    /* private void gotoProfile(){
         Intent intent=new Intent(getContext() ,SettingFragment.class);
         startActivity(intent);
     }*/
    AccessTokenTracker acs = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                Toast.makeText(getContext(), "User Logged out", Toast.LENGTH_SHORT).show();
            } else {
                //loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(LoginResult access)
    {
        GraphRequest request= GraphRequest.newMeRequest(access.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                String message = "¿Desea usar la foto de la red social vinculada?";

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // Change "this" to `getActivity()` if you're using this on a fragment
                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View view = factory.inflate(R.layout.sample, null);
                dialog_imageview = view.findViewById(R.id.dialog_imageview);
                try {
                    String image_url = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=normal";
                    URL newurl = new URL(image_url);
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    dialog_imageview.setImageBitmap(mIcon_val);
                    //Picasso.with(getActivity()).load(Uri.parse(image_url)).transform(new CircleTransform()).into(dialog_imageview);
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }

                builder.setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // "Yes" button was clicked
                                try {

                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");

                                    String id = object.getString("id");
                                    String image_url = "https://graph.facebook.com/"+id+"/picture?type=normal";


                                    Log.d("ResponseFacebook", image_url);


                                    Constant.FOTO_RED_SOCIAL = image_url;
                                    Constant.NOMBRE_RED_SOCIAL = first_name;

                                    Picasso.with(getActivity()).load(Uri.parse(image_url)).transform(new CircleTransform()).into(logo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();




            }
        });
        Bundle parametros = new Bundle();
        parametros.putString("fields","first_name,last_name,email,id");
        request.setParameters(parametros);
        request.executeAsync();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

    private void checkLoginStatus()
    {
        Log.d("checkLoginStatus","nocheckLoginStatus");

        if(AccessToken.getCurrentAccessToken()!=null)
        {
            Log.d("checkLoginStatus","checkLoginStatus");
            //loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
            viewModel.requestAvatar(Constant.get(Constant.ID_PERSONA));



    }
}