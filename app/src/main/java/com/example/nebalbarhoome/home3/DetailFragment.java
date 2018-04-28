package com.example.nebalbarhoome.home3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.Serializable;


public class DetailFragment extends Fragment{

    private static final String PAGE_INDEX_KEY = "PAGE_INDEX_KEY";

    // factory method
    public static DetailFragment newInstance(Context c, int pageIndex) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_INDEX_KEY, pageIndex);
        fragment.setArguments(args);
        return fragment;

    }

    private int pageIndex;

    private WebView wv;
    private TextView urlTextView;
    private TextView countTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        pageIndex = b.getInt(PAGE_INDEX_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        wv = v.findViewById(R.id.web_view);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        urlTextView =  v.findViewById(R.id.text_url);
        countTextView = v.findViewById(R.id.text_visit);
        updateUI();
        return v;
    }

    private void updateUI() {
        FavoritePage page =getPage();
        String url = page.getUrl();
        urlTextView.setText(url);
        String count= "" + page.getVisitCount();
        countTextView.setText(count);
        wv.loadUrl(url);
    }
    private FavoritePage getPage() {
        return DataBase.getInstance().getPage(pageIndex);
    }

}
