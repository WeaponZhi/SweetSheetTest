package com.weaponzhi.sweetsheettest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import java.util.List;

/**
 * Created by pc on 2017/4/20.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerVH> {
    List<String> mDataList;

    public RecyclerAdapter(List<String> dataLis) {
        mDataList = dataLis;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizon, null, false);
        return new RecyclerVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {
        holder.nameTV.setText(mDataList.get(position));
        animation(holder);
    }

    private void animation(RecyclerVH vh) {

        ViewHelper.setAlpha(vh.itemView, 0);

        ViewHelper.setTranslationY(vh.itemView, 1000);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(vh.itemView, "translationY", 500, 0);
        translationY.setDuration(500);
        translationY.setInterpolator(new OvershootInterpolator(2.0f));
        ObjectAnimator alphaIn = ObjectAnimator.ofFloat(vh.itemView, "alpha", 0, 1);
        alphaIn.setDuration(100);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationY, alphaIn);
        animatorSet.setStartDelay(30 * vh.getAdapterPosition());
        animatorSet.start();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class RecyclerVH extends RecyclerView.ViewHolder {

        public TextView nameTV;

        public RecyclerVH(View itemView) {
            super(itemView);

            nameTV = (TextView) itemView.findViewById(R.id.tv_right);
        }
    }
}
