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
import { ProtectedRoute } from './ProtectedRoute';

const Routes = () => {
  return <RouterProvider router={router} />;
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
        element: <Discover />,
      },
      {
        path: RouterPath.categories,
        element: <Categories />,
      },
      {
        path: RouterPath.products,
        element: <ProtectedRoute />,
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
        element: <ProtectedRoute />,
        children: [{ index: true, element: <ChatList /> }],
      },
      {
        path: RouterPath.my,
        element: <ProtectedRoute />,
        children: [
          { index: true, element: <My /> },
          {
            path: RouterPath.orders,
            element: <MyOrders />,
          },
          {
            path: RouterPath.favorites,
            element: <MyFavorites />,
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
        element: <SearchResults />,
      },
      {
        path: RouterPath.chats,
        element: <ProtectedRoute />,
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

export default Routes;
