import { api } from './api';

export const RelatorioService = {
  gerarResumo: () => api.get('/relatorio'),
  listarPendentes: () => api.get('/relatorio/pedidos-pendentes'),
  clientesMaisAtivos: (topN = 5) => api.get(`/relatorio/clientes-mais-ativos?topN=${topN}`)
};
