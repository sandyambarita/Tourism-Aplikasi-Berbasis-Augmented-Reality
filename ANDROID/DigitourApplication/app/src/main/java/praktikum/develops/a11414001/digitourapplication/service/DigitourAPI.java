package praktikum.develops.a11414001.digitourapplication.service;

import praktikum.develops.a11414001.digitourapplication.response.ResponseAchievement;
import praktikum.develops.a11414001.digitourapplication.response.ResponseAchievementUser;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilAbout;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilChallengeType;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilCheckpoint;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilLocation;
import praktikum.develops.a11414001.digitourapplication.response.ResponseHasilQuestion;
import praktikum.develops.a11414001.digitourapplication.response.ResponseLeaderboard;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DigitourAPI {
    @POST("/pa3ws/digitour/dataLocation")
    Call<ResponseHasilLocation> getHasillocation();

    @GET("/pa3ws/digitour/dataCheckpoint")
    Call<ResponseHasilCheckpoint> getHasilCheckpoint();

    @POST("/pa3ws/digitour/dataQuestion")
    Call<ResponseHasilQuestion> getHasilQuestion();

    @POST("/pa3ws/digitour/dataChallengeType")
    Call<ResponseHasilChallengeType> getHasilChallenge();

    @POST("/pa3ws/digitour/dataAbout")
    Call<ResponseHasilAbout> getHasilAbout();

    @POST("/pa3ws/digitour/dataLeaderboard")
    Call<ResponseLeaderboard> getHasilLeaderboard();

    @POST("/pa3ws/digitour/dataAchievement")
    Call<ResponseAchievement> getHasilAchievement();

    @POST("/pa3ws/digitour/dataAchievementUser")
    Call<ResponseAchievementUser> getHasilAchievementUser();

    @POST("/pa3ws/digitour/tambahLeaderboard/{challenge_type_name}/{checkpoint_name}/{user}/{score}/{date_time}")
    Call<ResponseLeaderboard> tambahLeaderboard(@Path("challenge_type_name") String challenge_type_name, @Path("checkpoint_name") String checkpoint_name, @Path("user") String user, @Path("score") int score, @Path("date_time") String date_time);

    @POST("/pa3ws/digitour/tambahAchievementUser/{user}/{checkpoint_name}/{challenge_type_name}/{hadiah}")
    Call<ResponseAchievementUser> tambahAchievementUser(@Path("user") String user, @Path("checkpoint_name") String checkpoint_name, @Path("challenge_type_name") String challenge_type_name, @Path("hadiah") String hadiah);

}
