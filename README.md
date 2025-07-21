# Sistema de Gerenciamento de Vendas

Aplicação web full stack para controle de vendas, pedidos, produtos e clientes. Desenvolvido com **Spring Boot** no back-end e **React** com **TailwindCSS** no front-end. Permite que vendedores registrem pedidos, acompanhem status e acessem relatórios de desempenho.

---

## 🚀 Funcionalidades

### 🔒 Autenticação
- Controle de acesso com Spring Security

### 📦 Gerenciamento de Vendas
- Cadastro de produtos, clientes e pedidos
- Registro de vendas com múltiplos produtos
- Cálculo de valor total do pedido automaticamente

### 📈 Relatórios
- Resumo de vendas por período
- Lista de pedidos pendentes
- Ranking de clientes mais ativos

---

## 🛠️ Tecnologias Utilizadas

### Back-end (Spring Boot)
- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Security
- Spring Data JPA
- H2 Database (dev)
- CORS Configuration

### Front-end (React)
- Vite + React 19 + TypeScript
- TailwindCSS
- Axios
- React Router DOM

---

## 📂 Estrutura do Projeto

```
app-front/           # Projeto React
 └── src/
     └── components/
     └── pages/
     └── services/
vendasonline/         # Projeto Spring Boot
 └── src/main/java/... 
```

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- Java 17+
- Node.js 23+
- npm ou yarn

### 1. Backend (Spring Boot)

```bash
cd vendasonline
./mvnw spring-boot:run
```

### 2. Frontend (React)

```bash
cd app-front
npm install
npm run dev
```

Acesse o app em: [http://localhost:5173](http://localhost:5173)

---

## 🔐 Configuração de Segurança

- CORS habilitado para integração com o React (`localhost:5173`).

---

## 📄 Licença

Este projeto está licenciado sob a licença MIT.