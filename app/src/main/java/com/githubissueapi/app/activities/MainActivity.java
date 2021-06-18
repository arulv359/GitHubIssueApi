package com.githubissueapi.app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.githubissueapi.app.R;
import com.githubissueapi.app.adapters.IssuesAdapter;
import com.githubissueapi.app.model.GitHubIssue;
import com.githubissueapi.app.network.ServiceGenerator;
import com.githubissueapi.app.utils.IListItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity implements IListItemClickListener {

    private Context context;
    private List<GitHubIssue> issues;
    private IssuesAdapter issuesAdapter;
    private RecyclerView recyclerView;
    private TextView txvEmptyList;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        recyclerView = findViewById(R.id.recyclerView);
        txvEmptyList = findViewById(R.id.txvEmptyList);
        progressBar = findViewById(R.id.progressBar);

        issuesAdapter = new IssuesAdapter(context, this, issues);
        recyclerView.setAdapter(issuesAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.loadMoreDataFromApi();
    }

    @Override
    public void onListItemClick(View view, int position) {

        GitHubIssue obj = issues.get(position);
        Intent intent = new Intent(this, IssueDetailActivity.class);
        intent.putExtra("ISSUE", obj);
        startActivity(intent);
    }


    // Network request
    public void loadMoreDataFromApi() {

        //Show Progress Dialog
        showProgressBar(true);

        Call<List<GitHubIssue>> issueResultCallback = ServiceGenerator.getGitHubIssueApi().getIssues();

        // asynchronous call
        issueResultCallback.enqueue(new Callback<List<GitHubIssue>>() {

            @Override
            public void onResponse(Call<List<GitHubIssue>> call, retrofit2.Response<List<GitHubIssue>> response) {
                showProgressBar(false);
                List<GitHubIssue> gitHubIssueList = response.body();

                //Set Issues Adapter
                if (gitHubIssueList != null && gitHubIssueList.size() > 0) {
                    MainActivity.this.issues = gitHubIssueList;
                    issuesAdapter = new IssuesAdapter(context, MainActivity.this, issues);
                    recyclerView.setAdapter(issuesAdapter);
                    issuesAdapter.notifyDataSetChanged();
                } else if (issues == null) {
                    showEmptyList(true);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubIssue>> call, Throwable t) {

            }
        });

    }


    private void showProgressBar(boolean value) {
        if (value)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    private void showEmptyList(boolean show) {
        if (show)
            txvEmptyList.setVisibility(View.VISIBLE);
        else
            txvEmptyList.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
