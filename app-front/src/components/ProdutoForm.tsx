import { useEffect, useState } from "react";

interface ProdutoFormProps {
  onSubmit: (data: any) => void;
  onCancel?: () => void;
  initialData?: any;
  isEditing?: boolean;
  pedidos: any[];
}

export function ProdutoForm({
  onSubmit,
  onCancel,
  initialData = {},
  isEditing = false,
  pedidos = [],
}: ProdutoFormProps) {
  const [codigo, setCodigo] = useState("");
  const [nome, setNome] = useState("");
  const [descricao, setDescricao] = useState("");
  const [preco, setPreco] = useState<number | "">("");
  const [desconto, setDesconto] = useState<number | "">("");
  const [pedidoId, setPedidoId] = useState("");

  useEffect(() => {
    setCodigo(initialData.codigo || "");
    setNome(initialData.nome || "");
    setDescricao(initialData.descricao || "");
    setPreco(initialData.preco ?? "");
    setDesconto(initialData.desconto ?? "");
    setPedidoId(initialData.pedido?.id ? String(initialData.pedido.id) : "");
  }, [initialData]);

  const limparFormulario = () => {
    setCodigo("");
    setNome("");
    setDescricao("");
    setPreco("");
    setDesconto("");
    setPedidoId("");
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ 
      codigo, 
      nome, 
      descricao, 
      preco, 
      desconto, 
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
        {isEditing ? "Editando Produto" : "Cadastrar Novo Produto"}
      </h2>

      <div className="mb-2">
        <label className="block font-medium">Código *</label>
        <input
          type="text"
          value={codigo}
          onChange={(e) => setCodigo(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Nome *</label>
        <input
          type="text"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Descrição</label>
        <input
          type="text"
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          className="border px-2 py-1 w-full"
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Preço *</label>
        <input
          type="number"
          value={preco}
          onChange={(e) => setPreco(Number(e.target.value))}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Desconto</label>
        <input
          type="number"
          value={desconto}
          onChange={(e) => setDesconto(Number(e.target.value))}
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
