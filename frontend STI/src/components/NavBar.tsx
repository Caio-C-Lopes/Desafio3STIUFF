import { Link } from 'react-router-dom'

export default function Navbar() {
  return (
    <nav className="w-full bg-gray-800 text-white shadow-md fixed top-0 left-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16 items-center">
          <div className="hidden md:flex space-x-4">
            <Link to="/" className="px-3 py-2 rounded-md hover:bg-gray-700">Home</Link>
            <Link to="/alunos" className="px-3 py-2 rounded-md hover:bg-gray-700">Alunos</Link>
            <Link to="/cursos" className="px-3 py-2 rounded-md hover:bg-gray-700">Cursos</Link>
            <Link to="/disciplinas" className="px-3 py-2 rounded-md hover:bg-gray-700">Disciplinas</Link>
            <Link to="/historicos" className="px-3 py-2 rounded-md hover:bg-gray-700">Históricos</Link>
            <Link to="/cr" className="px-3 py-2 rounded-md hover:bg-gray-700">CR</Link>
            <Link to="/historicos/aluno" className="px-3 py-2 rounded-md hover:bg-gray-700">Buscar Histórico por Matrícula</Link>
            <Link to="/crporcurso" className="px-3 py-2 rounded-md hover:bg-gray-700">CR por Curso</Link>
            <Link to="/historicos/cadastrar" className="px-3 py-2 rounded-md hover:bg-gray-700">Cadastrar Histórico</Link>
          </div>
        </div>
      </div>
    </nav>
  )
}
