import styled from '@emotion/styled';

type ProductItemProps = {
  id: any;
  author: string;
  title: string;
  price: number;
  heart?: boolean;
  src?: any;
  alt?: string;
  isLiked: boolean;
  onClick: any;
};

const ProductItem = ({ id, author, title, price, src, alt }: ProductItemProps) => {
  return (
    <Wrapper>
      <img src={src} alt={alt} id={id}></img>
      <MidWrapper>
        <DescriptionWrapper style={{ fontWeight: '600' }}>{author}</DescriptionWrapper>
        <DescriptionWrapper>{title}</DescriptionWrapper>
        <DescriptionWrapper style={{ fontWeight: '600' }}>{price}원</DescriptionWrapper>
      </MidWrapper>
    </Wrapper>
  );
};

export default ProductItem;

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  max-width: 100px;
  background-color: var(--color-white);
`;

const MidWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
  width: 100%;
  height: 60px;
  margin: 0.8rem 0;
`;

const DescriptionWrapper = styled.p`
  font-size: var(--font-size-sm);
`;
