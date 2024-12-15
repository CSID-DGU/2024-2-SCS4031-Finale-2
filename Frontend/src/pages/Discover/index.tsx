import styled from '@emotion/styled';

import Masonry from 'react-masonry-css';

import SearchModal from '@/components/common/SearchModal';
import SearchBar from '@/components/layouts/SearchBar';
import { HEIGHTS } from '@/styles/constants';
import { PRODUCTS, CHILDREN_ARTIST } from '@/constants/datas';
import { useNavigate } from 'react-router-dom';
import { RouterPath } from '@/routes/path';

interface DiscoverProps {
  followingList: any[];
  handleFollowToggle: (id: any) => void;
}

const Discover = ({ followingList, handleFollowToggle }: DiscoverProps) => (
  <Wrapper>
    <SearchBar includeBack={false} includeFavorite={true} />
    <SearchModal />
    <ContentWrapper>
      <Feed followingList={followingList} handleFollowToggle={handleFollowToggle} />
    </ContentWrapper>
  </Wrapper>
);

const Feed = ({ followingList, handleFollowToggle }: DiscoverProps) => {
  const navigate = useNavigate();

  return (
    <FeedWrapper>
      {/* PRODUCTS 섹션 */}
      <Section className="top">
        <ImageGrid
          className="masonry-grid-1"
          breakpointCols={{ default: 3, 600: 3, 480: 2 }}
          columnClassName="masonry-grid-column"
        >
          {PRODUCTS.map((item) => (
            <ImageItem
              key={item.id}
              onClick={() => navigate(`/${RouterPath.results}?query=${item.name.trim()}`)}
            >
              <img src={item.thumbnailUrl} alt={item.name} />
              <div className="image-item-overlay">
                <p className="image-item-name">{item.name}</p>
                <p className="image-item-artist">{item.artist}</p>
              </div>
            </ImageItem>
          ))}
        </ImageGrid>
      </Section>

      {/* CHILDREN_ARTIST 섹션 */}
      <Section className="bottom">
        <ImageGrid
          className="masonry-grid-2"
          breakpointCols={{ default: 2, 600: 2, 480: 2 }}
          columnClassName="masonry-grid-column"
        >
          {CHILDREN_ARTIST.map((item) => (
            <ArtistItem key={item.id}>
              <div
                style={{
                  display: 'flex',
                  justifyContent: 'space-between',
                }}
              >
                <img src={item.src} alt={item.nickname} width={50} />
                <div
                  style={{
                    display: 'flex',
                    justifyContent: 'center',
                    flexDirection: 'column',
                    marginLeft: '2rem',
                  }}
                >
                  <p className="image-item-name" style={{ fontSize: '1.5rem', fontWeight: 800 }}>
                    {item.nickname}
                  </p>
                  <p className="image-item-artist" style={{ fontSize: '1.2rem', fontWeight: 300 }}>
                    팔로워 수 {item.totalFollowers}
                  </p>
                </div>
                <div style={{ position: 'relative', bottom: '-1rem', left: '5rem' }}>
                  <FollowButton
                    isFollowing={followingList.includes(item.id)}
                    onClick={() => handleFollowToggle(item.id)}
                  />
                </div>
              </div>
            </ArtistItem>
          ))}
        </ImageGrid>
      </Section>
    </FeedWrapper>
  );
};

const FollowButton = ({ isFollowing, onClick }: { isFollowing: boolean; onClick: () => void }) => {
  const buttonStyle = {
    border: 'solid 1px',
    padding: '0.5rem 1rem',
    borderRadius: '2rem',
    backgroundColor: isFollowing ? 'rgba(81, 79, 79, 0.259)' : 'white',
    color: isFollowing ? 'white' : 'black',
    cursor: 'pointer',
  };

  return (
    <button style={buttonStyle} onClick={onClick}>
      {isFollowing ? '팔로잉' : '팔로우'}
    </button>
  );
};

const FeedWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

const Section = styled.div`
  max-height: 700px; /* 원하는 높이 제한 */
  overflow-y: auto; /* 스크롤 활성화 */
  padding: 8px;

  /* 스크롤바 스타일 */
  scrollbar-width: none; /* Firefox */
  -ms-overflow-style: none; /* IE, Edge */

  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari */
  }
`;

const ArtistItem = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 2rem;
  background-color: rgba(0, 0, 0, 0.02);

  img {
    border-radius: 30%;
    width: 6rem;
    height: 6rem;
  }
`;
const Wrapper = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  margin: ${HEIGHTS.HEADER} 0 ${HEIGHTS.BOTTOM} 0;
`;

const ContentWrapper = styled.div`
  padding: 4px;
  overflow-y: auto;
  flex: 1;
`;

const ImageGrid = styled(Masonry)`
  display: flex;
  margin-left: -8px; /* gutter size */
  width: auto;

  & > div {
    padding-left: 8px; /* gutter size */
    background-clip: padding-box;
  }

  .masonry-grid-column {
    display: flex;
    flex-direction: column;
    gap: 8px;
  }
`;

const ImageItem = styled.div`
  border-radius: var(--border-radius);
  overflow: hidden;
  position: relative;

  img {
    width: 100%;
    height: auto;
    display: block;
    transition: filter 0.2s ease;
  }

  &:hover img {
    filter: brightness(70%);
  }

  .image-item-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    padding: 16px;
    display: inline-flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 8px;
    color: var(--color-white);
    background: rgba(0, 0, 0, 0.5);
    opacity: 0; /* 초기에는 투명 */
    transition: opacity 0.2s ease;
  }

  .image-item-name {
    font-size: var(--font-size-md);
    font-weight: 700;
    white-space: nowrap; // 줄바꿈 방지
    overflow: hidden; // 넘어가면 숨겨줌
    text-overflow: ellipsis; // 말줄임표
  }

  .image-item-artist {
    font-size: var(--font-size-sm);
    font-weight: 500;
  }

  &:hover .image-item-overlay {
    opacity: 1; /* 호버 시 작품 정보 보임 */
  }
`;

export default Discover;
