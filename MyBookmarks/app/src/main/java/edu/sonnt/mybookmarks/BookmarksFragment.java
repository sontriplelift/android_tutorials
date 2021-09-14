package edu.sonnt.mybookmarks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookmarksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookmarksFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bookmarks, container, false);
        LinearLayout zingmp3 = view.findViewById(R.id.zingmp3);
        zingmp3.setOnClickListener(this);
        LinearLayout bluezone = view.findViewById(R.id.bluezone);
        bluezone.setOnClickListener(this);
        LinearLayout baomoi = view.findViewById(R.id.baomoi);
        baomoi.setOnClickListener(this);
        LinearLayout medium = view.findViewById(R.id.medium);
        medium.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zingmp3:
                FragmentManager manager = getFragmentManager();
                Fragment fragment = new WebViewFragment();

                // pass argument
                Bundle args = new Bundle();
                args.putString("URL", "https://zingmp3.vn/"); // putInt, putDouble...
                fragment.setArguments(args);

                manager.beginTransaction()
                        .replace(R.id.fragmentsContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.bluezone:
                manager = getFragmentManager();
                fragment = new WebViewFragment();

                // pass argument
                args = new Bundle();
                args.putString("URL", "https://bluezone.gov.vn/"); // putInt, putDouble...
                fragment.setArguments(args);

                manager.beginTransaction()
                        .replace(R.id.fragmentsContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.baomoi:
                manager = getFragmentManager();
                fragment = new WebViewFragment();

                // pass argument
                args = new Bundle();
                args.putString("URL", "https://baomoi.com/"); // putInt, putDouble...
                fragment.setArguments(args);

                manager.beginTransaction()
                        .replace(R.id.fragmentsContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.medium:
                manager = getFragmentManager();
                fragment = new WebViewFragment();

                // pass argument
                args = new Bundle();
                args.putString("URL", "https://medium.com/"); // putInt, putDouble...
                fragment.setArguments(args);

                manager.beginTransaction()
                        .replace(R.id.fragmentsContainer, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}