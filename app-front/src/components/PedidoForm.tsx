import { useEffect, useState } from "react";

interface PedidoFormProps {
  onSubmit: (data: any) => void;
  onCancel?: () => void;
  initialData?: any;
  isEditing?: boolean;
  clientes: any[];
}

const statusOptions = [
  { label: "Em Andamento", value: "EM_ANDAMENTO" },
  { label: "Finalizado", value: "FINALIZADO" },
  { label: "Cancelado", value: "CANCELADO" },
];

export function PedidoForm({
  onSubmit,
  onCancel,
  initialData = {},
  isEditing = false,
  clientes = [],
}: PedidoFormProps) {
  const [numeroPedido, setNumeroPedido] = useState("");
  const [dataPedido, setDataPedido] = useState("");
  const [observacoes, setObservacoes] = useState("");
  const [status, setStatus] = useState("EM_ANDAMENTO");
  const [clienteId, setClienteId] = useState("");

  useEffect(() => {
    setNumeroPedido(initialData.numeroPedido || "");
    setDataPedido(initialData.dataPedido || "");
    setObservacoes(initialData.observacoes || "");
    setStatus(initialData.status || "EM_ANDAMENTO");
    setClienteId(initialData.clienteId ? String(initialData.clienteId) : "");
  }, [initialData]);

  const limparFormulario = () => {
    setNumeroPedido("");
    setDataPedido("");
    setObservacoes("");
    setStatus("EM_ANDAMENTO");
    setClienteId("");
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({
      numeroPedido: parseInt(numeroPedido),
      dataPedido,
      observacoes,
      status,
      clienteId: Number(clienteId),
    });
    if (!isEditing) limparFormulario();
  };

  const handleCancelar = () => {
    limparFormulario();
    onCancel && onCancel();
  };

  return (
    <form
      onSubmit={handleSubmit}
      className={`mb-4 border rounded p-4 shadow-sm ${
        isEditing ? "bg-yellow-50" : "bg-white"
      }`}
    >
      <h2 className="text-xl font-bold mb-2 text-gray-700">
        {isEditing ? "Editando Pedido" : "Cadastrar Novo Pedido"}
      </h2>

      <div className="mb-2">
        <label className="block font-medium">Número do Pedido *</label>
        <input
          type="number"
          value={numeroPedido}
          onChange={(e) => setNumeroPedido(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Data do Pedido *</label>
        <input
          type="date"
          value={dataPedido}
          onChange={(e) => setDataPedido(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Observações</label>
        <textarea
          value={observacoes}
          onChange={(e) => setObservacoes(e.target.value)}
          className="border px-2 py-1 w-full"
        />
      </div>

      <div className="mb-4">
        <label className="block font-medium mb-1">Status</label>
        {statusOptions.map((option) => (
          <label key={option.value} className="mr-4">
            <input
              type="radio"
              name="status"
              value={option.value}
              checked={status === option.value}
              onChange={() => setStatus(option.value)}
              className="mr-1"
            />
            {option.label}
          </label>
        ))}
      </div>

      <div className="mb-4">
        <label className="block font-medium">Cliente</label>
        <select
          value={clienteId}
          onChange={(e) => setClienteId(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        >
          <option value="">-- Selecione um cliente --</option>
          {clientes.map((cliente) => (
            <option key={cliente.id} value={cliente.id}>
              {cliente.nome} (ID: {cliente.id})
            </option>
          ))}
        </select>
      </div>

      <div className="flex gap-2">
        <button
          type="submit"
          className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition"
        >
          {isEditing ? "Atualizar" : "Cadastrar"}
        </button>

        {isEditing && (
          <button
            type="button"
            onClick={handleCancelar}
            className="bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400 transition"
          >
            Cancelar
          </button>
        )}
      </div>
    </form>
  );
}
