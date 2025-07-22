import { Link } from 'react-router-dom';

export function Navbar() {
  return (
    <nav className="bg-blue-600 text-white p-4 flex flex-wrap justify-center gap-2 sm:gap-4 text-sm sm:text-base">
      <Link to="/" className="hover:underline">Página Inicial</Link>
      <Link to="/clientes" className="hover:underline">Clientes</Link>
      <Link to="/pedidos" className="hover:underline">Pedidos</Link>
      <Link to="/produtos" className="hover:underline">Produtos</Link>
      <Link to="/vendas" className="hover:underline">Vendas</Link>
      <Link to="/relatorio" className="hover:underline">Relatório</Link>
    </nav>
  );
}

