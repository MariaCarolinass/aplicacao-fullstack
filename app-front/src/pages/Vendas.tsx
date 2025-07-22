import { useEffect, useState } from 'react';
import { PedidoService } from '../services/pedidoService';
import { VendaService } from '../services/vendaService';
import { VendaForm } from '../components/VendaForm';

export function Vendas() {
  const [vendas, setVendas] = useState<any[]>([]);
  const [editando, setEditando] = useState<any | null>(null);
  const [pedidos, setPedidos] = useState<any[]>([]);
  
  const carregarVendas = () => {
    VendaService.listar().then(res => setVendas(res.data));
  };

  const carregarPedidos = () => {
    PedidoService.listar().then(res => setPedidos(res.data));
  };

  useEffect(() => {
    carregarVendas();
    carregarPedidos();
  }, []);

  const handleCreate = (dados: any) => {
    VendaService.criar(dados).then(carregarVendas);
  };

  const handleUpdate = (dados: any) => {
    if (editando) {
      VendaService.atualizar(editando.id, dados).then(() => {
        setEditando(null);
        carregarVendas();
      });
    }
  };

  const handleCancelEdit = () => {
    setEditando(null);
  };

  const handleDelete = (id: number) => {
    VendaService.deletar(id).then(carregarVendas);
  };

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Vendas</h1>

      <VendaForm
        pedidos={pedidos}
        onSubmit={editando ? handleUpdate : handleCreate}
        onCancel={handleCancelEdit}
        initialData={editando ?? {}}
        isEditing={!!editando}
      />

      <div className="overflow-x-auto">
        <table className="min-w-full bg-white border border-gray-300 mt-4">
          <thead>
            <tr className="bg-gray-100">
              <th className="py-2 px-4 border">ID</th>
              <th className="py-2 px-4 border">Data Venda</th>
              <th className="py-2 px-4 border">Data Cancelamento</th>
              <th className="py-2 px-4 border">Motivo Cancelamento</th>
              <th className="py-2 px-4 border">Observações</th>
              <th className="py-2 px-4 border">Pedido ID</th>
              <th className="py-2 px-4 border">Ações</th>
            </tr>
          </thead>
          <tbody>
            {vendas.map(venda => (
              <tr key={venda.id}>
                <td className="py-2 px-4 border">{venda.id}</td>
                <td className="py-2 px-4 border">{venda.dataVenda}</td>
                <td className="py-2 px-4 border">{venda.dataCancelamento}</td>
                <td className="py-2 px-4 border">{venda.motivoCancelamento}</td>
                <td className="py-2 px-4 border">{venda.observacoes}</td>
                <td className="py-2 px-4 border">{venda.pedido?.numeroPedido ?? 'N/A'}</td>
                <td className="py-2 px-4 border space-x-2">
                  <button onClick={() => setEditando(venda)} className="text-blue-600">Editar</button>
                  <button onClick={() => handleDelete(venda.id)} className="text-red-600">Excluir</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
