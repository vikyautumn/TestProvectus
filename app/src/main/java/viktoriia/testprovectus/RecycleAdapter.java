package viktoriia.testprovectus;

/**
 * Created by Viktoriia on 05.07.2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import viktoriia.testprovectus.data.Result;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.recyclerUsers> {

    public ArrayList<Result> users;
    private UserListFragment act;

    public RecycleAdapter(ArrayList<Result> u, UserListFragment par) {
        this.users = u;
        this.act = par;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(recyclerUsers userHolder, int i) {
        Result hi = users.get(i);

        String name = hi.getName().getFirst();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        userHolder.name.setText(name + " ");
        name = hi.getName().getLast();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        userHolder.name.append(name);

        userHolder.login.setText(hi.getEmail());

        Glide.with(act).load(hi.getPicture().getThumbnail()).apply(RequestOptions.circleCropTransform()).into(userHolder.image);

    }

    @Override
    public recyclerUsers onCreateViewHolder(ViewGroup viewGroup, final int i) {
        final View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview, viewGroup, false);
        return new recyclerUsers (itemView, this);
    }
    public void sentPosition (int i){
        act.ItemClicked(users.get(i));
    }


    public class recyclerUsers extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecycleAdapter parent;
        protected ImageView image;
        protected TextView name;
        protected TextView login;
        public recyclerUsers(View v, RecycleAdapter p){
            super(v);
            parent = p;
            v.setOnClickListener(this);
            image = (ImageView) v.findViewById(R.id.user_image);
            name = (TextView)v.findViewById(R.id.user_name);
            login = (TextView)v.findViewById(R.id.user_login);
        }

        @Override
        public void onClick(View v) {
            parent.sentPosition(getAdapterPosition());
        }
    }

}
