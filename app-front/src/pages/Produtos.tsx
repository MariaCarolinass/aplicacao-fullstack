import { useEffect, useState } from 'react';
import { PedidoService } from '../services/pedidoService';
import { ProdutoService } from '../services/produtoService';
import { ProdutoForm } from '../components/ProdutoForm';

export function Produtos() {
  const [produtos, setProdutos] = useState<any[]>([]);
  const [editando, setEditando] = useState<any | null>(null);
  const [pedidos, setPedidos] = useState<any[]>([]);

  const [resumoPedidos, setResumoPedidos] = useState<Record<number, { quantidadeProdutos: number; valorTotal: number }>>({});

  const carregarProdutos = () => {
    ProdutoService.listar().then(res => setProdutos(res.data));
  };

  const carregarPedidos = () => {
    PedidoService.listar().then(res => setPedidos(res.data));
  };

  const carregarResumoPedidos = async () => {
    const resumoTemp: Record<number, { quantidadeProdutos: number; valorTotal: number }> = {};

    await Promise.all(
      pedidos.map(async (pedido) => {
        try {
          const qtdRes = await ProdutoService.countProdutosPorPedido(pedido.id);
          const somaRes = await ProdutoService.sumPrecoProdutosPorPedido(pedido.id);

          resumoTemp[pedido.id] = {
            quantidadeProdutos: qtdRes.data ?? 0,
            valorTotal: somaRes.data ?? 0,
          };
        } catch (error) {
          resumoTemp[pedido.id] = {
            quantidadeProdutos: 0,
            valorTotal: 0,
          };
        }
      })
    );

    setResumoPedidos(resumoTemp);
  };

  useEffect(() => {
    carregarProdutos();
    carregarPedidos();
  }, []);

  useEffect(() => {
    if (pedidos.length > 0) {
      carregarResumoPedidos();
    }
  }, [pedidos]);

  const handleCreate = (dados: any) => {
    ProdutoService.criar(dados).then(carregarProdutos);
  };

  const handleUpdate = (dados: any) => {
    if (editando) {
      ProdutoService.atualizar(editando.id, dados).then(() => {
        setEditando(null);
        carregarProdutos();
      });
    }
  };

  const handleCancelEdit = () => {
    setEditando(null);
  };

  const handleDelete = (id: number) => {
    ProdutoService.deletar(id).then(carregarProdutos);
  };

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Produtos</h1>

      <ProdutoForm
        pedidos={pedidos}
        onSubmit={editando ? handleUpdate : handleCreate}
        onCancel={handleCancelEdit}
        initialData={editando ?? {}}
        isEditing={!!editando}
      />
      
      <table className="min-w-full bg-white border border-gray-300 mt-4">
        <thead>
          <tr className="bg-gray-100">
            <th className="py-2 px-4 border">ID</th>
            <th className="py-2 px-4 border">Código</th>
            <th className="py-2 px-4 border">Nome</th>
            <th className="py-2 px-4 border">Descrição</th>
            <th className="py-2 px-4 border">Preço</th>
            <th className="py-2 px-4 border">Desconto</th>
            <th className="py-2 px-4 border">Pedido ID</th>
            <th className="py-2 px-4 border">Ações</th>
          </tr>
        </thead>
        <tbody>
          {produtos.map(produto => (
            <tr key={produto.id}>
              <td className="py-2 px-4 border">{produto.id}</td>
              <td className="py-2 px-4 border">{produto.codigo}</td>
              <td className="py-2 px-4 border">{produto.nome}</td>
              <td className="py-2 px-4 border">{produto.descricao}</td>
              <td className="py-2 px-4 border">{produto.preco}</td>
              <td className="py-2 px-4 border">{produto.desconto}</td>
              <td className="py-2 px-4 border">{produto.pedido?.numeroPedido ?? 'N/A'}</td>
              <td className="py-2 px-4 border space-x-2">
                <button onClick={() => setEditando(produto)} className="text-blue-600">Editar</button>
                <button onClick={() => handleDelete(produto.id)} className="text-red-600">Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Tabela resumo dos pedidos */}
      <h2 className="text-lg font-bold mt-8 mb-2">Resumo por Pedido</h2>
      <table className="min-w-full bg-white border border-gray-300">
        <thead>
          <tr className="bg-gray-100">
            <th className="py-2 px-4 border">Pedido ID</th>
            <th className="py-2 px-4 border">Número do Pedido</th>
            <th className="py-2 px-4 border">Status do Pedido</th>
            <th className="py-2 px-4 border">Quantidade de Produtos</th>
            <th className="py-2 px-4 border">Valor Total dos Produtos</th>
          </tr>
        </thead>
        <tbody>
          {pedidos.map(pedido => (
            <tr key={pedido.id}>
              <td className="py-2 px-4 border">{pedido.id}</td>
              <td className="py-2 px-4 border">{pedido.numeroPedido}</td>
              <td className="py-2 px-4 border">{pedido.status}</td>
              <td className="py-2 px-4 border">{resumoPedidos[pedido.id]?.quantidadeProdutos ?? 0}</td>
              <td className="py-2 px-4 border">R$ {resumoPedidos[pedido.id]?.valorTotal.toFixed(2) ?? "0.00"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
