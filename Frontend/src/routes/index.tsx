import { createBrowserRouter, Navigate, RouterProvider } from 'react-router-dom';

import BasicLayout from '@/components/layouts/BasicLayout';
import FreeLayout from '@/components/layouts/FreeLayout';
import ArtistDetails from '@/pages/ArtistDetails';
import Categories from '@/pages/Categories';
import ChatList from '@/pages/chats/ChatList';
import ChatRoom from '@/pages/chats/ChatRoom';
import Discover from '@/pages/Discover';
import Home from '@/pages/Home';
import Login from '@/pages/Login';
import My from '@/pages/My';
import MyFavorites from '@/pages/MyFavorites';
import MyGallery from '@/pages/MyGallery';
import MyOrders from '@/pages/MyOrders';
import MySales from '@/pages/MySales';
import ProductDetails from '@/pages/ProductDetails';
import ProductPosting from '@/pages/ProductPosting';
import SearchResults from '@/pages/SearchResults';
import Signup from '@/pages/Signup';
import { RouterPath } from './path';
import { useState, useEffect } from 'react';

const Routes = () => {
  const [isLiked, setIsLiked] = useState(false);
  const [likedList, setLikedList] = useState<number[]>([]);
  const [isFollowing, setIsFollowing] = useState(false);
  const [followingList, setFollowingList] = useState<number[]>([]);

  const handleIsLiked = (id: number) => {
    // 좋아요 상태 변경
    setIsLiked((prev) => !prev);

    // 좋아요 목록 업데이트
    setLikedList((prevList) => {
      const updatedList = prevList.includes(id)
        ? prevList.filter((likedId) => likedId !== id) // 이미 좋아요 목록에 있으면 제거
        : [...prevList, id]; // 없으면 추가

      // 로컬 스토리지에 저장
      localStorage.setItem('likedList', JSON.stringify(updatedList));
      return updatedList;
    });
  };

  const handleFollowToggle = (userId: number) => {
    setIsFollowing((prev) => !prev);

    setFollowingList((prevList) => {
      const updatedList = prevList.includes(userId)
        ? prevList.filter((id) => id !== userId) // 이미 팔로잉 중이면 제거
        : [...prevList, userId]; // 팔로잉 추가

      // 로컬 스토리지에 저장
      localStorage.setItem('followingList', JSON.stringify(updatedList));
      return updatedList;
    });
  };

  const router = createBrowserRouter([
    {
      path: RouterPath.root,
      element: <BasicLayout />,
      children: [
        {
          path: RouterPath.home,
          element: <Home />,
        },
        {
          path: RouterPath.discover,
          element: (
            <Discover handleFollowToggle={handleFollowToggle} followingList={followingList} />
          ),
        },
        {
          path: RouterPath.categories,
          element: <Categories />,
        },
        {
          path: RouterPath.products,

          children: [
            {
              path: RouterPath.posting,
              element: <ProductPosting />,
            },
          ],
        },
        {
          path: `${RouterPath.artists}/:artistId`,
          element: <ArtistDetails />,
        },
        {
          path: RouterPath.chats,

          children: [{ index: true, element: <ChatList /> }],
        },
        {
          path: RouterPath.my,
          children: [
            { index: true, element: <My /> },
            {
              path: RouterPath.orders,
              element: <MyOrders />,
            },
            {
              path: RouterPath.favorites,
              element: (
                <MyFavorites
                  handleIsLiked={handleIsLiked}
                  handleFollowToggle={handleFollowToggle}
                />
              ),
            },
            {
              path: RouterPath.sales,
              element: <MySales />,
            },
            {
              path: RouterPath.gallery,
              element: <MyGallery />,
            },
          ],
        },
        {
          path: RouterPath.notFound,
          element: <Navigate to={RouterPath.home} />,
        },
      ],
    },
    {
      path: RouterPath.root,
      element: <FreeLayout />,
      children: [
        {
          path: RouterPath.results,
          element: <SearchResults likedList={likedList} handleIsLiked={handleIsLiked} />,
        },
        {
          path: RouterPath.chats,
          children: [{ path: ':chatRoomId', element: <ChatRoom /> }],
        },
        {
          path: RouterPath.login,
          element: <Login />,
        },
        {
          path: RouterPath.signup,
          element: <Signup />,
        },
        {
          path: `${RouterPath.products}/:productId`,
          element: <ProductDetails />,
        },
      ],
    },
  ]);

  return <RouterProvider router={router} />;
};

export default Routes;
