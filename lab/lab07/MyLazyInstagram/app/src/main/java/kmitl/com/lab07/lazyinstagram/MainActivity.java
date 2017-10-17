package kmitl.com.lab07.lazyinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import kmitl.com.lab07.lazyinstagram.adapter.PostAdapter;
import kmitl.com.lab07.lazyinstagram.api.LazyInstagramApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    protected int number = 0, gridorlist = 3;
    protected String[] user = {"android", "nature", "cartoon"};
    //public String current_user = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String current_user = user[number];
        getUserProfile(current_user);


        recycler();
    }
    public void switchUser(View view){
        number++;
        if (number >= 3) {
            number = 0;
        }
        String current_user = user[number];
        getUserProfile(current_user);
    }
    public void gridOrList(View view){
        if (gridorlist == 3) {
            gridorlist = 1;
        }
        else{
            gridorlist = 3;
        }
        recycler();
    }
    public void recycler(){
        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridorlist));
        recyclerView.setAdapter(postAdapter);
    }
    private void getUserProfile(String usrName){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(LazyInstagramApi.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = lazyInstagramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();
                    TextView TextName = (TextView)findViewById(R.id.textName);
                    TextName.setText("@"+userProfile.getUser());
                    TextView TextBoi = (TextView)findViewById(R.id.textBoi);
                    TextBoi.setText(userProfile.getBio());
                    ImageView imageProfile = findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
                    TextView TexTPost = (TextView)findViewById(R.id.textPost);
                    TexTPost.setText("Post\n"+userProfile.getPost());
                    TextView TexTFollower = (TextView)findViewById(R.id.textFollower);
                    TexTFollower.setText("Follow\n"+userProfile.getFollower());
                    TextView TexTFollowing = (TextView)findViewById(R.id.textFollowing);
                    TexTFollowing.setText("Following\n"+userProfile.getFollowing());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
