import { useEffect, useState } from 'react';
import { RelatorioService } from '../services/RelatorioService';

export function Relatorio() {
  const [resumo, setResumo] = useState<any>(null);
  const [pendentes, setPendentes] = useState<any[]>([]);
  const [clientesAtivos, setClientesAtivos] = useState<any[]>([]);

  useEffect(() => {
    RelatorioService.gerarResumo().then((res) => setResumo(res.data));
    RelatorioService.listarPendentes().then((res) => setPendentes(res.data));
    RelatorioService.clientesMaisAtivos(5).then((res) => setClientesAtivos(res.data));
  }, []);

  return (
    <div className="p-8">
      <h1 className="text-3xl font-bold text-center mb-8">Relatório de Vendas</h1>

      {resumo && (
        <div className="overflow-x-auto">
          <div className="mb-10 bg-white shadow-md rounded p-6">
            <h2 className="text-xl font-semibold mb-4">Resumo das Vendas</h2>
            <table className="table-auto w-full text-left">
              <thead>
                <tr>
                  <th className="px-4 py-2">Total de Pedidos</th>
                  <th className="px-4 py-2">Valor Total Faturado (R$)</th>
                  <th className="px-4 py-2">Quantidade de Produtos Vendidos</th>
                </tr>
              </thead>
              <tbody>
                <tr className="bg-gray-100">
                  <td className="px-4 py-2">{resumo.totalPedidos}</td>
                  <td className="px-4 py-2">
                    {resumo.valorTotalFaturado.toLocaleString('pt-BR', {
                      style: 'currency',
                      currency: 'BRL'
                    })}
                  </td>
                  <td className="px-4 py-2">{resumo.quantidadeProdutosVendidos}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      )}

      <div className="overflow-x-auto">
        <div className="mb-10 bg-white shadow-md rounded p-6">
          <h2 className="text-xl font-semibold mb-4">Pedidos Pendentes</h2>
          <table className="table-auto w-full text-left">
            <thead>
              <tr>
                <th className="px-4 py-2">ID</th>
                <th className="px-4 py-2">Cliente</th>
                <th className="px-4 py-2">Status</th>
              </tr>
            </thead>
            <tbody>
              {pendentes.map((pedido) => (
                <tr key={pedido.id} className="border-t">
                  <td className="px-4 py-2">{pedido.id}</td>
                  <td className="px-4 py-2">{pedido.cliente?.nome || 'N/A'}</td>
                  <td className="px-4 py-2">{pedido.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {pendentes.length === 0 && (
            <p className="text-gray-500 mt-2">Nenhum pedido pendente.</p>
          )}
        </div>

        <div className="bg-white shadow-md rounded p-6">
          <h2 className="text-xl font-semibold mb-4">Clientes Mais Ativos</h2>
          <table className="table-auto w-full text-left">
            <thead>
              <tr>
                <th className="px-4 py-2">ID</th>
                <th className="px-4 py-2">Nome</th>
                <th className="px-4 py-2">Total de Pedidos</th>
              </tr>
            </thead>
            <tbody>
              {clientesAtivos.map((cliente) => (
                <tr key={cliente.id} className="border-t">
                  <td className="px-4 py-2">{cliente.id}</td>
                  <td className="px-4 py-2">{cliente.nome}</td>
                  <td className="px-4 py-2">{cliente.pedidos?.length ?? 0}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {clientesAtivos.length === 0 && (
            <p className="text-gray-500 mt-2">Nenhum cliente ativo encontrado.</p>
          )}
        </div>
      </div>
    </div>
  );
}
