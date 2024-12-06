import { Text } from '@chakra-ui/react';
import styled from '@emotion/styled';
import { useNavigate } from 'react-router-dom';

// import { getKakaoLgoin } from '@/apis/login/useGetKakaoLogin';

import IconButton from '@/components/common/IconButton';
import Header from '@/components/layouts/Header';
import { BACKGROUND_IMAGE_LIST } from '@/constants/login';
import { RouterPath } from '@/routes/path';
import { HEIGHTS } from '@/styles/constants';
import KakaoLoginButton from './components/KakaoLoginButton';
import fetchInstance from '@/apis/fetchInstance';
import { APIResponse, UserInfo } from '@/types';
import axios from 'axios';

const Login = () => {
  const navigate = useNavigate();

  // 랜덤 배경이미지
  const randomIndex = Math.floor(Math.random() * BACKGROUND_IMAGE_LIST.length);
  const backgroundImage = BACKGROUND_IMAGE_LIST[randomIndex].src;
  const backgroundImageCreator = BACKGROUND_IMAGE_LIST[randomIndex].creator;

  const handleLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/kakao';
    const searchParams = new URLSearchParams(window.location.search);
    const accessToken = searchParams.get('accessToken');
    const refreshToken = searchParams.get('refreshToken');

    if (accessToken && refreshToken) {
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      console.log('Tokens saved:', { accessToken, refreshToken });
    }

  };
  

  // const handleLogin = async () => {
  //   try {
  //     const response = await axios.get('http://localhost:8080/oauth2/login/kakao', {
  //       headers: {
  //         'Content-Type': 'application/json',
  //       },
  //     });
  //     const { accessToken, refreshToken } = response.data.data;
  //     localStorage.setItem('accessToken', accessToken);
  //     localStorage.setItem('refreshToken', refreshToken);
  
  //     console.log('Login successful:', accessToken);
  //   } catch (error) {
  //     console.error('Login failed:', error);
  //   }
  // };


  return (
    <Wrapper backgroundImage={backgroundImage}>
      <Header
        leftSideChildren={
          <IconButton icon="arrow-back" color="var(--color-white)" onClick={() => navigate(-1)} />
        }
        rightSideChildren={
          <IconButton
            icon="home"
            color="var(--color-white)"
            onClick={() => navigate(RouterPath.home)}
          />
        }
      />
      <ContentWrapper>
        <Text color="var(--color-white)" textAlign="center" fontSize="var(--font-size-md)">
          예술은 비율 속 무한을 드러내며, <br />
          그 무한은 숨겨진 가치를 밝혀줍니다. <br />
          예술 속에 숨겨진 가치를 찾아드립니다.
        </Text>
        <KakaoLoginButton onClick={handleLogin} />
      </ContentWrapper>
      <Text
        fontSize="var(--font-size-xs)"
        color="var(--color-gray-dk)"
        position="absolute"
        right="16px"
        bottom="16px"
      >
        사진: Unsplash의 {backgroundImageCreator}
      </Text>
    </Wrapper>
  );
};

export default Login;

const Wrapper = styled.div<{ backgroundImage: string }>`
  display: flex;
  flex-direction: column;
  flex: 1;
  overflow-y: auto;
  min-height: 100vh;
  background-image: ${({ backgroundImage }) => `url(${backgroundImage})`};
  background-size: cover;
  background-position: center;
  position: relative;
`;

const ContentWrapper = styled.div`
  margin: ${HEIGHTS.HEADER} 0 0 0;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 24px;

  .logo-svg {
    path {
      fill: white;
    }
    width: 118.5px;
    height: 37.5px;

    @media (min-width: 480px) {
      width: 158px;
      height: 50px;
    }
  }
`;
