import styled from '@emotion/styled';

type StyledCheckboxProps = {
  isChecked: boolean;
  onChange: (checked: boolean) => void;
};

const Checkbox = ({ isChecked, onChange }: StyledCheckboxProps) => {
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onChange(e.target.checked);
  };

  return <StyledCheckbox type="checkbox" checked={isChecked} onChange={handleChange} />;
};

export default Checkbox;

const StyledCheckbox = styled.input`
  width: 14px;
  height: 14px;
  border: 1px solid #d6d6d6;
  border-radius: var(--border-radius);
  appearance: none; /* 기본 체크박스 스타일 제거 */
  outline: none;
  position: relative;

  &:checked {
    background-color: var(--color-gray-dk);
  }

  &:checked::after {
    content: '✓';
    position: absolute;
    top: 0;
    left: 2px;
    color: white;
    font-size: 14px;
  }
`;
