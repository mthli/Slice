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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import io.github.mthli.slice.Slice;

public class MainActivity extends Activity {
    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
        private Context context;

        public RecyclerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        @Override
        public void onBindViewHolder(final RecyclerHolder holder, int position) {
            Slice slice = new Slice(holder.getFrame());
            slice.setRadius(8.0f);
            slice.setElevation(8.0f);

            if (position == 0) {
                slice.showLeftTopRect(false);
                slice.showRightTopRect(false);
                slice.showRightBottomRect(true);
                slice.showLeftBottomRect(true);
                slice.showTopEdgeShadow(true);
                slice.showBottomEdgeShadow(false);
            } else if (position == getItemCount() - 1) {
                slice.showLeftTopRect(true);
                slice.showRightTopRect(true);
                slice.showRightBottomRect(false);
                slice.showLeftBottomRect(false);
                slice.showTopEdgeShadow(false);
                slice.showBottomEdgeShadow(true);
            } else {
                slice.showLeftTopRect(true);
                slice.showRightTopRect(true);
                slice.showRightBottomRect(true);
                slice.showLeftBottomRect(true);
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
    }
}
