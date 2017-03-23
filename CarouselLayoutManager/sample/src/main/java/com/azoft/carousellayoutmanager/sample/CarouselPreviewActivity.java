package com.azoft.carousellayoutmanager.sample;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.azoft.carousellayoutmanager.DefaultChildSelectionListener;
import com.azoft.carousellayoutmanager.sample.databinding.ActivityCarouselPreviewBinding;
import com.azoft.carousellayoutmanager.sample.databinding.ItemViewBinding;

import java.util.Locale;
import java.util.Random;

public class CarouselPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityCarouselPreviewBinding binding = DataBindingUtil
            .setContentView(this, R.layout.activity_carousel_preview);

        final TestAdapter adapter = new TestAdapter();

        // create layout manager with needed params: vertical, cycle
        initRecyclerView(binding.listVertical, new CarouselLayoutManager(
            CarouselLayoutManager.VERTICAL, true), adapter);
    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final TestAdapter adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        layoutManager.setMaxVisibleItems(2);

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());
        // enable center post touching on item and item click listener
    }

    private static final class TestAdapter extends RecyclerView.Adapter<TestViewHolder> {
        private final static int ITEMS = 5;

        @Override
        public TestViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return new TestViewHolder(ItemViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(final TestViewHolder holder, final int position) {
            holder.bind();
        }

        @Override
        public int getItemCount() {
            return ITEMS;
        }
    }

    private static class TestViewHolder extends RecyclerView.ViewHolder {

        private final ItemViewBinding mItemViewBinding;

        TestViewHolder(final ItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());

            mItemViewBinding = itemViewBinding;
        }

        public void bind() {
            mItemViewBinding.text.setText("Item: "+getAdapterPosition());
        }
    }
}