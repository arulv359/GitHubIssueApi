package com.githubissueapi.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.githubissueapi.app.R;
import com.githubissueapi.app.model.GitHubIssue;
import com.githubissueapi.app.utils.IListItemClickListener;

import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder> {

    private Context context;
    private List<GitHubIssue> mDataList;
    private IListItemClickListener listItemClickListener;

    public IssuesAdapter(Context ctx, IListItemClickListener listItemClickListener, List<GitHubIssue> dataList) {
        context = ctx;
        this.listItemClickListener = listItemClickListener;
        mDataList = dataList;
    }

    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivPoster;

        public TextView tvTitle;
        public TextView tvRating;
        public TextView tvOverview;
        public TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvRating = (TextView) itemView.findViewById(R.id.tvRating);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listItemClickListener != null)
                listItemClickListener.onListItemClick(v, getPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View inflatedView = inflater.inflate(R.layout.list_item_issue, parent, false);

        ViewHolder viewHolder = new ViewHolder(inflatedView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        GitHubIssue issue = mDataList.get(position);

        viewHolder.tvTitle.setText(issue.getTitle());
        viewHolder.tvOverview.setText(issue.getBody());
        viewHolder.tvDate.setText(issue.getCreated_at().split("T")[0]);

        RequestOptions requestOptions = new RequestOptions().centerCrop().error(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(issue.getUser().getAvatar_url()).into(viewHolder.ivPoster);


    }

    @Override
    public int getItemCount() {
        if (mDataList != null) {
            return mDataList.size();
        } else
            return 0;
    }


}
