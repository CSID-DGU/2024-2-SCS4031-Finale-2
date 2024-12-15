import styled from '@emotion/styled';
import { HEIGHTS } from '@/styles/constants';
import UserMenuSection from './components/MenuSection/UserMenuSection';
import UserProfileBox from './components/UserProfileBox';

const userInfo = {
  username: '정동현',
  hashTags: '서양화',
  userImageUrl:
    'https://i.namu.wiki/i/Bge3xnYd4kRe_IKbm2uqxlhQJij2SngwNssjpjaOyOqoRhQlNwLrR2ZiK-JWJ2b99RGcSxDaZ2UCI7fiv4IDDQ.webp',
};

const My = () => {
  return <MyContent />;
};

const MyContent = () => {
  return (
    <Wrapper>
      <ProfileSection>
        <UserProfileBox
          userImageUrl={userInfo.userImageUrl}
          username={userInfo.username}
          hashTags={userInfo.hashTags}
        />
      </ProfileSection>
      <UserMenuSection />
    </Wrapper>
  );
};

export default My;

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  padding-bottom: ${HEIGHTS.BOTTOM};
`;

const ProfileSection = styled.div`
  background: var(--color-black);
  padding: 16px;
`;
