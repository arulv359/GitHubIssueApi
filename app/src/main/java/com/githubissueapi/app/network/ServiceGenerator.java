package com.githubissueapi.app.network;

import com.githubissueapi.app.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    public static GitHubIssueApi gitHubIssueApi = retrofit.create(GitHubIssueApi.class);

    public static GitHubIssueApi getGitHubIssueApi() {
        return gitHubIssueApi;
    }


}
