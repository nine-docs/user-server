# 이미지 빌드 및 실행
프로젝트 root 위치에서 명령 실행
```bash
# 이미지 빌드
docker build -t (이미지이름) .

# 컨테이너 실행
docker run --name (원하는 컨테이너 이름) -d -p 80:8080 --env-file (.env 파일 위치) (이미지이름)
```

# Redis, Mysql 로컬 환경 전용 docker-compose

## Redis

- Redis는 공식적으로 Window를 지원하지 않는다
- Redis 공식 홈페이지에 가면 Window에서 Redis 를 다운로드 받을 수 있는 방법을 소개해주고 있으나, 이건 wsl 이용해서 linux를 깔고 그 위에 redis를
  설치하는 방식

### Window에서 redis-cli를 통해 컨테이너 이미지에 접근 방법

- https://github.com/microsoftarchive/redis
- 위의 링크를 통해 윈도우용 파일을 다운받아서 설치 경로에 가면 redis-cli가 있다. 이 파일을 통해 redis에 접근 가능
- 그러나 최신 버전은 없다

## MySQL

- docker-compose.yml에는 default로 환경변수를 적어놨음
    - 사용할 때 application.yml의 db이름이랑 환경변수 db이름만 맞춰주기, mount 경로확인 만 하면 될것 같습니다.
- 환경변수 변경해서 원하는대로 사용하기

### DBeaver에서 MySQL 연결 시 !AuthenticationProvider.BadAuthenticationPlugin! 에러

- MySql 8.0 이상은 caching_sha2_password를 기본 인증 플러그인으로 사용
- 근데 DBeaver에서는 이 플러그인을 지원 X
- 플러그인을 변경해줘야 함
- ALTER USER [사용자명] IDENTIFIED WITH mysql_native_password BY 'password';
- 위의 커맨드로 플러그인 변경 후 dbeaver에서 접속 가능
    - 근데 9.0 버전부터는 mysql_native_password 플러그인 제거되어 적용 불가
- 그냥 mysql workbench 사용하면 변경 없이 사용 가능