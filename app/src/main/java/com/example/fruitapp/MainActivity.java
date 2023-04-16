package com.example.fruitapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FruitItem> data = new ArrayList<>();
    RecyclerView.Adapter adapter;
    EditText fruitET;
    String FRUIT_SYMBOL;
    Button btn;
    Button addBtn;
    Button deleteBtn;
    Button updateBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager

        btn = findViewById(R.id.go_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFruit(new FruitItem("Banana", "Chuoi", 1, 2, 3));
            }
        });


        deleteBtn = findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFruit("1");
            }
        });

        updateBtn = findViewById(R.id.update_btn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get old fruit...

                FruitItem newFruit = new FruitItem("Apple", "a", 1, 2, 3);

                updateFruit("2", newFruit);
            }
        });


        //set adapter to datasource
        adapter = new RecyclerAdapter(data);
        // assign the adapter to the recycler view
        recyclerView.setAdapter(adapter);

    }

    private void updateFruit(String id, FruitItem fruit){
        Log.i("log", "UPDATE");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://64107e277b24bb91f21efea4.mockapi.io/fruits/" + id;

        JSONObject body = new JSONObject();
        try {
            body.put("name", fruit.getName());
            body.put("family", fruit.getFamily());

            JSONObject nutritions = new JSONObject();
            nutritions.put("sugar", fruit.getSugar());
            nutritions.put("calories", fruit.getCalories());
            nutritions.put("carbohydrates", fruit.getCarbohydrates());

            body.put("nutritions", nutritions);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.PUT, url, body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("stock", error.getMessage());
                    }
                });

        // due to long response time, we need to add a long delay time
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void deleteFruit(String id){
        Log.i("log", "DELETE");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://64107e277b24bb91f21efea4.mockapi.io/fruits/" + id;

        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.DELETE, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("stock", error.getMessage());
                    }
                });

        // due to long response time, we need to add a long delay time
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void addFruit(FruitItem fruit){
        Log.i("log", "Add");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://64107e277b24bb91f21efea4.mockapi.io/fruits/";

        JSONObject requestBody = new JSONObject();

        try {
            requestBody.put("name", fruit.getName());
            requestBody.put("family", fruit.getFamily());

            JSONObject nutritionsObject = new JSONObject();
            nutritionsObject.put("calories", fruit.getCalories());
            nutritionsObject.put("sugar", fruit.getSugar());
            nutritionsObject.put("carbohydrates", fruit.getCarbohydrates());

            requestBody.put("nutritions", nutritionsObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.POST, url, requestBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("stock", error.getMessage());
                    }
                });

        // due to long response time, we need to add a long delay time
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void makeRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        fruitET = findViewById(R.id.fruit_id);

        FRUIT_SYMBOL = fruitET.getText().toString();
        String url =
                "https://64107e277b24bb91f21efea4.mockapi.io/fruits/" + FRUIT_SYMBOL;

        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String name = response.getString("name");
                                    String family = response.getString("family");
                                    JSONObject nutritionObj = response.getJSONObject("nutritions");
                                    int calories = nutritionObj.getInt("calories");
                                    int sugar = nutritionObj.getInt("sugar");
                                    int carbohydrates = nutritionObj.getInt("carbohydrates");

                                    //add fruit with details
                                    data.add(new FruitItem(name, family, calories, sugar, carbohydrates));

                                    adapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("stock", error.getMessage());
                    }
                });

        // due to long response time, we need to add a long delay time
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
