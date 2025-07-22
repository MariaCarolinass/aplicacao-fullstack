import { useEffect, useState } from 'react';
import { ClienteService } from '../services/clienteService';
import { ClienteForm } from '../components/ClienteForm';

export function Clientes() {
  const [clientes, setClientes] = useState<any[]>([]);
  const [editando, setEditando] = useState<any | null>(null);

  const carregarClientes = () => {
    ClienteService.listar().then(res => setClientes(res.data));
  };

  useEffect(() => {
    carregarClientes();
  }, []);

  const handleCreate = (dados: any) => {
    ClienteService.criar(dados).then(carregarClientes);
  };

  const handleUpdate = (dados: any) => {
    if (editando) {
      ClienteService.atualizar(editando.id, dados).then(() => {
        setEditando(null);
        carregarClientes();
      });
    }
  };

  const handleCancelEdit = () => {
    setEditando(null);
  };

  const handleDelete = (id: number) => {
    ClienteService.deletar(id).then(carregarClientes);
  };
  
  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Clientes</h1>

      <ClienteForm
        onSubmit={editando ? handleUpdate : handleCreate}
        onCancel={handleCancelEdit}
        initialData={editando ?? {}}
        isEditing={!!editando}
      />
    
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white border border-gray-300">
          <thead>
            <tr className="bg-gray-100">
              <th className="py-2 px-4 border">ID</th>
              <th className="py-2 px-4 border">Nome</th>
              <th className="py-2 px-4 border">Email</th>
              <th className="py-2 px-4 border">Telefone</th>
              <th className="py-2 px-4 border">Endereço</th>
              <th className="py-2 px-4 border">Ações</th>
            </tr>
          </thead>
          <tbody>
            {clientes.map(cliente => (
              <tr key={cliente.id}>
                <td className="py-2 px-4 border">{cliente.id}</td>
                <td className="py-2 px-4 border">{cliente.nome}</td>
                <td className="py-2 px-4 border">{cliente.email}</td>
                <td className="py-2 px-4 border">{cliente.telefone}</td>
                <td className="py-2 px-4 border">{cliente.endereco}</td>
                <td className="py-2 px-4 border space-x-2">
                  <button onClick={() => setEditando(cliente)} className="text-blue-600">Editar</button>
                  <button onClick={() => handleDelete(cliente.id)} className="text-red-600">Excluir</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
  </div>
  );
}
