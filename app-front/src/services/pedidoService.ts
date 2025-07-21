import { api } from './api';

export const PedidoService = {
  listar: () => api.get('/pedidos'),
  criar: (dados: any) => api.post('/pedidos', dados),
  atualizar: (id: number, dados: any) => api.put(`/pedidos/${id}`, dados),
  deletar: (id: number) => api.delete(`/pedidos/${id}`)
};
