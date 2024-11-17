import { ChakraProvider, ChakraProviderProps } from "@chakra-ui/react";

import Routes from "@/routes";

function App() {
  const chakraProps: ChakraProviderProps = {};
  return (
    <ChakraProvider {...chakraProps}>
      <Routes />
    </ChakraProvider>
  );
}

export default App;
