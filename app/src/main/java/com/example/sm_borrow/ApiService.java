package com.example.sm_borrow;

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






    // 빌려준 물건 삭제
    @DELETE("/api/lent-items/{lentItemId}")
    Call<Void> deleteLentItem(@Path("lentItemId") Long lentItemId);
}
