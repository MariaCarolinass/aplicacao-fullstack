import { api } from './api';

export const ClienteService = {
  listar: () => api.get('/clientes'),
  criar: (dados: any) => api.post('/clientes', dados),
  atualizar: (id: number, dados: any) => api.put(`/clientes/${id}`, dados),
  deletar: (id: number) => api.delete(`/clientes/${id}`)
};