import { useEffect, useState } from 'react';
import { ClienteService } from '../services/clienteService';
import { PedidoService } from '../services/pedidoService';
import { PedidoForm } from '../components/PedidoForm';

export function Pedidos() {
  const [pedidos, setPedidos] = useState<any[]>([]);
  const [editando, setEditando] = useState<any | null>(null);
  const [clientes, setClientes] = useState<any[]>([]);

  const carregarPedidos = () => {
    PedidoService.listar().then(res => setPedidos(res.data));
  };

  const carregarClientes = () => {
    ClienteService.listar().then(res => setClientes(res.data));
  };

  useEffect(() => {
    carregarPedidos();
    carregarClientes();
  }, []);

  const formatarDados = (dados: any) => {
    const dadosFormatados = {
      ...dados,
      cliente: { id: dados.clienteId },
    };
    delete dadosFormatados.clienteId;
    return dadosFormatados;
  };

  const handleCreate = (dados: any) => {
    PedidoService.criar(formatarDados(dados)).then(carregarPedidos);
  };

  const handleUpdate = (dados: any) => {
    if (editando) {
      PedidoService.atualizar(editando.id, formatarDados(dados)).then(() => {
        setEditando(null);
        carregarPedidos();
      });
    }
  };

  const handleCancelEdit = () => {
    setEditando(null);
  };

  const handleDelete = (id: number) => {
    PedidoService.deletar(id).then(carregarPedidos);
  };

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Pedidos</h1>

      <PedidoForm
        clientes={clientes}
        onSubmit={editando ? handleUpdate : handleCreate}
        onCancel={handleCancelEdit}
        initialData={
          editando
            ? { ...editando, clienteId: editando.cliente?.id }
            : {}
        }
        isEditing={!!editando}
      />

      <table className="min-w-full bg-white border border-gray-300 mt-4">
        <thead>
          <tr className="bg-gray-100">
            <th className="py-2 px-4 border">ID</th>
            <th className="py-2 px-4 border">Número do Pedido</th>
            <th className="py-2 px-4 border">Data</th>
            <th className="py-2 px-4 border">Observações</th>
            <th className="py-2 px-4 border">Status</th>
            <th className="py-2 px-4 border">Cliente</th>
            <th className="py-2 px-4 border">Ações</th>
          </tr>
        </thead>
        <tbody>
          {pedidos.map(pedido => (
            <tr key={pedido.id}>
              <td className="py-2 px-4 border">{pedido.id}</td>
              <td className="py-2 px-4 border">{pedido.numeroPedido}</td>
              <td className="py-2 px-4 border">{pedido.dataPedido}</td>
              <td className="py-2 px-4 border">{pedido.observacoes}</td>
              <td className="py-2 px-4 border">{pedido.status}</td>
              <td className="py-2 px-4 border">{pedido.cliente?.nome ?? 'N/A'}</td>
              <td className="py-2 px-4 border space-x-2">
                <button onClick={() => setEditando(pedido)} className="text-blue-600">Editar</button>
                <button onClick={() => handleDelete(pedido.id)} className="text-red-600">Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
