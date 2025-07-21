import { api } from './api';

export const VendaService = {
  listar: () => api.get('/vendas'),
  criar: (dados: any) => api.post('/vendas', dados),
  atualizar: (id: number, dados: any) => api.put(`/vendas/${id}`, dados),
  deletar: (id: number) => api.delete(`/vendas/${id}`)
};
