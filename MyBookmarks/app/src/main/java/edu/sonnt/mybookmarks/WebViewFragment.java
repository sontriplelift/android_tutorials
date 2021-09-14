package edu.sonnt.mybookmarks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        // refs
        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); //without this line => open browser
        // enable js
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // get passed url
        String url = getArguments().getString("URL");
        webView.loadUrl(url);
        // handle events
        Button btnBack = view.findViewById(R.id.back);
        btnBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:

                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
                break;
        }
    }
}