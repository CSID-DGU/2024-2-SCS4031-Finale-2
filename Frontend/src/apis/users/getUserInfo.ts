import axios from 'axios';

const getUserInfo = async () => {
  const accessToken = localStorage.getItem('accessToken');

  try {
    const response = await axios.get('https://kapi.kakao.com/v2/user/me', {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

    console.log('User Info:', response.data);
  } catch (error) {
    console.error('Failed to fetch user info:', error);
  }
};

export default getUserInfo;
