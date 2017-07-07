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

import java.util.ArrayList;

public class RecycleAdapterUserInfo extends RecyclerView.Adapter<RecycleAdapterUserInfo.recyclerUsers> {

    public ArrayList<UserPageFragment.UserParams> params;

    public RecycleAdapterUserInfo(ArrayList<UserPageFragment.UserParams> u) {
        this.params = u;
    }

    @Override
    public int getItemCount() {
        return params.size();
    }

    @Override
    public void onBindViewHolder(recyclerUsers userHolder, int i) {
        UserPageFragment.UserParams hi = params.get(i);
        userHolder.image.setImageResource(hi.getImage());
        userHolder.param.setText(hi.getParam());
        userHolder.value.setText(hi.getValue());

    }

    @Override
    public recyclerUsers onCreateViewHolder(ViewGroup viewGroup, final int i) {
        final View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview, viewGroup, false);
        return new recyclerUsers (itemView, this);
    }


    public class recyclerUsers extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView value;
        protected TextView param;
        public recyclerUsers(View v, RecycleAdapterUserInfo p){
            super(v);
            image = (ImageView) v.findViewById(R.id.user_image);
            value = (TextView)v.findViewById(R.id.user_name);
            param = (TextView)v.findViewById(R.id.user_login);
        }
    }

}
