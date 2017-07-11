package viktoriia.testprovectus;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import viktoriia.testprovectus.data.PostModel;
import viktoriia.testprovectus.data.Result;

/**
 * Created by Viktoriia on 07.07.2017.
 */

public class UserListFragment extends Fragment {

    View v = null;
    ArrayList<Result> users;

    public UserListFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.user_list, null);
        super.onCreate(savedInstanceState);
        users = new ArrayList<Result>();
        final ProgressBar pb = (ProgressBar) v.findViewById(R.id.progressBar1);
        final RecyclerView recList = (RecyclerView) v.findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        RecyclerView.Adapter mAdapter;
        mAdapter = new RecycleAdapter(users, this);
        recList.setAdapter(mAdapter);
        if (savedInstanceState != null) {
            users = (ArrayList<Result>) savedInstanceState.getSerializable("users");
            pb.setVisibility(View.INVISIBLE);
            if (users.size()>0) {
                recList.setVisibility(View.VISIBLE);
                mAdapter = new RecycleAdapter(users, this);
                recList.setAdapter(mAdapter);
                recList.getAdapter().notifyDataSetChanged();
            }
            else {
                TextView tv = (TextView) v.findViewById(R.id.textView);
                tv.setVisibility(View.VISIBLE);
            }
        }
        else {
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
                    TextView tv = (TextView) v.findViewById(R.id.textView);
                    tv.setVisibility(View.VISIBLE);
                    System.out.println(t.getMessage() + call.toString());
                    Toast.makeText(v.getContext(), " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void ItemClicked (Result r){
        Object o = getActivity();
        if(o instanceof ItemClickedInterface){
            ((ItemClickedInterface)o).itemClicked(r);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("users", users);
        super.onSaveInstanceState(savedInstanceState);
    }
}
