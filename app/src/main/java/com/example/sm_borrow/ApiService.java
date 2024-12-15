package com.example.sm_borrow;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/items/save") // 서버 엔드포인트 설정
    Call<Void> saveItem(@Body ItemRequest itemRequest); // 서버에 데이터 전송
}
