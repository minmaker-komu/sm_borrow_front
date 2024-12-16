package com.example.sm_borrow;

import com.example.sm_borrow.data.AlertDto;
import com.example.sm_borrow.data.BorrowedItemDto;
import com.example.sm_borrow.data.LentItem;
import com.example.sm_borrow.data.LentItemDto;
import com.example.sm_borrow.data.LoginRequest;
import com.example.sm_borrow.data.LoginResponse;
import com.example.sm_borrow.data.MemberRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // 회원가입
    @POST("/api/member/signup")
    Call<Void> signUp(@Body MemberRequest memberRequest);
    // 로그인
    @POST("/api/member/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // 빌린 물건 목록 조회
    @GET("/api/lent-items")
    Call<List<BorrowedItemDto>> getBorrowedItems(@Query("userId") Long userId);

    // 빌린 물건 추가
    @POST("/api/lent-items/add")
    Call<LentItemDto> addLentItem(@Body LentItemDto lentItemDto);



    // 빌려준 물품 조회
    @GET("/api/borrowed-items")
    Call<List<BorrowedItemDto>> getLentItems(@Query("userId") Long userId);

    // 빌려준 물건 추가
    @POST("/api/borrowed-items/add")
    Call<LentItemDto> addBorrowedItem(@Body LentItemDto lentItemDto);




    // 알림 조회
    @GET("api/alerts")
    Call<List<AlertDto>> getAlerts(@Query("userId") Long userId);

    @GET("/api/alerts/borrower")
    Call<List<AlertDto>> getBorrowerAlerts(@Query("memberId") Long memberId);

    @GET("/api/alerts/lender")
    Call<List<AlertDto>> getLenderAlerts(@Query("memberId") Long memberId);

    // 알림 상태 업데이트 (승인 또는 거절)
    @PATCH("api/alerts/{id}")
    Call<Void> updateAlertStatus(@Path("id") Long alertId, @Query("status") String status);



    // 빌려준 물건 삭제
    @DELETE("/api/lent-items/{lentItemId}")
    Call<Void> deleteLentItem(@Path("lentItemId") Long lentItemId);
}
