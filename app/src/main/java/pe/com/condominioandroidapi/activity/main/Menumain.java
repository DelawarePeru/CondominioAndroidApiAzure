package pe.com.condominioandroidapi.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class Menumain extends BaseActivity {

    @BindView(R.id.vwCircular)
    ImageView vwCircular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_home);
        Log.d("FOTO_RED_SOCIAL", Constant.FOTO_RED_SOCIAL.toString());

        if(Constant.FOTO_RED_SOCIAL != null)
        {
            Log.d("FOTO_RED_SOCIAL", Constant.FOTO_RED_SOCIAL.toString());
            Glide.with(this).load(Constant.FOTO_RED_SOCIAL).into(vwCircular);

        }




    }
    @Override
    public void initializeObjects() {

    }

    @Override
    public void initializeObservers() {

    }

    @Override
    public void setupViews() {

    }
}
