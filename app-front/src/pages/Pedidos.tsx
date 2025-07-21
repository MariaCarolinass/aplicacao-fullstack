import { useEffect, useState } from 'react';
import { ClienteService } from '../services/clienteService';
import { PedidoService } from '../services/pedidoService';
import { PedidoForm } from '../components/PedidoForm';

export function Pedidos() {
  const [pedidos, setPedidos] = useState<any[]>([]);
  const [editando, setEditando] = useState<any | null>(null);
  const [clientes, setClientes] = useState<any[]>([]);

  const carregarClientes = async () => {
    const res = await ClienteService.listar();
    setClientes(res.data);
    return res.data;
  };
  
  const carregarPedidos = async (clientesDisponiveis: any[]) => {
    const pedidosRes = await PedidoService.listar();
    const pedidosComClientes = pedidosRes.data.map((pedido: any) => {
      const clienteEncontrado = clientesDisponiveis.find(
        (c: any) => c.id === pedido.cliente?.id || c.id === pedido.clienteId
      );
      return {
        ...pedido,
        cliente: clienteEncontrado || pedido.cliente,
      };
    });
    setPedidos(pedidosComClientes);
  };

  useEffect(() => {
    const carregarDados = async () => {
      const clientesData = await carregarClientes();
      await carregarPedidos(clientesData);
    };

    carregarDados();
  }, []);

  const formatarDados = (dados: any) => {
    const dadosFormatados = {
      ...dados,
      cliente: { id: dados.clienteId },
    };
    delete dadosFormatados.clienteId;
    return dadosFormatados;
  };

  const handleCreate = async (dados: any) => {
    await PedidoService.criar(formatarDados(dados));
    const clientesData = await ClienteService.listar();
    setClientes(clientesData.data);
    await carregarPedidos(clientesData.data);
  };

  const handleUpdate = async (dados: any) => {
    if (editando) {
      await PedidoService.atualizar(editando.id, formatarDados(dados));
      setEditando(null);
      const clientesData = await ClienteService.listar();
      setClientes(clientesData.data);
      await carregarPedidos(clientesData.data);
    }
  };

  const handleCancelEdit = () => {
    setEditando(null);
  };

  const handleDelete = async (id: number) => {
    await PedidoService.deletar(id);
    const clientesData = await ClienteService.listar();
    setClientes(clientesData.data);
    await carregarPedidos(clientesData.data);
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
          {pedidos.map((pedido) => (
            <tr key={pedido.id}>
              <td className="py-2 px-4 border">{pedido.id}</td>
              <td className="py-2 px-4 border">{pedido.numeroPedido}</td>
              <td className="py-2 px-4 border">{pedido.dataPedido}</td>
              <td className="py-2 px-4 border">{pedido.observacoes}</td>
              <td className="py-2 px-4 border">{pedido.status}</td>
              <td className="py-2 px-4 border">{pedido.cliente?.nome ?? 'N/A'}</td>
              <td className="py-2 px-4 border space-x-2">
                <button
                  onClick={() => setEditando(pedido)}
                  className="text-blue-600"
                >
                  Editar
                </button>
                <button
                  onClick={() => handleDelete(pedido.id)}
                  className="text-red-600"
                >
                  Excluir
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
