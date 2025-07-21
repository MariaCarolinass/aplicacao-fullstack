import { useEffect, useState } from "react";

interface ClienteFormProps {
  onSubmit: (data: any) => void;
  onCancel?: () => void;
  initialData?: any;
  isEditing?: boolean;
}

export function ClienteForm({
  onSubmit,
  onCancel,
  initialData = {},
  isEditing = false,
}: ClienteFormProps) {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [telefone, setTelefone] = useState("");
  const [endereco, setEndereco] = useState("");

  useEffect(() => {
    setNome(initialData.nome || "");
    setEmail(initialData.email || "");
    setTelefone(initialData.telefone || "");
    setEndereco(initialData.endereco || "");
  }, [initialData]);

  const limparFormulario = () => {
    setNome("");
    setEmail("");
    setTelefone("");
    setEndereco("");
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ nome, email, telefone, endereco });
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
        {isEditing ? "Editando Cliente" : "Cadastrar Novo Cliente"}
      </h2>

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
        <label className="block font-medium">Email *</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-2">
        <label className="block font-medium">Telefone *</label>
        <input
          type="text"
          value={telefone}
          onChange={(e) => setTelefone(e.target.value)}
          className="border px-2 py-1 w-full"
          required
        />
      </div>

      <div className="mb-4">
        <label className="block font-medium">Endereço</label>
        <input
          type="text"
          value={endereco}
          onChange={(e) => setEndereco(e.target.value)}
          className="border px-2 py-1 w-full"
        />
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
