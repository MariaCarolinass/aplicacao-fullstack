export function Footer() {
  return (
    <footer className="bg-gray-100 text-center text-xs sm:text-sm text-gray-600 py-2 px-4 border-t">
      © {new Date().getFullYear()} Todos os direitos reservados.{' '}
      <a
        href="https://github.com/MariaCarolinass/aplicacao-fullstack"
        target="_blank"
        rel="noopener noreferrer"
        className="text-blue-600 hover:underline break-words"
      >
        GitHub
      </a>
    </footer>
  );
}