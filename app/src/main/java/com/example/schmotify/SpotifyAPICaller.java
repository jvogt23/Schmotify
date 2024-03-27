package com.example.schmotify;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpotifyAPICaller {
    public static final String CLIENT_ID = "a490a8f4469741a8a198f15187f11ff8";
    public static final String REDIRECT_URI = "com.example.schmotify://auth";

    public static final int AUTH_TOKEN_REQUEST_CODE = 0;
    public static final int AUTH_CODE_REQUEST_CODE = 1;
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static String mAccessToken;
    private Call mCall;

    /**
     * Constructor for new SpotifyAPICaller.
     * Access code and access token are both static, and they should always be provided
     * to this method if they are null. If they are not null, the access code and access token
     * parameters do not need to be null.
     * Do note that this method does NOT check for the scope of the access token given.
     * The required scopes are:
     * user-library-read
     * user-follow-read
     * user-top-read
     * user-read-email
     * @param accessToken A token used for API calls.
     */
    public SpotifyAPICaller(@Nullable String accessToken) {
        if (accessToken == null && mAccessToken == null) {
            throw new IllegalArgumentException(
                    "Access code or token provided was null and no code exists already."
            );
        }
        mAccessToken = accessToken;
    }




    /**
     * Retrieves JSON representing a user's profile from the Spotify API.
     * Returns null if the call was unsuccessful, and LogCat should be
     * referenced if this method returns null.
     * @return A JSON object representing a Spotify user profile.
     */
    public LiveData<JSONObject> getSpotifyUserProfile() {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();

        cancelCall();
        mCall = mOkHttpClient.newCall(request);
        MutableLiveData<JSONObject> obj = new MutableLiveData<>();
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("HTTP", "Failed to fetch data: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    final JSONObject jsonObject = new JSONObject(response.body().string());
                    obj.postValue(jsonObject);
                } catch (JSONException e) {
                    Log.d("JSON", "Error when obtaining user profile: " + e.toString());
                }
            }
        });
        return obj;
    }

    /**
     * Gets the top tracks for the user that this SpotifyAPICaller acts on.
     * Returns a JSON object containing 5 tracks.
     * Returns null if there was a problem getting the data.
     * Consult LogCat if null is ever returned.
     * @return The result of the API request for top tracks
     */
    public LiveData<JSONObject> getTopTracks() {
        final Request artistRequest = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/tracks?limit=5")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        cancelCall();
        mCall = mOkHttpClient.newCall(artistRequest);

        MutableLiveData<JSONObject> obj = new MutableLiveData<>();;
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("HTTP", "Failed to fetch data: " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    obj.postValue(jsonObject);
                } catch (JSONException e) {
                    Log.e("JSON", "Failed to obtain tracks: " + e.toString());
                }
            }
        });
        return obj;
    }

    /**
     * Gets the top artists for the user that this SpotifyAPICaller acts on.
     * Returns a JSON object containing 5 artists.
     * Returns null if there was a problem getting the data.
     * Consult LogCat if null is ever returned.
     * @return The result of the API request for top artists
     */
    public LiveData<JSONObject> getTopArtists() {
        final Request artistRequest = new Request.Builder()
                .url("https://api.spotify.com/v1/me/top/artists?limit=5")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        cancelCall();
        mCall = mOkHttpClient.newCall(artistRequest);

        MutableLiveData<JSONObject> obj = new MutableLiveData<>();
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("HTTP", "Failed to fetch data: " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    final JSONObject jsonObject = new JSONObject(response.body().string());
                    obj.postValue(jsonObject);
                } catch (JSONException e) {
                    Log.d("JSON", "Failed to obtain tracks: " + e.toString());
                }
            }
        });
        return obj;
    }

    /**
     * Gets recommendations from a list of song IDs.
     * songIDs is assumed NOT NULL, should have NO NULL ELEMENTS
     * and should contain AT LEAST 5 ELEMENTS with NO SPACES.
     * This method will return a LiveData of a JSON object.
     * If the LiveData has an unexpected value, consult LogCat.
     * @param songIDs A non-null array with at least 5 values and no gaps
     * @return A JSONObject live data representing the recommendations
     */
    public LiveData<JSONObject> getRecommendations(String[] songIDs) {
        // Trust me, String.format() won't work here.
        String url =
                "https://api.spotify.com/v1/recommendations?seed_tracks="
                        + songIDs[0]
                        + "%2C"
                        + songIDs[1]
                        + "%2C"
                        + songIDs[2]
                        + "%2C"
                        + songIDs[3]
                        + "%2C"
                        + songIDs[4];

        final Request recRequest = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        MutableLiveData<JSONObject> obj = new MutableLiveData<>();
        cancelCall();
        mCall = mOkHttpClient.newCall(recRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("HTTP", "Failed to fetch data: " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    obj.postValue(jsonObject);
                } catch (JSONException e) {
                    Log.d("JSON", "Failed to parse data: " + e.toString());
                }
            }
        });
        return obj;
    }

    private void cancelCall() {
        if (mCall != null) {
            mCall.cancel();
        } else {
            System.out.println("aaa");
        }
    }

}
