import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Navbar } from './components/Navbar';
import { Footer } from './components/Footer';
import { Clientes } from './pages/Clientes';
import { Pedidos } from './pages/Pedidos';
import { Produtos } from './pages/Produtos';
import { Vendas } from './pages/Vendas';
import { Relatorio } from './pages/Relatorio';

function App() {
  return (
    <BrowserRouter>
      <div className="flex flex-col min-h-screen">  
        <Navbar />
        <main className="flex-grow">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/clientes" element={<Clientes />} />
            <Route path="/pedidos" element={<Pedidos />} />
            <Route path="/produtos" element={<Produtos />} />
            <Route path="/vendas" element={<Vendas />} />
            <Route path="/relatorio" element={<Relatorio />} />
          </Routes>
        </main>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
