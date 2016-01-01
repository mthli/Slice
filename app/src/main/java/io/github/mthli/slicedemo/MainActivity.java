/*
 * Copyright (C) 2016 Matthew Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.mthli.slicedemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import io.github.mthli.slice.Slice;

public class MainActivity extends Activity {
    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
        private static final int VIEW_TYPE_TOP = 0x01;
        private static final int VIEW_TYPE_CENTER = 0x02;
        private static final int VIEW_TYPE_BOTTOM = 0x03;

        private Context context;

        public RecyclerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return VIEW_TYPE_TOP;
            } else if (position == getItemCount() - 1) {
                return VIEW_TYPE_BOTTOM;
            } else {
                return VIEW_TYPE_CENTER;
            }
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_TOP) {
                return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_top, parent, false));
            } else if (viewType == VIEW_TYPE_CENTER) {
                return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_center, parent, false));
            } else {
                return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_bottom, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerHolder holder, int position) {
            Slice slice = new Slice(holder.getFrame());
            slice.setElevation(2.0f);

            if (position == 0) {
                slice.setRadius(4.0f);
                slice.showLeftTopRect(false);
                slice.showRightTopRect(false);
                slice.showRightBottomRect(true);
                slice.showLeftBottomRect(true);
                slice.showTopEdgeShadow(true);
                slice.showBottomEdgeShadow(false);
            } else if (position == getItemCount() - 1) {
                slice.setRadius(4.0f);
                slice.showLeftTopRect(true);
                slice.showRightTopRect(true);
                slice.showRightBottomRect(false);
                slice.showLeftBottomRect(false);
                slice.showTopEdgeShadow(false);
                slice.showBottomEdgeShadow(true);
            } else {
                slice.setRadius(0.0f);
                slice.showTopEdgeShadow(false);
                slice.showBottomEdgeShadow(false);
            }
        }
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        private FrameLayout frame;

        public RecyclerHolder(View view) {
            super(view);
            this.frame = (FrameLayout) view.findViewById(R.id.frame);
        }

        public FrameLayout getFrame() {
            return frame;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new RecyclerAdapter(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, 16.0f, 16.0f));
    }
}
