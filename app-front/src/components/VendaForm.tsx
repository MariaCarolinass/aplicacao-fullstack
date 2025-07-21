import { useEffect, useState } from "react";

interface VendaFormProps {
  onSubmit: (data: any) => void;
  onCancel?: () => void;
  initialData?: any;
  isEditing?: boolean;
  pedidos: any[];
}

export function VendaForm({
  onSubmit, 
  onCancel,
  initialData = {}, 
  isEditing = false,
  pedidos = [],
}: VendaFormProps) {
  const [dataVenda, setDataVenda] = useState(initialData.dataVenda || '');
  const [dataCancelamento, setDataCancelamento] = useState(initialData.dataCancelamento || '');
  const [motivoCancelamento, setMotivoCancelamento] = useState(initialData.motivoCancelamento || '');
  const [observacoes, setObservacoes] = useState(initialData.observacoes || '');
  const [pedidoId, setPedidoId] = useState("");

  useEffect(() => {
    setDataVenda(initialData.dataVenda || "");
    setDataCancelamento(initialData.dataCancelamento || "");
    setMotivoCancelamento(initialData.motivoCancelamento || "");
    setObservacoes(initialData.observacoes || "");
    setPedidoId(initialData.pedidoId ? String(initialData.pedidoId) : "");
  }, [initialData]);

  const limparFormulario = () => {
    setDataVenda("");
    setDataCancelamento("");
    setMotivoCancelamento("");
    setObservacoes("");
    setPedidoId("");
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ 
      dataVenda, 
      dataCancelamento, 
      motivoCancelamento, 
      observacoes, 
      pedido:  {
        id: Number(pedidoId),
      },
    });
    if (!isEditing) {
      limparFormulario();
    }
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
        {isEditing ? "Editando Venda" : "Cadastrar Nova Venda"}
      </h2>

      <div className="mb-2">
        <label className="block font-medium">Data da Venda *</label>
        <input
          type="date"
          value={dataVenda}
          onChange={(e) => setDataVenda(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>
      <div className="mb-2">
        <label className="block font-medium">Data de Cancelamento</label>
        <input
          type="date"
          value={dataCancelamento}
          onChange={(e) => setDataCancelamento(e.target.value)}
          className="border px-2 py-1 w-full"
        />
      </div>
      <div className="mb-2">
        <label className="block font-medium">Motivo de Cancelamento</label>
        <input
          type="text"
          value={motivoCancelamento}
          onChange={(e) => setMotivoCancelamento(e.target.value)}
          className="border px-2 py-1 w-full"
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
        <label className="block font-medium">Pedido</label>
        <select
          value={pedidoId}
          onChange={(e) => setPedidoId(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        >
          <option value="">-- Selecione um pedido --</option>
          {pedidos.map((pedido) => (
            <option key={pedido.id} value={pedido.id}>
              {pedido.numeroPedido} (ID: {pedido.id})
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
