- <a href="#fun0">홈화면
- <a href="#fun1">1.&nbsp;로그인</a>
 	- 1-1. 회원가입
- <a href="#fun2">2.&nbsp;영화 선택</a>
   	- 2-1. 영화 선택
- <a href="#fun3">3.&nbsp;영화 촬영지 선택</a>
  	- 3-1. 영화 촬영지 선택
- <a href="#fun4">4.&nbsp;경로 선택</a>
 	- 4-1. 경로선택
- <a href="#fun5">5.&nbsp;마이페이지</a>
	- 5-1. 경로 확인
   	- 5-2. 촬영지 주변 호텔 & 레스토랑 확인
  	- 5-3. 경로 삭제
- <a href="#fun6">6.&nbsp;내 정보 변경</a>
	- 6-1. 프로필 사진 변경
  	- 6-2. 닉네임 변경
  	- 6-3. 비밀번호 변경

### <a name="fun0">홈화면</a>
<img src="https://github.com/Psh230412/MovieTravelProject/assets/134483516/ed23b7be-cb0f-4e94-bfde-e9642ca454b4"/>

### <a name="fun1">영화 선택</a>
<img src="https://github.com/Psh230412/MovieTravelProject/assets/134483516/f896d3d3-c780-4721-a363-ea858a7b9903"/>

### <a name="fun2">영화 촬영지 선택</a>
<img src="https://github.com/Psh230412/MovieTravelProject/assets/134483516/59a05239-ebf3-48f9-ad63-27f231c9baa0"/>

### <a name="fun3">경로 선택</a>
<img src="https://github.com/Psh230412/MovieTravelProject/assets/134483516/7aaf0654-aba7-4c2b-a0d6-0db8c4a68915"/>

#### 경로는 어떻게 짜여지는가? </br>
첫 번째 경로: 선택환 촬영지 4개의 위도와 경도 정보를 이용하여 각 촬영지별 거리를 계산, 최단 거리를 제공 </br>
두 번째 경로, 세 번째 경로: 앞 번째 경로를 제외한 나머지 촬영지 중에서 랜덤으로 하나를 선택한 뒤, 최단 거리에 있는 촬영지 중 거리가 100m 이상인 촬영지 4개로 구성 
</br></br>

#### - 사용자에게 영화 선택 → 촬영지 선택 → 경로 선택의 순서대로 정보 제공 및 선택을 할 때, 정보를 어떻게 관리할 것인가? </br>
사용자가 각 단계에서 선택한 정보를 세션에 임시 저장. </br>
이를 통해 사용자는 이전 단계로 돌아가는 경우 선택한 정보를 유지할 수 있도록 하고, 페이지를 이탈했을 경우 불필요한 정보가 DB에 저장되는 것을 방지</br>
최종 경로까지 선택을 완료하면 세션에 저장하고 있었던 정보들을 DB에 저장</br>

</br></br>

#### <a>촬영지 주변 호텔 & 레스토랑 확인</a>
<img src="https://github.com/Psh230412/MovieTravelProject/assets/134483516/8c7b4976-2d84-4dba-85d3-f7ae4d7b6fd5"/>

### <a name="fun4">마이페이지</a>
<img src="https://github.com/Psh230412/MovieTravelProject/assets/134483516/32a21a8c-319f-4f8c-b39d-ceaee78a29f7"/>

### <a name="fun5">내 정보 변경</a>
<img src="https://github.com/Psh230412/0623Start/assets/134483516/14b420c1-7f55-447d-a054-d7e4d51c074a"/>
