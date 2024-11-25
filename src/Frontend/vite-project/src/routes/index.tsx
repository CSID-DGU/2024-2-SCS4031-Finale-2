import { createBrowserRouter, Navigate, RouterProvider } from 'react-router-dom';
import { Outlet } from 'react-router-dom';

import ArtistDetails from '@/pages/ArtistDetails';
import Categories from '@/pages/Categories';
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
import Search from '@/pages/Search';
import SearchResults from '@/pages/SearchResults';
import Signup from '@/pages/Signup';
import { ProtectedRoute } from './ProtectedRoute';
import { RouterPath } from './path';
const Routes = () => {
  return <RouterProvider router={router} />;
};
const router = createBrowserRouter([
  {
    path: RouterPath.root,
    element: <Outlet />, // 레이아웃 추후 추가하기
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
        path: RouterPath.search,
        element: <Search />,
        children: [
          {
            path: RouterPath.results,
            element: <SearchResults />,
          },
        ],
      },
      {
        path: `${RouterPath.products}/:productId`,
        element: <ProductDetails />,
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
    path: RouterPath.login,
    element: <Login />,
  },
  {
    path: RouterPath.signup,
    element: <Signup />,
  },
]);

export default Routes;