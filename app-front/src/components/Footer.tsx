export function Footer() {
  return (
    <footer className="bg-gray-100 text-center text-sm text-gray-600 py-3 border-t">
      © {new Date().getFullYear()} Todos os direitos reservados.{' '}
      <a
        href="https://github.com/MariaCarolinass/aplicacao-fullstack"
        target="_blank"
        rel="noopener noreferrer"
        className="text-blue-600 hover:underline"
      >
        GitHub
      </a>
    </footer>
  );
}
