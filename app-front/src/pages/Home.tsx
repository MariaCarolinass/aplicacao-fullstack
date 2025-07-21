import { Link } from "react-router-dom";

export function Home() {
  return (
    <div className="p-8 text-center">
      <h1 className="text-3xl font-bold mb-6">Sistema de Gerenciamento de Vendas</h1>
      <p className="mb-8 text-gray-700">Selecione uma das seções abaixo:</p>

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-4 max-w-3xl mx-auto">
        <Link
          to="/clientes"
          className="bg-blue-500 text-white px-4 py-3 rounded hover:bg-blue-600"
        >
          Clientes
        </Link>
        <Link
          to="/pedidos"
          className="bg-green-500 text-white px-4 py-3 rounded hover:bg-green-600"
        >
          Pedidos
        </Link>
        <Link
          to="/produtos"
          className="bg-yellow-500 text-white px-4 py-3 rounded hover:bg-yellow-600"
        >
          Produtos
        </Link>
        <Link
          to="/vendas"
          className="bg-purple-500 text-white px-4 py-3 rounded hover:bg-purple-600"
        >
          Vendas
        </Link>
        <Link
          to="/relatorio"
          className="bg-red-500 text-white px-4 py-3 rounded hover:bg-red-600"
        >
          Relatório
        </Link>
      </div>
    </div>
  );
}
