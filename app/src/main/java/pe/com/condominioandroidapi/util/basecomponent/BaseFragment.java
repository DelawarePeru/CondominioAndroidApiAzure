package pe.com.condominioandroidapi.util.basecomponent;


import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public String action;

    public void initializeView() {
        initializeObjects();
        initializeObservers();
        setupViews();
    }

    public abstract void initializeObjects();
    public abstract void initializeObservers();
    public abstract void setupViews();

    public String getStringResource(int resourceId) {
        return getResources().getString(resourceId);
    }
}
