package pe.com.condominioandroidapi.util.basecomponent;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public abstract  class BaseViewModel extends AndroidViewModel{

    private MutableLiveData<Integer> pgbVisibility;
    public MutableLiveData<String> messageResult;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        messageResult = new MutableLiveData<>();
        pgbVisibility = new MutableLiveData<>();
        messageResult.observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                onRetrieveDataFinish();
            }
        });
    }
    public abstract void setupObservers();

    public LiveData<Integer> getPgbVisibility() {
        return pgbVisibility;
    }
    public LiveData<String> getMessage() {
        return messageResult;
    }
    protected void onRetrieveData() {
        pgbVisibility.setValue(View.VISIBLE);
    }
    public void onRetrieveDataFinish() {
        pgbVisibility.setValue(View.GONE);
    }

    public String getMessageByErrorCode(Integer code) {
        switch (code) {
            case 400: return "Error: El estado de la solicitud no permite realizar esta acción";
            case 401: return "Credenciales incorrectas";
            case 404: return "Estamos trabajando en ello, inténtelo más tarde";
            default: return "Fallo en el servicio, inténtelo más tarde";
        }
    }
}
