import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import getUserInfo from '@/apis/users/getUserInfo';

const KakaoCallback = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const getKakaoToken = async () => {
      const urlParams = new URLSearchParams(window.location.search);
      const code = urlParams.get('code');

      if (code) {
        try {
          // 서버로 인가 코드를 전송해 액세스 토큰 요청
          const response = await axios.post('http://localhost:8080/oauth2/code/kakao', {
            code,
          });

          const { accessToken, refreshToken } = response.data;

          // 토큰 저장
          localStorage.setItem('accessToken', accessToken);
          localStorage.setItem('refreshToken', refreshToken);

          console.log('Access Token and Refresh Token saved.');

          // 사용자 정보 가져오기
          const userInfo = await getUserInfo();
          console.log('User Info:', userInfo);

          // 홈 화면으로 리다이렉트
          navigate('/');
        } catch (error) {
          console.error('Failed to fetch token or user info:', error);
        }
      }
    };

    getKakaoToken();
  }, [navigate]);

  return <div>Processing Kakao Login...</div>;
};

export default KakaoCallback;
