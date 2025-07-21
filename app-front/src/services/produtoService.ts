import { api } from './api';

export const ProdutoService = {
  listar: () => api.get('/produtos'),
  criar: (dados: any) => api.post('/produtos', dados),
  atualizar: (id: number, dados: any) => api.put(`/produtos/${id}`, dados),
  deletar: (id: number) => api.delete(`/produtos/${id}`),

  countProdutosPorPedido: (pedidoId: number) => api.get(`/produtos/count-produtos-por-pedido/${pedidoId}`),
  sumPrecoProdutosPorPedido: (pedidoId: number) => api.get(`/produtos/sum-preco-produtos-por-pedido/${pedidoId}`),
};
