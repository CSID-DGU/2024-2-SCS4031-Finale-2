import styled from '@emotion/styled';
import { useSearchParams } from 'react-router-dom';
import { PRODUCTS } from '@/constants/datas';
import SearchBar from '@/components/layouts/SearchBar';
import { useEffect, useState } from 'react';
interface Product {
  id: string;
  name: string;
  artist: string;
  price: number;
  thumbnailUrl: string;
  des?: string;
}
interface DiscoverProps {
  likedList: any[];
  handleIsLiked: (id: any) => void;
}

const SearchResults = ({ handleIsLiked, likedList }: DiscoverProps) => {
  const [searchParams] = useSearchParams();
  const searchQuery = searchParams.get('query') || '';
  const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);

  useEffect(() => {
    // 쿼리 스트링으로 받은 이름으로 PRODUCTS 필터링
    const filtered = PRODUCTS.filter((product) =>
      product.name.toLowerCase().includes(searchQuery.toLowerCase()),
    );
    setFilteredProducts(filtered);
  }, [searchQuery]);

  return (
    <PageContainer>
      <SearchBar includeBack={true} />
      <ContentSection>
        <ProductGrid>
          {filteredProducts.map((product) => (
            <ProductCard key={product.id}>
              <img src={product.thumbnailUrl} alt={product.name} />
              <div
                className="product-info"
                style={{ display: 'flex', justifyContent: 'space-between' }}
              >
                <div>
                  <p className="product-name">{product.name}</p>
                  <p className="product-artist">{product.artist}</p>
                  <p
                    className="product-artist"
                    style={{
                      margin: '2rem 0rem',
                    }}
                  >
                    {product.price.toLocaleString()}원
                  </p>
                  {product.des ? (
                    <>
                      <p className="product-artist">{product.des}</p>
                    </>
                  ) : (
                    ''
                  )}
                </div>
                <p
                  style={{
                    fontSize: '3rem',
                    color: likedList.includes(product.id) ? 'red' : 'black',
                    cursor: 'pointer',
                  }}
                  onClick={() => handleIsLiked(product.id)}
                >
                  {likedList.includes(product.id) ? '♥' : '♡'}
                </p>
              </div>
            </ProductCard>
          ))}
        </ProductGrid>
      </ContentSection>
    </PageContainer>
  );
};

export default SearchResults;

const PageContainer = styled.div`
  width: 100%;
  height: 100%;
`;

const ContentSection = styled.div`
  padding: 16px;
`;

const ResultFont = styled.div`
  font-size: var(--font-size-sm);
  font-weight: 600;
  margin-bottom: 16px;
`;

const ProductGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 16px;
`;

const ProductCard = styled.div`
  position: relative;
  border-radius: var(--border-radius);
  overflow: hidden;
  cursor: pointer;

  img {
    width: 100%;
    height: auto;
    display: block;
  }

  .product-info {
    width: 100%;
    padding: 8px;

    color: black;

    font-size: 2rem;

    .product-name {
      font-weight: 700;
    }

    .product-artist {
      font-weight: 400;
    }
  }
`;

const NoDataMessage = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  font-size: var(--font-size-md);
  color: var(--color-gray-dark);
`;
