package pe.com.condominioandroidapi.util;

import android.content.SharedPreferences;

public class AccessToken {

    private String mApiAccessToken;

    public AccessToken() {
    }

    public AccessToken(String apiAccessToken) {
        mApiAccessToken = apiAccessToken;
    }

    public AccessToken(SharedPreferences sharedPreferences) {
        loadAccessToken(sharedPreferences);
    }

    private void loadAccessToken(SharedPreferences sharedPreferences) {
        mApiAccessToken = sharedPreferences.getString(Constant.KEY_ACCESS_TOKEN, "");
    }

    public String getApiAccessToken() {
        return mApiAccessToken;
    }

    public static boolean updatePreferencesAccessToken(SharedPreferences sharedPreferences,
                                                       String apiAccessToken) {
        if(apiAccessToken != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constant.KEY_ACCESS_TOKEN, apiAccessToken);
            editor.apply();
            return true;
        }
        return false;
    }
}
