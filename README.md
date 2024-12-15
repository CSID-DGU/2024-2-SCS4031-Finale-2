## FINALE | 신진 예술가를 위한 예술품P2P플랫폼

## 개요

대학생과 신진 작가를 위한 미술 작품 중개 서비스를 개발하였습니다.😀

졸업 전시 후 버려지는 작품들에 대한 문제성을 인식하고,  
소비자의 합리적인 가격대의 미술품 및 인테리어의 수요를 충족하자는 아이디어에서 시작하여  
해당 서비스를 기획•개발하게 되었습니다.  


</br>


## 관련 리소스들
[프론트엔드 GitHub](https://github.com/CSID-DGU/2024-2-SCS4031-Finale-2/tree/main/Frontend)  
[백엔드 GitHub](https://github.com/CSID-DGU/2024-2-SCS4031-Finale-2/tree/main/Backend)  

[Swagger](http://golden-ratio.duckdns.org/swagger-ui/index.html#/)  
 

## 팀원

  | 성명 | 역할 |
  | :---: | :---: |
  | **양준모** | 멘토님 |
  | **정동현** |  서비스 총괄 & 팀장 |
  | **김한서** | Frontend |
  | **이하연** | Backend |
  | **허지혜** | Cloud Server & BE보조 |

## 기술 스택

### Frontend

![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![Vite](https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white)
![PNPM](https://img.shields.io/badge/pnpm-%234a4a4a.svg?style=for-the-badge&logo=pnpm&logoColor=f69220)
![Zustand](https://img.shields.io/badge/Zustand-black?style=for-the-badge&labelColor=white)
![TanStack Query](https://img.shields.io/badge/-TanStack%20Query-FF4154?style=for-the-badge&logo=react%20query&logoColor=white)
![Emotion](https://img.shields.io/badge/Emotion-black?style=for-the-badge&labelColor=white)
![Chakra](https://img.shields.io/badge/chakra-%234ED1C5.svg?style=for-the-badge&logo=chakraui&logoColor=white)
![Chakra](https://img.shields.io/badge/SwiperJS-0B66FF.svg?style=for-the-badge&logo=swiper&logoColor=white)
![React Hook Form](https://img.shields.io/badge/React%20Hook%20Form-%23EC5990.svg?style=for-the-badge&logo=reacthookform&logoColor=white)
![Amazon S3](https://img.shields.io/badge/Amazon%20S3-FF9900?style=for-the-badge&logo=amazons3&logoColor=white)

### Backend

![Java](https://img.shields.io/badge/java-17-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.8-6DB33F?style=for-the-badge&logo=springboot&logoColor=6DB33F)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)

### DevOps

![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white)
![Github Actions](https://img.shields.io/badge/GitHub_Actions-181717?style=for-the-badge&logo=GitHub&logoColor=white)

## 주요 개발 현황

### Frontend

#### 로그인 / 회원가입
카카오 로그인 경로로 리다이렉트하여 로그인 완료 시 액세스 토큰을 가져옵니다.  
회원가입이 아직 되지 않은 상태라면 회원가입 페이지로 리다이렉트받으며,  
일반 유저와 작가로 모드를 나누어 회원가입을 진행하며, 모드에 따라 다른 경로로 회원가입 API를 요청합니다.  
또한 UnivCert 및 국세청 사업자등록정보 API를 통해 학생 작가나 사업자 등록된 신진 작가를 검증했습니다.<br>

#### 피드 둘러보기
탠스택 쿼리의 useSuspenseInfiniteQuery를 호출하여 다양한 작품 이미지를 한눈에 볼 수 있게 화면을 제공합니다. <br>

#### 검색
사용자가 작품이나 작가, 찾고 싶은 작품의 키워드를 입력하면 검색 결과를 페칭합니다. <br>
최근 검색어, 검색 결과 정렬, 통합/작품/작가 검색 결과를 볼 수 있게 UI를 구성하여 UX를 고려하였습니다.  

#### 채팅
판매자(작가)와 수요자가 플랫폼 내에서 편리하게 연락을 주고받을 수 있도록 채팅을 구현했습니다.  
SockJS를 사용하여 웹소켓을 연결하고, STOMP 프로토콜 통신을 구현했습니다. <br>

#### 마이페이지
자신의 프로필을 확인 할 수 있습니다. 그리고 자신이 찜한 목록 및 팔로우 목록을 볼 수 있습니다.

### Backend
#### 회원가입
카카오 로그인을 통해 간편하게 회원등록을 할 수 있습니다.  

#### 작가 등록
작가로 등록하여 상품을 등록하고 판매하고 싶은 사용자는 사업자번호 또는 관련학과 전공자 인증을 통해 작가 프로필을 개선할 수 있습니다.  

#### 상품 관련 기능
작가는 상품 등록과 수정 삭제를 할 수 있습니다.  
상품 사진으로 10MB이하이고 확장자가 jpg,jpeg,png인 파일을 등록할 수 있습니다.  

#### 찜 기능
사용자는 마음에 드는 작품을 '찜' 기능을 사용하여 설정할 수 있습니다.  
사용자는 본인의 찜목록을 확인할 수 있습니다.  

#### 감상평 남기기 기능
사용자는 작품을 보고 감상평을 남길 수 있습니다.  

#### 채팅 기능
사용자는 구매를 원하는 작품의 작가에게 채팅으로 구매의사를 표현할 수 있습니다.  

#### 검색 기능
해시태그, 작가명, 상품명을 구분하여 검색할 수 있습니다.  

#### 인프라 구축
<img src="image.png" alt="image" width="700">



### Backend

**백엔드 역할 분담**

1. 검색 - 이하연
2. 로그인 및 회원 가입 + 작가 + 팔로우 - 이하연
3. 채팅 + 파일 - 허지혜
4. 상품(피드), 좋아요, 감상평 - 정동현
5. 인프라(ci/cd, https, 모니터링) - 이하연

**CI/CD 및 인프라 구축**

[https://camo.githubusercontent.com/9fad39067426911983c24c3fc003ceada4d72b9c160b799eb65c685661505444/68747470733a2f2f76656c6f672e76656c63646e2e636f6d2f696d616765732f6879756e6e2f706f73742f39383663366166392d623639342d343463322d613535342d6464353165303931666465302f696d6167652e706e67](https://camo.githubusercontent.com/9fad39067426911983c24c3fc003ceada4d72b9c160b799eb65c685661505444/68747470733a2f2f76656c6f672e76656c63646e2e636f6d2f696d616765732f6879756e6e2f706f73742f39383663366166392d623639342d343463322d613535342d6464353165303931666465302f696d6167652e706e67)

**CI/CD 과정 - GitHub Actions를 활용한 배포 프로세스**

1. **GitHub Actions 트리거**
    - Master 브랜치에 코드가 푸시되면 GitHub Action이 자동으로 동작합니다.
2. **도커 이미지 생성 및 업로드**
    - 프로젝트를 빌드하고, 도커 이미지를 생성한 후 도커 허브에 이미지를 업로드합니다.
3. **배포 스크립트 실행**
    - EC2-1 서버에 접속하여 배포 스크립트(`deploy.sh`)를 실행합니다.

**(deploy.sh 스크립트 동작)**

1. **백엔드 서버의 포트 검사**
    - EC2-1, EC2-2에서 현재 동작 중인 백엔드 서버(도커 컨테이너)가 사용 중인 포트(8080 또는 8081)를 검사합니다.
    - 사용하지 않는 포트(8081 또는 8080)를 확인합니다.
2. **새 서버 컨테이너 배포**
    - 도커 허브에서 최신 이미지를 PULL하여, EC2-1, EC2-2의 사용 가능한 포트에 새 백엔드 서버(도커 컨테이너)를 실행합니다.
3. **헬스 체크 수행**
    - 새로운 서버의 정상 동작을 확인하기 위해 `/actuator/health` 엔드포인트로 헬스 체크를 수행합니다.
    - 이 과정에서 DB 연결 상태도 함께 확인합니다.
4. **트래픽 분산 대상 변경 (Blue-Green 배포)**
    - 헬스 체크가 성공하면 NGINX 설정을 업데이트하여 트래픽을 새 서버로 분산합니다.
    - BLUE-GREEN 방식으로 무중단 배포를 수행합니다.
5. **NGINX 리로드**
    - 변경된 NGINX 설정을 적용하기 위해 NGINX를 리로드합니다.
6. **기존 서버 종료 및 삭제**
    - 기존에 EC2-1, EC2-2에서 실행 중이던 컨테이너를 종료하고 삭제하여 이전 서버를 정리합니다.

**인프라 구성 - EC2 서버 구성 및 모니터링**

**EC2 - 1**

- **백엔드 서버1** (도커 컨테이너)
- **Nginx** - 80 포트로 들어오는 요청을 백엔드 서버 1, 2에 대해 로드밸런싱
- **MySQL** - 백엔드 서버 1, 2가 참조하는 DB
- **Prometheus** - 백엔드 서버 1, 2의 메트릭 수집
- **Grafana** - Prometheus가 수집한 메트릭 시각화 (URL: [http://golden-ratio.duckdns.org:3000/](http://golden-ratio.duckdns.org:3000/)) (admin/admin)
- **Dozzle** - EC2-1, EC2-2의 도커 컨테이너 로그 시각화 (URL: [http://golden-ratio.duckdns.org:7070/](http://golden-ratio.duckdns.org:7070/))

**EC2 - 2**

- **백엔드 서버2** (도커 컨테이너)
- **Dozzle** - (Agent) EC2-2의 도커 컨테이너 로그를 EC2-1의 Dozzle에게 전달

**서버 1개일 때 vs 2개일 때(LB 적용) TPS 간단 비교 (Swagger GET 기준)**

- TPS 테스트 결과: 두 개의 서버로 로드밸런싱 적용 시 성능이 향상됨

**EC2 인스턴스 생성 - Terraform 이용**

1. **Terraform을 이용한 EC2 인스턴스 생성**
    - Terraform 스크립트를 사용해 AWS에서 EC2 인스턴스를 생성하고, 관련 리소스(보안 그룹, VPC, 서브넷, 키 페어 등)를 설정하였습니다.

