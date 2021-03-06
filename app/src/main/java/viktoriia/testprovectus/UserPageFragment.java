package viktoriia.testprovectus;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import viktoriia.testprovectus.data.Result;

/**
 * Created by Viktoriia on 07.07.2017.
 */

public class UserPageFragment extends Fragment {

    View v;
    Result res;

    public UserPageFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.user_page, null);
        super.onCreate(savedInstanceState);
        res = (Result) getArguments().getSerializable("result");
        setHasOptionsMenu(true);
        Toolbar tb = (Toolbar)v.findViewById(R.id.main_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name = res.getName().getFirst();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        tb.setTitle(name + " ");
        name = res.getName().getLast();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        tb.setTitle(tb.getTitle() + name);
        ImageView iv = (ImageView) v.findViewById(R.id.main_backdrop);
        Glide.with(this).load(res.getPicture().getLarge()).into(iv);
        ArrayList<UserParams> list = getUserInfoParsed(res);

        final RecyclerView recList = (RecyclerView) v.findViewById(R.id.recyclerUsers);
        LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        RecyclerView.Adapter mAdapter;
        mAdapter = new RecycleAdapterUserInfo(list);
        recList.setAdapter(mAdapter);


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public ArrayList<UserParams> getUserInfoParsed(Result r){
        ArrayList<UserParams> arr = new ArrayList<>();
        UserParams cur;
        String str, temp =  new String();
        String upper = r.getName().getTitle();
        upper = upper.substring(0,1).toUpperCase() + upper.substring(1);
        str = upper + " " + upperString(r.getName().getFirst()) + " " +upperString(r.getName().getLast());
        cur = new UserParams(R.drawable.account, "Name", str);
        arr.add(cur);
        cur = new UserParams(0, "Username", r.getLogin().getUsername());
        arr.add(cur);
        cur = new UserParams(R.drawable.phone, "Phone", r.getPhone());
        arr.add(cur);
        cur = new UserParams(0, "Cell", r.getCell());
        arr.add(cur);
        cur = new UserParams(R.drawable.email, "Email", r.getEmail());
        arr.add(cur);

        str = upperString(r.getLocation().getStreet()) + " ";

        str = str + upperString(r.getLocation().getCity()) + " ";

        str = str + upperString(r.getLocation().getState()) + " ";

        str = str + r.getLocation().getPostcode();

        cur = new UserParams(R.drawable.map_marker, "Location", str);
        arr.add(cur);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMMM yyyy");
        try {
            str = sdf1.format(sdf.parse(r.getDob()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cur = new UserParams(R.drawable.calendar, "Date of birth", str);
        arr.add(cur);
        return arr;
    }

    public String upperString(String upper){ //uppercase for the first letter of every word ↓
        upper = upper.substring(0,1).toUpperCase() + upper.substring(1);
        int i = 0;
        String temp = new String();
        while (upper.indexOf(' ')!=-1){
            i = upper.indexOf(' ');
            temp = temp + upper.substring(0, 1).toUpperCase()  + upper.substring(1, i) + " ";
            upper = upper.substring(i+1);
        }
        if(upper.length()>0)
            upper = upper.substring(0,1).toUpperCase() + upper.substring(1);
        temp = temp + upper;
        return temp;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                getFragmentManager().popBackStack();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public class UserParams {
        private int image;
        private String param;
        private String value;

        public UserParams(int image, String param, String value){
            this.image = image;
            this.param = param;
            this.value = value;
        }

        public int getImage(){
            return this.image;
        }

        public void setImage(int image){
            this.image = image;
        }

        public String getParam(){
            return this.param;
        }

        public void setParam(String param){
            this.param = param;
        }

        public String getValue(){
            return this.value;
        }

        public void setValue(String value){
            this.value = value;
        }
    }
}
