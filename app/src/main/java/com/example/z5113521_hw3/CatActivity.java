package com.example.z5113521_hw3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        Bundle b = getIntent().getExtras();
        String receivingId = b.getString("catId");
        String receivingName = b.getString("catName");
        String receivingDesc = b.getString("catDesc");
        Serializable receivingWeight = b.getSerializable("catWeight");
        String receivingTemp = b.getString("catTemp");
        String receivingOrigin = b.getString("catOrigin");
        String receivingLife = b.getString("catLife");
        String receivingWiki = b.getString("catWiki");
        int receivingDog = b.getInt("catDog");

        String catId = receivingId;
        final String catName = receivingName;
        String catDesc = receivingDesc;

        Serializable catWeight = receivingWeight;
        Weight testWeight = (Weight)catWeight;
        String catImperial = testWeight.getImperial();
        String catMetric = testWeight.getMetric();

        String catTemp = receivingTemp;
        String catOrigin = receivingOrigin;
        String catLife = receivingLife;
        String catWiki = receivingWiki;
        int catDog = receivingDog;
        String catDog2 = Integer.toString(catDog);

        TextView DetailName = findViewById(R.id.DetailName);
        DetailName.setText(catName);

        TextView DetailDesc = findViewById(R.id.DetailDesc);
        DetailDesc.setText(catDesc);

        TextView DetailImperial = findViewById(R.id.DetailImperial);
        DetailImperial.setText(catImperial + "lbs");

        TextView DetailMetric = findViewById(R.id.DetailMetric);
        DetailMetric.setText(catMetric + "kgs");

        TextView DetailTemp = findViewById(R.id.DetailTemp);
        DetailTemp.setText(catTemp);

        TextView DetailOrigin = findViewById(R.id.DetailOrigin);
        DetailOrigin.setText(catOrigin);

        TextView DetailLife = findViewById(R.id.DetailLife);
        DetailLife.setText(catLife);

        TextView DetailWiki = findViewById(R.id.DetailWiki);
        DetailWiki.setText(catWiki);

        TextView DetailDog = findViewById(R.id.DetailDog);
        DetailDog.setText(catDog2);

        Button addFavourite = findViewById(R.id.FavouriteButton);

        addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase database = Room.databaseBuilder(CatActivity.this, AppDatabase.class, "db-favourites")
                        .allowMainThreadQueries()   //Allows room to do operation on main thread
                        .build();
                FavouriteDAO favouriteDAO = database.getFavouriteDAO();
                Favourite f1 = new Favourite(catName);

                if (favouriteDAO.getFavouriteWithName(f1.getCatName()) == null ){
                    favouriteDAO.insert(f1);
                    Toast toast = Toast.makeText(CatActivity.this, "This cat has been added to your favourites", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(CatActivity.this, "This cat has already been added to your favourites", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + catId;
        RequestQueue queue = Volley.newRequestQueue(CatActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JsonParser jsonParser = new JsonParser();
                            JsonArray jsonObject = (JsonArray)jsonParser.parse(response);

                            Type articleType = new TypeToken<ArrayList<Image>>(){}.getType();
                            Gson mGson = new Gson();
                            ArrayList<Image> jsonObjList = mGson.fromJson(jsonObject, articleType);

                            Image i1 =  jsonObjList.get(0);
                            String catimage = i1.getUrl();

                            ImageView DetailImage = findViewById(R.id.DetailImage);
                            Glide.with(DetailImage.getContext()).load(catimage).into(DetailImage);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);

    }
}
