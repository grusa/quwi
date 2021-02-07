package earth.sochi.quwi.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import earth.sochi.quwi.R;
import earth.sochi.quwi.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private Button buttonLogin;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor ;
    private final String TAG = "LF";
    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.bind(view);
        buttonLogin  = binding.buttonLogin;
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        binding.userName.setText(sharedPref.getString( getString(R.string.email), "Enter email"));
        binding.userPass.setText(sharedPref.getString(getResources().getString(R.string.password), "Enter password"));
        if (!isNetworkConnected()) {
            binding.buttonLogin.setText("No internet");
            binding.buttonLogin.setEnabled(false);
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (getLogin(binding.userName.getText().toString(),
                        binding.userPass.getText().toString()) == "token") {
                    try {
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_projectListFragment);
                    } catch (Exception e) {
                        Log.d(TAG,e.toString());
                    }
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onDestroyView(){
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(getResources().getString(R.string.email), binding.userName.getText().toString());
        editor.putString(getResources().getString(R.string.password), binding.userPass.getText().toString());
        editor.apply();
        logout();
        super.onDestroyView();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    private void logout() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =getString(R.string.baseUrl)+"auth/logout";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
    private String getLogin (String userName,String userPassword) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =getString(R.string.baseUrl)+"auth/login";

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", userName);
            postData.put("password",userPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(),
                                response.toString(),Toast.LENGTH_LONG).show();
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Toast.makeText(getContext(),
                                        jsonObject.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                        catch ( org.json.JSONException e){
                            Toast.makeText(getContext(),
                                    e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
        return "token";
    }

}