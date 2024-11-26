import { ChakraProvider, ChakraProviderProps } from "@chakra-ui/react";
import { QueryClientProvider } from "@tanstack/react-query";
import { queryClient } from "@/apis/instance";
import "swiper/css";
import "swiper/css/pagination";
import Routes from "@/routes";

function App() {
  const chakraProps: ChakraProviderProps = {};

  return (
    <ChakraProvider {...chakraProps} resetCSS={false}>
      <QueryClientProvider client={queryClient}>
        <Routes />
      </QueryClientProvider>
    </ChakraProvider>
  );
}
export default App;
