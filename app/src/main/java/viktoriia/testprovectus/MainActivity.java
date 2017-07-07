package viktoriia.testprovectus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import viktoriia.testprovectus.data.Result;

public class MainActivity extends AppCompatActivity implements ItemClickedInterface {

    private static final String FRAGMENT_TAG = "ULF";
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            UserListFragment ulf = new UserListFragment();
            fragment = ulf;
            transaction.replace(R.id.content_frame, fragment, FRAGMENT_TAG);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else {
            fragment = (UserListFragment)fragmentManager.findFragmentByTag(FRAGMENT_TAG);
            transaction.replace(R.id.content_frame, fragment, FRAGMENT_TAG);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void itemClicked(Result r) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("result", r);
        UserPageFragment upf = new UserPageFragment();
        upf.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = upf;
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
