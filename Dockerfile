# Gradle과 OpenJDK가 포함된 베이스 이미지 사용
FROM gradle:8.0-jdk17 AS builder

# 필요한 패키지 설치
RUN apt-get update && apt-get install -y \
    unzip \
    wget \
    zlib1g \
    libncurses6 \
    libstdc++6 \
    git && \
    rm -rf /var/lib/apt/lists/*

# Android SDK 설치
RUN mkdir -p /opt/android-sdk && cd /opt/android-sdk && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-10406996_latest.zip -O android-sdk.zip && \
    unzip android-sdk.zip && rm android-sdk.zip

RUN mv /opt/android-sdk/cmdline-tools /opt/android-sdk/cmdline-tools-old && \
    mkdir -p /opt/android-sdk/cmdline-tools/latest && \
    cp -r /opt/android-sdk/cmdline-tools-old/* /opt/android-sdk/cmdline-tools/latest/

# 환경 변수 설정
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH=${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/tools:$PATH

# Android SDK 라이선스 동의 및 도구 설치
RUN yes | sdkmanager --licenses && \
    sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.3"

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 복사
COPY . /app

# Gradle 빌드를 통해 APK 생성
RUN gradle assembleRelease

# 최종 이미지
FROM alpine:latest
WORKDIR /app

# 빌드된 APK 파일 복사
COPY --from=builder /app/app/build/outputs/apk/release/*.apk /app/app-release.apk

CMD ["echo", "Build complete. APK is available in /app/app-release.apk"]
