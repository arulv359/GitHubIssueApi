package com.githubissueapi.app.network;

import com.githubissueapi.app.model.GitHubIssue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubIssueApi {

    //GET GitHub Issues list
    @GET("ReactiveX/RxJava/issues")
    Call<List<GitHubIssue>> getIssues(
    );

}
