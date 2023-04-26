package com.example.languageapp.api;

import com.example.languageapp.api.models.TranslationRequest;
import com.example.languageapp.api.models.TranslationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TranslationService {
    @POST("/api/translate")
    Call<TranslationResponse> translate(@Body TranslationRequest request);
}
