package com.example.sm_borrow;

import com.example.sm_borrow.data.LentItem;
import com.example.sm_borrow.data.LentItemDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // 빌려준 물건 목록 조회
    @GET("/api/lent-items")
    Call<List<LentItem>> getLentItems(@Query("userId") Long userId);

    // 빌려주는 물건 추가
    @POST("/api/lent-items")
    Call<LentItem> addLentItem(
            @Body ItemRequest itemRequest,
            @Query("userId") Long userId,
            @Query("specialNote") String specialNote
    );

    @POST("/api/lent-items/add")
    Call<LentItemDto> addLentItem(@Body LentItemDto lentItemDto);



    // 빌려준 물건 삭제
    @DELETE("/api/lent-items/{lentItemId}")
    Call<Void> deleteLentItem(@Path("lentItemId") Long lentItemId);
}
