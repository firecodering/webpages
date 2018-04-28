package com.example.nebalbarhoome.home3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.accounts.AccountManager.get;

public class MainFragment extends Fragment {



    private RecyclerView mRecyclerView;
    private PageAdapter pageAdapter;


   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    private void showDetails(int i) {
        ((DetailDisplayer)getActivity()).showDetail(i);
    }

    private void updateUI() {

        if (pageAdapter == null) {
            pageAdapter = new PageAdapter();
            mRecyclerView.setAdapter(pageAdapter);
        }else {
            pageAdapter.notifyDataSetChanged();
        }


    }

    private void itemClicked(int index) {
        showDetails(index);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public class PageAdapter extends RecyclerView.Adapter<PageHolder> {

        @Override
        public PageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new PageHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(PageHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return DataBase.getInstance().getPageCount();
        }
    }
    public class PageHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private int index;
        public PageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list_page, parent, false));
            textView = itemView.findViewById(R.id.item_list_page_name);
            itemView.setOnClickListener((view) -> itemClicked(index));
        }

        public void bind(int index) {
            FavoritePage p = DataBase.getInstance().getPage(index);
            textView.setText(p.getName());
            this.index = index;
        }
    }
}

