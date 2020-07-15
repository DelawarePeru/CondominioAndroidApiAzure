package pe.com.condominioandroidapi.util.basecomponent;


import androidx.lifecycle.MutableLiveData;

public class BaseDataModel {

    public MutableLiveData<Integer> errorCodeLiveData;
    public MutableLiveData<String> errorMessageLiveData;

    public BaseDataModel() {
        this.errorCodeLiveData = new MutableLiveData<>();
        this.errorMessageLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getErrorCodeLiveData() {
        return errorCodeLiveData;
    }

    public MutableLiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }
}
