package com.githubissueapi.app.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.githubissueapi.app.R;
import com.githubissueapi.app.model.GitHubIssue;

public class IssueDetailActivity extends AppCompatActivity {

    private Context context;
    private GitHubIssue issue;
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);

        issue = getIntent().getParcelableExtra("ISSUE");
        context = this;
        imageView = findViewById(R.id.imageViewHeader);
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        loadMoreDataFromApi(issue);
    }

    // Network request
    public void loadMoreDataFromApi(final GitHubIssue anIssue) {

        if (anIssue != null) {

            RequestOptions requestOptions = new RequestOptions().centerCrop().error(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(anIssue.getUser().getAvatar_url()).into(imageView);
            tvTitle.setText(anIssue.getTitle());
            tvOverview.setText(anIssue.getBody());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
