import styled from '@emotion/styled';
import { useEffect, useState } from 'react';

import CategoryTabBar from '@/components/common/CategoryTabBar';

import ProductItem from '@/components/common/ProductItem';
import * as G from '@/styles/globalStyles';
import { CHILDREN_ARTIST, PRODUCTS } from '@/constants/datas';

interface FavProps {
  handleIsLiked: any;
  handleFollowToggle: (id: any) => void;
}

const MyFavorites = ({ handleIsLiked, handleFollowToggle }: FavProps) => {
  const categoryList = ['작품', '작가'];
  const [selectedTab, setSelectedTab] = useState('작품');
  const likedLists = JSON.parse(localStorage.getItem('likedList') || '[]');
  const followingList = JSON.parse(localStorage.getItem('followingList') || '[]');

  const handleTabClick = (tab: string) => {
    setSelectedTab(tab);
  };

  return (
    <>
      <CategoryTabBar tabList={categoryList} tabClick={handleTabClick} tabState={selectedTab} />
      {selectedTab === '작품' ? (
        <Wrapper>
          {likedLists?.length === 0 ? (
            <p>찜한 작품이 없습니다.</p>
          ) : (
            <G.Grid col={2}>
              {PRODUCTS.filter(
                (product) => likedLists?.includes(product.id), // likedList에 해당 product.id가 있는지 확인
              ).map((filteredProduct) => (
                <ProductItem
                  key={filteredProduct.id}
                  id={filteredProduct.id}
                  title={filteredProduct.name}
                  author={filteredProduct.artist}
                  price={filteredProduct.price}
                  src={filteredProduct.thumbnailUrl}
                />
              ))}
            </G.Grid>
          )}
        </Wrapper>
      ) : (
        <Wrapper>
          {followingList === 0 ? (
            <p>팔로우한 작가가 없습니다.</p>
          ) : (
            <G.Grid col={2}>
              {CHILDREN_ARTIST.filter(
                (product) => followingList?.includes(product.id), // likedList에 해당 product.id가 있는지 확인
              ).map((filteredProduct) => (
                <ArtistItem
                  id={filteredProduct.id}
                  key={filteredProduct.id}
                  author={filteredProduct.nickname}
                  follower={filteredProduct.totalFollowers}
                  src={filteredProduct.src}
                />
              ))}
            </G.Grid>
          )}
        </Wrapper>
      )}
    </>
  );
};

export default MyFavorites;

const Wrapper = styled.div`
  margin-top: 41px;
`;

type ArtistItemProps = {
  id: any;
  author: string;
  title: string;
  price: number;
  heart?: boolean;
  src?: any;
  alt?: string;
  isLiked: boolean;
  onClick: any;
  follower: number;
  isFollow: boolean;
};

const ArtistItem = ({ id, author, src, alt, follower }: ArtistItemProps) => {
  return (
    <AWrapper>
      <img src={src} alt={alt} id={id} style={{ borderRadius: '2rem' }}></img>
      <MidAWrapper>
        <DescriptionAWrapper style={{ fontWeight: '800', fontSize: '2rem' }}>
          {author}
        </DescriptionAWrapper>
        <DescriptionAWrapper style={{ fontWeight: '300' }}>
          팔로워 수 {follower}
        </DescriptionAWrapper>
      </MidAWrapper>
    </AWrapper>
  );
};

const AWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  max-width: 100px;

  background-color: var(--color-white);
`;

const MidAWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 60px;
  margin: 0.8rem 0;
`;

const DescriptionAWrapper = styled.p`
  font-size: var(--font-size-sm);
`;
