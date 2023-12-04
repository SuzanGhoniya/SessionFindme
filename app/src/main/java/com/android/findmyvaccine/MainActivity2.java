package com.android.findmyvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity2 extends AppCompatActivity {
    private EditText pinCode;
    private EditText edate;
    private Button findInfo;
    private ListView listView;
    RequestQueue requestQueue;
    ArrayList<vaccine> vaccines = new ArrayList<>();
    ListView display;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pinCode = findViewById(R.id.pinCode);
        edate = findViewById(R.id.get_date);
        findInfo = findViewById(R.id.get_info);
        listView = findViewById(R.id.list_item);
        display =findViewById(R.id.list_item);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        findInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                closeKeyboard();
                String gPinCode = pinCode.getText().toString();
                String gDate = edate.getText().toString();

                if (TextUtils.isEmpty(gPinCode)) {
                    Toast.makeText(MainActivity2.this, "please enter pin code!!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (gPinCode.length() != 6) {
                    Toast.makeText(MainActivity2.this, "please enter valid pin code!!", Toast.LENGTH_LONG).show();
                    return;
                }
                getAppointment(gPinCode, gDate);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference store = db.collection("code").document("count");
                store.update("Count", FieldValue.increment(1));
            }
        });
    }

    private void getAppointment(String gpincode, String gDate) {
        String urlLink = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+gpincode+"&date="+gDate;

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlLink, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    JSONArray centers = response.getJSONArray("sessions");
                    if (centers.length() == 0) {
                        Toast.makeText(MainActivity2.this, "No centers available", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (int i=0; i<centers.length();i++){
                        JSONObject centerObject = centers.getJSONObject(i);
                        String Cn = centerObject.getString("block_name");
                        String cenName = centerObject.getString("name");
                        String time = centerObject.getString("from");
                        String vaccName = centerObject.getString("vaccine");
                        String feeType = centerObject.getString("fee_type");
                        String ageLimit = centerObject.getString("min_age_limit");
                        String avalible = centerObject.getString("available_capacity_dose2");
                        String address = centerObject.getString("address");


                        vaccine v1 = new vaccine(Cn, cenName, vaccName, feeType, avalible, ageLimit, time,address);
                        vaccines.add(v1);
                        ArrayList<vaccine> vaccinesRegis = vaccines;

                        vaccAdapter vaccAdapter=new vaccAdapter(MainActivity2.this, vaccinesRegis);

                        display.setAdapter(vaccAdapter);
                    }
                    Toast.makeText(MainActivity2.this,"session added \nEnd of the list",Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("appData", "Something went wrong");
                Toast.makeText(MainActivity2.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}