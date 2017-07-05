package viktoriia.testprovectus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import viktoriia.testprovectus.data.PostModel;
import viktoriia.testprovectus.data.Result;

public class MainActivity extends AppCompatActivity {

    ArrayList<Result> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
        users = new ArrayList<Result>();
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);


        final RecyclerView recList = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        RecyclerView.Adapter mAdapter;
        mAdapter = new RecycleAdapter(users, this);
        recList.setAdapter(mAdapter);
        MainClass.getApi().getData(10).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                users.addAll(response.body().getResults());
                pb.setVisibility(View.INVISIBLE);
                recList.setVisibility(View.VISIBLE);
                recList.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setVisibility(View.VISIBLE);
                System.out.println(t.getMessage() + call.toString()) ;
                Toast.makeText(MainActivity.this, " " + t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ItemClicked (Result r){
        Intent intent = new Intent(this, UserInfoActivity.class);
        intent.putExtra("user", r);
        startActivity(intent);
    }
}
